package tour.session;

import tour.main.MainPage;
import tour.signinout.SignInPage;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;


public class TourApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }
    
    @Override
    public Session newSession(Request request, Response response)
    {
        return new SignInSession(request);
    }

    @Override
    protected void init()
    {
        super.init();

        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy()
        {
            @Override
            public boolean isActionAuthorized(Component component, Action action)
            {
                return true;
            }

            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                Class<T> componentClass)
            {
                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass))
                {
                    if (((SignInSession)Session.get()).isSignedIn())
                    {
                        return true;
                    }
                    throw new RestartResponseAtInterceptPageException(SignInPage.class);
                }
                return true;
            }
        });
    }
}
