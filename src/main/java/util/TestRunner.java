package util;

public class TestRunner {
    public static void main(String[] args) {
        // Calling getSessionFactory() triggers the static initializer block
        HibernateUtil.getSessionFactory();
        HibernateUtil.shutdown();
    }
}
