package sinugakoos;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

public class WicketApplication extends WebApplication {
    public Class<? extends WebPage> getHomePage() {
        return LogInPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SignInSession(request);
    }

    @Override
    public void init() {
        super.init();

        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy() {
            public boolean isActionAuthorized(Component component, Action action) {
                return true;
            }

            @Override
            public boolean isResourceAuthorized(IResource iResource, PageParameters pageParameters) {
                return false;
            }

            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                    Class<T> componentClass) {

                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
                    if (((SignInSession) Session.get()).isSignedIn()) {
                        return true;
                    }
                    throw new RestartResponseAtInterceptPageException(SignInPage.class);
                }
                return true;
            }
        });
    }
}
