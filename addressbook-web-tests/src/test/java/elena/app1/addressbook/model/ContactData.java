package elena.app1.addressbook.model;

public class ContactData {
    private final int id;
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String address;
    private final String workphone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public ContactData(int id, String firstname, String lastname, String company, String address, String workphone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.workphone = workphone;
    }

    public ContactData(String firstname, String lastname, String company, String address, String workphone) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.workphone = workphone;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' + "lastname='" + lastname + '\'' + "firstname='" + firstname + '\'' +
                "company='" + company + '\'' + "address='" + address + '\'' +
                "workphone='" + workphone + '\'' +
                '}';

    }

    public int getId() {
        return id;
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
