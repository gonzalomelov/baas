package uy.com.group05.baascore.common.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;

public class StartupListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
//		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//		
//		mapperFactory.classMap(Application.class, ApplicationDTO.class)
//			.byDefault()
//			.register();
//		
//		mapperFactory.classMap(User.class, UserDTO.class)
//			.exclude("password")
//			.exclude("applications")
//			.byDefault()
//			.register();
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		// shut down logic?
	}
}