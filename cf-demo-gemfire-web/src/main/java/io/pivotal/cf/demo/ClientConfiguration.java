/**
 * 
 */
package io.pivotal.cf.demo;

import io.pivotal.cf.demo.entity.Attendee;
import io.pivotal.spring.cloud.service.gemfire.GemfireServiceConnectorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer;

/**
 * This class gets the required GemFire references to use the Spring Data GemFire Repositories
 * @author 
 *
 */
@Configuration
@EnableGemfireRepositories(basePackages="io.pivotal.cf.demo.repo")
public class ClientConfiguration extends AbstractCloudConfig {
	
	@Value("${cache_name}")
	private String cache_name;
	
	@Value("${region_name}")
	private String region_name;
	
	public ServiceConnectorConfig createGemfireConnectorConfig() {
	    GemfireServiceConnectorConfig gemfireConfig = new GemfireServiceConnectorConfig();
	    gemfireConfig.setPoolIdleTimeout(7777L);
	    gemfireConfig.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.cf.demo.entity.*"));
	    gemfireConfig.setPdxPersistent(true);
	    return gemfireConfig;
	  }


	  @Bean
	  public ClientCache getGemfireClientCache() {
	    CloudFactory cloudFactory = new CloudFactory();
	    Cloud cloud = cloudFactory.getCloud();
	    ClientCache cache = cloud.getServiceConnector(cache_name, ClientCache.class, createGemfireConnectorConfig());
	    return cache;
	  }
	  
	  @Bean
	  public Pool pool(GemFireCache cache){
	    return ((ClientCache)cache).getDefaultPool();
	  }
	  
	  @Bean
	  public ClientRegionFactoryBean<String,Attendee> personRegion(GemFireCache cache){
	    ClientRegionFactoryBean<String,Attendee> region = new ClientRegionFactoryBean<>();
	    region.setCache(cache);
	    region.setName(region_name);
	    region.setShortcut(ClientRegionShortcut.PROXY);
	    return region;

	  }   

}
