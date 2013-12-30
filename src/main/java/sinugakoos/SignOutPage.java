package sinugakoos;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOutPage extends WebPage implements AuthenticatedWebPage {
    public SignOutPage(final PageParameters parameters) {
        getSession().invalidate();
    }
}
