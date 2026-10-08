package server;

import jakarta.persistence.*;

@Entity
@Table(name = "_business_owners")
public class _businesses_owners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String business_name;
    private String service_type;
    private String id_number;
    private String business_contact;

    // Default no-arg constructor required by JPA
    public _businesses_owners() {}

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getService_type() {
        return service_type;
    }

    // Fixed typo: was setService_typet
    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getBusiness_contact() {
        return business_contact;
    }

    public void setBusiness_contact(String business_contact) {
        this.business_contact = business_contact;
    }
}