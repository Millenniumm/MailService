package tour.profile;

import mail.account.Account;
import mail.menupanel.MenuPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Valentin
 */
public final class Profile extends WebPage {

    Account acc = new Account();

    public Profile() {
        add(new MenuPanel("menuPanel"));
        
        final TextField yourName = new TextField("yourName",new PropertyModel<String>(acc,"name"));
        final TextField yourSurname = new TextField("yourSurname",new PropertyModel<String>(acc,"surname"));
        final TextField yourAge = new TextField("yourAge",new PropertyModel<String>(acc,"age"));
        final TextField yourPerCode = new TextField("yourPerCode",new PropertyModel<String>(acc,"persCode"));
        
        Button saveButton = new Button("saveChanges") {
            @Override
            public void onSubmit() {
            
            }
        };
        
        Form profileForm = new Form("profileForm");
        profileForm.add(yourName, yourSurname, yourAge, yourPerCode, saveButton);
        
        final PasswordTextField oldPassword = new PasswordTextField("oldPassword",new PropertyModel<String>(acc,"password"));
        final PasswordTextField newPassword = new PasswordTextField("newPassword",new PropertyModel<String>(acc,"password"));
        final PasswordTextField repeatNewPassword = new PasswordTextField("repeatNewPassword",new PropertyModel<String>(acc,"password"));
        
        Button changeButton = new Button("savePw") {
            @Override
            public void onSubmit() {
            
            }
        };
        
        Form passwordForm = new Form("passwordForm");
        passwordForm.add(oldPassword, newPassword, repeatNewPassword, changeButton);
        
        add(profileForm, passwordForm);
    }
}
