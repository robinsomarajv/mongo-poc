package com.ust.supplychain.services.servlet.modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jongo.Jongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.ust.supplychain.dao.LogisticalItemMongoDAO;
import com.ust.supplychain.dao.MongoDao;
import com.ust.supplychain.dao.ProductMongoDAO;
import com.ust.supplychain.dao.TradeItemMongoDAO;
import com.ust.supplychain.dao.apis.LogisticalItemDAO;
import com.ust.supplychain.dao.apis.ProductDAO;
import com.ust.supplychain.dao.apis.TradeItemDAO;
import com.ust.supplychain.services.servlet.config.GenericBootstrapConstants;
/**
 * Guice Module to load Properties file and bind it to Guice Injector.<br>
 * Properties can later be used in constructor or field injection by using: <br> 
 * <code>@Inject @Named("name.of.the.key") private String propValue;</code>
 * 
 */
public class BootstrapPropertiesModule extends AbstractModule{
	
	@Override
	protected void configure() {
		Properties bootstrapProperties = new Properties();
		try {
			InputStream is = getClass().getResourceAsStream("/"+GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE);
			bootstrapProperties.load(is);
			Names.bindProperties(binder(), bootstrapProperties);

			Injector injector = Guice.createInjector(new AbstractModule() {

				@Override
				protected void configure() {
					InputStream is = getClass().getResourceAsStream("/"+GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE);
					Properties bootstrapProperties = new Properties();
					try {
						bootstrapProperties.load(is);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Names.bindProperties(binder(), bootstrapProperties);
				}
				
			});
			
			bind(Jongo.class).toInstance(injector.getInstance(MongoDao.class).getJongo());
			bind(ObjectMapper.class).toInstance(new ObjectMapper());
			bind(LogisticalItemDAO.class).to(LogisticalItemMongoDAO.class);
			bind(TradeItemDAO.class).to(TradeItemMongoDAO.class);
			bind(ProductDAO.class).to(ProductMongoDAO.class);
		} catch (FileNotFoundException e) {
	        System.out.println("The configuration file "+ GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE + " can not be found");
	    } catch (IOException e) {
	        System.out.println("I/O Exception during loading configuration");
	    }
	}
	
}
