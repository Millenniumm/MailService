package tour.profile;

import java.util.List;
import mail.account.Account;
import mail.account.AccountDAO;
import mail.menupanel.MenuPanel;
import mail.session.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import tour.header.HeaderPanel;

/**
 *
 * @author Valentin
 */
public final class Profile extends WebPage {

    private final Account acc = new Account();
    private final AccountDAO aO = new AccountDAO();
    private String name;
    private String surname;
    private String age;
    private String persCode;

    public Profile() {
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        List<Account> accounts = aO.getAccountList();
        SignInSession session = getMySession();
        Account currentAccount = getAccount(accounts, session.getUser());

        setName(currentAccount.getName());
        setSurname(currentAccount.getSurname());
        setAge(currentAccount.getAge());
        setPersCode(currentAccount.getPersCode());
        
        
        final TextField yourName = new TextField("yourName", new PropertyModel<String>(this, "name"));
        final TextField yourSurname = new TextField("yourSurname", new PropertyModel<String>(this, "surname"));
        final TextField yourAge = new TextField("yourAge", new PropertyModel<String>(this, "age"));
        final TextField yourPerCode = new TextField("yourPerCode", new PropertyModel<String>(this, "persCode"));

        Button saveButton = new Button("saveChanges") {
            @Override
            public void onSubmit() {
                String login = ((SignInSession) Session.get()).getUser().toString();
                String name = yourName.getValue();
                String surname = yourSurname.getValue();
                String age = yourAge.getValue();
                String persCode = yourPerCode.getValue();
                AccountDAO.updateAccount(login,name,surname,age,persCode);
                String msg = getString("profileChanged", null, "Profile data changed!");
                success(msg);
            }
        };

        Form profileForm = new Form("profileForm");
        
        final PasswordTextField oldPassword = new PasswordTextField("oldPassword", new PropertyModel<String>(acc, "password"));
        final PasswordTextField newPassword = new PasswordTextField("newPassword", new PropertyModel<String>(acc, "newPassword"));
        final PasswordTextField repeatNewPassword = new PasswordTextField("repeatNewPassword", new PropertyModel<String>(acc, "repeatNewPassword"));
       
        Button changeButton = new Button("savePw") {
            @Override
            public void onSubmit() {
                String login = ((SignInSession) Session.get()).getUser().toString();
                String oldpassword = oldPassword.getValue();
                String newpassword = newPassword.getValue();
                String repeatnewpassword = repeatNewPassword.getValue();
                
                if(AccountDAO.checkOldPassword(login, oldpassword)==true)
                {
                    if(newpassword.equals(repeatnewpassword))
                    {
                        AccountDAO.changePassword(login, newpassword);
                        String msg = getString("passwordChanged", null, "Password changed!");
                        success(msg);
                    }
                    else
                    {
                        String errmsg = getString("repeatPasswordError", null, "'Repeat new password' do not match 'New Password'");
                        error(errmsg);
                    }
                }
                else
                {
                    String errmsg = getString("oldPasswordError", null, "Wrong old password.");
                    error(errmsg);
                }
                }
        };
        
        Form passwordForm = new Form("passwordForm");
        FeedbackPanel successP = new FeedbackPanel("successP");
        profileForm.add(yourName, yourSurname, yourAge, yourPerCode, saveButton, successP);
        passwordForm.add(oldPassword, newPassword, repeatNewPassword, changeButton);
        add(profileForm, passwordForm);
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

    private SignInSession getMySession() {
        return (SignInSession) getSession();
    }

    private Account getAccount(List<Account> accounts, String user) {
        Account account = new Account();
        for (Account ac : accounts) {
            if (ac.getLogin().equals(user)) {
                account = ac;
            }
        }
        return account;
    }
}