package uy.com.group05.baascore.common.mapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Operation;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.OperationDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionDTO;
import uy.com.group05.baascore.sl.entitiesws.RoleDTO;
import uy.com.group05.baascore.sl.entitiesws.SimpleApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.entitiesws.UserRegisterDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Singleton
public class Mapper {

	private MapperFactory mapperFactory;
	
	private MapperFacade mapper;
	
	public Mapper() {}
	
	@PostConstruct
	public void init() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		
		/* Mappings */
		
		//User <> UserDTO
		mapperFactory.classMap(User.class, UserDTO.class)
			.byDefault()
			.register();
		
		//UserRegisterDTO <> User
		mapperFactory.classMap(UserRegisterDTO.class, User.class)
			.byDefault()
			.register();
		
		//Application <> ApplicationDTO
		mapperFactory.classMap(Application.class, ApplicationDTO.class)
			.exclude("users")	
			.exclude("clients")
			.field("clients{id}", "clients{id}")
			.field("clients{email}", "clients{email}")
			.exclude("entities")
			.field("entities{id}", "entities{id}")
			.field("entities{name}", "entities{name}")
			.exclude("roles")
			.field("roles{id}", "roles{id}")
			.field("roles{name}", "roles{name}")
			.byDefault()
			.register();
		
		//Application <> ApplicationDTO
		mapperFactory.classMap(Application.class, SimpleApplicationDTO.class)
			.byDefault()
			.register();
		
		//Role <> RoleDTO
		mapperFactory.classMap(Role.class, RoleDTO.class)
			.exclude("application")
			.exclude("permissions")
			.byDefault()
			.register();
		
		//PushChannel <> PushChannelDTO
		mapperFactory.classMap(PushChannel.class, PushChannelDTO.class)
		.byDefault()
		.register();

		//Permission <> PermissionDTO
		mapperFactory.classMap(Permission.class, PermissionDTO.class)
			.exclude("application")	
			.field("application.id", "application.id")
			.field("application.name", "application.name")
			.field("application.apiClientId", "application.name")
			.exclude("entity")
			.field("entity.id", "entity.id")
			.field("entity.name", "entity.name")
			.exclude("role")
			.field("role.id", "role.id")
			.field("role.name", "role.name")
			.byDefault()
			.register(); 
		
		//Operation <> OperationDTO
		mapperFactory.classMap(Operation.class, OperationDTO.class)
			.byDefault()
			.register();
		
		/* Fin Mappings */
		
		mapper = mapperFactory.getMapperFacade();		
	}

	public MapperFactory getMapperFactory() {
		return mapperFactory;
	}

	public void setMapperFactory(MapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

	public MapperFacade getMapper() {
		return mapper;
	}

	public void setMapper(MapperFacade mapper) {
		this.mapper = mapper;
	}
	
	
 
}
