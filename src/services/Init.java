package services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Init implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            Class.forName("persistance.mediatek.MediathequeData");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("*********** ça marche bien ***********");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}