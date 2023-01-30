package Contacts;

import java.util.List;

public abstract class Contacts {

    protected String name;
    protected List<String> lastname;
    protected String type;
    protected String address;
    protected List<String> telelphoneNumbers;
    protected List<String> emailAddresses;
    protected List<String> socialAcounts;
    protected String profession;

    public Contacts() {
    }

    public Contacts(String name, List<String> lastname, String type, String address, List<String> telelphoneNumbers,
            List<String> emailAddresses,
            List<String> socialAcounts, String profession) {
        this.name = name;
        this.lastname = lastname;
        this.type = type;
        this.address = address;
        this.telelphoneNumbers = telelphoneNumbers;
        this.emailAddresses = emailAddresses;
        this.socialAcounts = socialAcounts;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLastname() {
        return lastname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLastname(List<String> lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTelelphoneNumbers() {
        return telelphoneNumbers;
    }

    public void setTelelphoneNumbers(List<String> telelphoneNumbers) {
        this.telelphoneNumbers = telelphoneNumbers;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public List<String> getSocialAcounts() {
        return socialAcounts;
    }

    public void setSocialAcounts(List<String> socialAcounts) {
        this.socialAcounts = socialAcounts;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Contacts)) {
            return false;
        }
        Contacts c = (Contacts) o;
        return name.equals(c.name) && telelphoneNumbers.get(0).equals(c.telelphoneNumbers.get(0));
    }

    public abstract String getRelation();

    public abstract void setRelation(String relation);
}
