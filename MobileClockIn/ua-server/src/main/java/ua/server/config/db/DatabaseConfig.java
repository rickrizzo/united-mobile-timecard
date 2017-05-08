package ua.server.config.db;

import java.util.Arrays;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableCaching
public abstract class DatabaseConfig extends CachingConfigurerSupport {

	// private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	// private static final String PROPERTY_NAME_DATABASE_PASSWORD =
	// "db.password";
	// private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	// private static final String PROPERTY_NAME_DATABASE_USERNAME =
	// "db.username";

	// private static final String PROPERTY_NAME_HIBERNATE_DIALECT =
	// "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	// private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN
	// = "entitymanager.packages.to.scan";

	@Autowired
	private Environment env;

	// @Bean
	// // @Profile("javaee")
	// public JndiObjectFactoryBean dataSource() throws IllegalArgumentException
	// {
	// JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
	// dataSource.setExpectedType(DataSource.class);
	// dataSource.setJndiName("java:jboss/datasources/FDDS");
	// return dataSource;
	// }

	abstract public DataSource dataSource() throws IllegalArgumentException, NamingException;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws NamingException {
		// public SessionFactory sessionFactory() throws NamingException{

		// LocalSessionFactoryBuilder builder = new
		// LocalSessionFactoryBuilder(dataSource());
		// builder.addProperties(hibernateProperties());
		// return builder.buildSessionFactory();

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.gess.fd.model", "com.gess.common.model");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	private Properties hibernateProperties() {

		Properties properties = new Properties();

		properties.put("hibernate.cache.use_second_level_cache", true);
		properties.put("hibernate.cache.use_query_cache", true);
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

		// properties.put("net.sf.ehcache.configurationResourceName",
		// "/WEB-INF/ehcache.xml");

		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		txManager.setAllowResultAccessAfterCompletion(true);
		return txManager;
	}

	// @Bean
	// @Profile("unit_test")
	// public DataSource testDataSource() {
	// return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
	// .build();
	// }

	@Bean
	@Override
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}
}
