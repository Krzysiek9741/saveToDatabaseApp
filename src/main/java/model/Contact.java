package model;


public class Contact {

    private Long id;
    private Long customerId;
    private Type contactType = Type.UKNOWN;
    private String contact;

    public Contact(Long id, Long customerId, Type contactType, String contact) {
        this.id = id;
        this.customerId = customerId;
        this.contactType = contactType;
        this.contact = contact;
    }

    public Contact(String contact) {
        this.contact = contact;
    }

    public Contact(String contactType, String contact){
        this.contact = contact;
        if (contactType.equals("phone")){
            this.contactType = Type.PHONE;
        }else if (contactType.equals("email")){
            this.contactType = Type.EMAIL;
        }else if (contactType.equals("jabber")){
            this.contactType = Type.JABBER;
        }else {
            this.contactType = Type.UKNOWN;
        }
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
                "contactType=" + contactType +
                ", contact='" + contact + '\'' +
                '}';
    }
}
