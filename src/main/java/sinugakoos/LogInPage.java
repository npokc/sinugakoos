package sinugakoos;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.value.ValueMap;

public class LogInPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public LogInPage() {
        add(new FeedbackPanel("feedback"));
        add(new LogInForm("logInForm"));
    }

    public final class LogInForm extends Form<Void> {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        private final ValueMap properties = new ValueMap();

        public LogInForm(final String id) {
            super(id);

            add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)));
        }

        @Override
        public final void onSubmit() {

            SignInSession session = getMySession();

            if (session.signIn(getUsername(), getPassword())) {
                setResponsePage(getApplication().getHomePage());
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
