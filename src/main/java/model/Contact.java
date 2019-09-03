package model;

public class Contact {

    private Long id;
    private Long customerId;
    // review to przypisanie jest zbędne. KOnstruktor nam je załatwia.
    private Type contactType = Type.UNKNOWN;
    private String contact;

    public Contact(String contactType, String contact) {
        this.contact = contact;
        // review to co poniżej to taka drobna logika biznesowa. W konstruktorze nie powinno być takiej.
        // review dlaczego nie powinno być logiki - nawet najprostszej - zobacz co się stanie, jeżeli damy contact == null
        if (contactType.equals("phone")) {
            this.contactType = Type.PHONE;
        } else if (contactType.equals("email")) {
            this.contactType = Type.EMAIL;
        } else if (contactType.equals("jabber")) {
            this.contactType = Type.JABBER;
        } else {
            this.contactType = Type.UNKNOWN;
        }
    }

    public Long getId() {
        return id;
    }

    // review po co nam te settery? Dlaczego nie przekażemy wszyskitego w konstruktorze? Usuńmy settery, te, które nie będa potrzebne.
    // + obiekty powinny być niemutowalne
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
}
