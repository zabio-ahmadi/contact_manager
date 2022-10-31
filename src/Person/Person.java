package Person;

public class Person {
    private String name;
    private String[] lastname;
    private String address;
    private String[] telelphoneNumbers;
    private String[] emailAddresses;
    private String[] socialAcounts;
    private String profession;


    public Person(){}

    public Person(String name, String[] lastname, String address, String[] telelphoneNumbers, String[] emailAddresses, String[] socialAcounts, String profession) {
        this.name = name;
        this.lastname = lastname;
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

    public String[] getLastname() {
        return lastname;
    }

    public void setLastname(String[] lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getTelelphoneNumbers() {
        return telelphoneNumbers;
    }

    public void setTelelphoneNumbers(String[] telelphoneNumbers) {
        this.telelphoneNumbers = telelphoneNumbers;
    }

    public String[] getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String[] emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String[] getSocialAcounts() {
        return socialAcounts;
    }

    public void setSocialAcounts(String[] socialAcounts) {
        this.socialAcounts = socialAcounts;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}