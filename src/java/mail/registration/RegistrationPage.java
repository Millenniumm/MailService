package mail.registration;

import mail.account.Account;
import mail.account.AccountDAO;
import mail.signinout.SignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

public class RegistrationPage extends WebPage {

    Account acc = new Account();

    public RegistrationPage() {

        final TextField loginEnter = new TextField("loginEnter", new PropertyModel<String>(acc, "login"));
        final PasswordTextField passwordEnter = new PasswordTextField("passwordEnter", new PropertyModel<String>(acc, "password"));
        add(new FeedbackPanel("error"));
        Button createButton = new Button("Create") {
            @Override
            public void onSubmit() {
                String username = loginEnter.getValue();
                String password = passwordEnter.getValue();
                if (AccountDAO.checkLogin(username) == true) {
                    error("This login alredy taken");
                } else {
                    AccountDAO.addNewAccount(username, password);
                    setResponsePage(SignInPage.class);
                }

            }
        };
        Form registerForm = new Form("registerForm");
        registerForm.add(loginEnter, passwordEnter, createButton);
        add(registerForm);
    }
}
