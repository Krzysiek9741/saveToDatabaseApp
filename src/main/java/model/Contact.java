package model;


public class Contact {

    private Long id;
    private Long customerId;
    private Type contactType;
    private String contact;

    public Contact(Long id, Long customerId, Type contactType, String contact) {
        this.id = id;
        this.customerId = customerId;
        this.contactType = contactType;
        this.contact = contact;
    }

    public Contact(String contact){
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Type getContactType() {
        return contactType;
    }

    public void setContactType(Type contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact='" + contact + '\'' +
                '}';
    }
}
