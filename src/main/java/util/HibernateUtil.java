package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Loads hibernate.properties automatically from src/main/resources
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .build();

            MetadataSources sources = new MetadataSources(standardRegistry);

            // Register your entity classes here when ready:
            // sources.addAnnotatedClass(server._businesses_owners.class);

            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            // Test connection using an open session with try-with-resources
            try (Session session = sessionFactory.openSession()) {
                Object result = session.createNativeQuery("SELECT 1", Object.class).getSingleResult();
                System.out.println("Database connection successful! Result: " + result);
            }

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}