package elena.app1.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String address;
    private final String workphone;

    public ContactData(String firstname, String lastname, String company, String address, String workphone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.workphone = workphone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkphone() {
        return workphone;
    }
}
