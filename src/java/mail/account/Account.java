package mail.account;

public class Account {

    private String login;
    private String password;
    private String age;
    private String persCode;
    private String name;
    private String surname;
    private Boolean admin;
    private String newPassword;
    private String repeatNewPassword;

    public Account() {
    }

    public Account(String login, String password, String name, String surname, String age, String persCode) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.persCode = persCode;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPersCode() {
        return persCode;
    }

    public void setPersCode(String persCode) {
        this.persCode = persCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }
}
