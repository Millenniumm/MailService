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
        final PasswordTextField passwordEnter = new PasswordTextField("password", new PropertyModel<String>(acc, "password"));
        final TextField nameEnter = new TextField("nameEnter",new PropertyModel<String>(acc,"name"));
        final TextField surnameEnter = new TextField("surnameEnter",new PropertyModel<String>(acc,"surname"));
        final TextField ageEnter = new TextField("ageEnter",new PropertyModel<String>(acc,"age"));
        final TextField perCodeEnter = new TextField("perCodeEnter",new PropertyModel<String>(acc,"persCode"));  
                               
        FeedbackPanel error = new FeedbackPanel("error");
        Button createButton = new Button("Create") {
            @Override
            public void onSubmit() {
                String username = loginEnter.getValue();
                String password = passwordEnter.getValue();
                String name = nameEnter.getValue();
                String surname = surnameEnter.getValue();
                String age = ageEnter.getValue();
                String persCode = perCodeEnter.getValue();
                
                if (AccountDAO.checkLogin(username) == true) {
                    error("This login alredy taken");
                } else {
                    if((username.equals(""))||(password.equals(""))||(name.equals(""))||(surname.equals(""))||(age.equals(""))||(persCode.equals("")))
                    {
                       error("Fill all required fields");
                    }
                    else
                    {
                        AccountDAO.addNewAccount(username, password, name, surname, age, persCode);
                        setResponsePage(SignInPage.class);
                    }
                }

            }
        };
        Form registerForm = new Form("registerForm");
        registerForm.add(error, loginEnter, passwordEnter, nameEnter, surnameEnter, ageEnter, perCodeEnter, createButton);
        add(registerForm);
    }
}