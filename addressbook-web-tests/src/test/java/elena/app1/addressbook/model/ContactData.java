package elena.app1.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "addressbook")
public class ContactData {
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String firstname;
    @Expose
    @Column (name = "lastname")
    private String lastname;
    @Expose
    @Column (name = "company")
    private String company;
    @Expose
    @Transient
    private String address;
    @Transient
    private String address2;

    @Column (name = "work")
    @Type(type = "text")
    private String workphone;

    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilephone;
    @Column (name = "home")
    @Type(type = "text")
    private String homephone;
    @Transient
    private String allPhones;
    @Column (name = "email")
    @Type(type = "text")
    private String email;
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;

    @Column (name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    private String allAddresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public Groups getGroups() {
        return new Groups(groups);
    }

    public String getAllEmails() {
        return allEmails;
    }
    public String getAllAddresses() {
        return allAddresses;
    }

    public String getAddress2() {
        return address2;
    }

    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {return allPhones;}

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

    public String getMobilephone() {
        return mobilephone;
    }

    public String getHomephone() {
        return homephone;
    }


    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return  this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;

    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }
    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }
    public ContactData withEmail(String email1) {
        this.email = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
