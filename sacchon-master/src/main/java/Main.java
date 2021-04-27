import jpautil.JpaUtil;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;
import router.CustomRouter;
import security.CorsFilter;
import security.Shield;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class Main extends Application {

    public static final Logger LOGGER = Engine.getLogger(Main.class);

    public Main() {
        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, Shield.ROLE_REPORTER));
        getRoles().add(new Role(this, Shield.ROLE_DOCTOR));
        getRoles().add(new Role(this, Shield.ROLE_PATIENT));
    }

    public static void main(String[] args) throws Exception {

        EntityManager em = JpaUtil.getEntityManager();
        em.close();
        LOGGER.info("Application starting...");

        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/v1", new Main());
        c.start();

        LOGGER.info("Web API started");
        LOGGER.info("URL: http://localhost:9000/v1");
    }

    @Override
    public Restlet createInboundRoot() {
        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);

        Router publicRouter = customRouter.publicResources();
        ChallengeAuthenticator apiGuard = shield.createApiGuard();

        Router apiRouter = customRouter.protectedResources();
        apiGuard.setNext(apiRouter);

        publicRouter.attachDefault(apiGuard);

        CorsFilter corsFilter = new CorsFilter(this);

        return corsFilter.createCorsFilter(publicRouter);
    }
}

