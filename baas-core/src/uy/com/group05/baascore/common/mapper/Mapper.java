package uy.com.group05.baascore.common.mapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import uy.com.group05.baascore.common.entities.User;
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
		/*mapperFactory.classMap(Application.class, ApplicationDTO.class)
		.byDefault()
		.register();
		*/
		
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
