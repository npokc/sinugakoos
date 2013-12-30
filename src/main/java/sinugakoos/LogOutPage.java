package sinugakoos;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LogOutPage extends WebPage implements AuthenticatedWebPage {
    public LogOutPage(final PageParameters parameters) {
        getSession().invalidate();
    }
}
