package tour.signinout;

import tour.main.MainPage;
import tour.registration.RegistrationPage;
import tour.session.SignInSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import tour.header.HeaderPanel;

public final class SignInPage extends WebPage {

    public SignInPage() {
        add(new HeaderPanel("headerPanel"));
        add(new SignInForm("SignInForm"));
    }

    public final class SignInForm extends Form<Void> {

        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";
        private final ValueMap properties = new ValueMap();

        public SignInForm(final String id) {
            super(id);
            
            add(new FeedbackPanel("feedback"));
            add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)));
            add(new Link("regPage") {
                @Override
                public void onClick() {
                    setResponsePage(RegistrationPage.class);
                }
            });
        }

        @Override
        public final void onSubmit() {
            SignInSession session = getMySession();
            if (session.signIn(getUsername(), getPassword())) {
                setResponsePage(MainPage.class);
            } else {
                String errmsg = getString("loginError", null, "Unable to sign you in");
                error(errmsg);
            }
        }

        private String getPassword() {
            return properties.getString(PASSWORD);
        }

        private String getUsername() {
            return properties.getString(USERNAME);
        }

        private SignInSession getMySession() {
            return (SignInSession) getSession();
        }
    }
}