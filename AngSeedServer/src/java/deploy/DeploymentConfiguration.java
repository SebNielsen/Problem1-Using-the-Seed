package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DeploymentConfiguration  implements ServletContextListener {

    public static String PU_NAME = "PU";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> env = System.getenv();
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "pu_openshift";
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
