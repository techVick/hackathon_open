package ge.dt.service.predixapp.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public class CloudFoundryDataSourceConfiguration extends AbstractCloudConfig {

//	@Bean
//	public DataSource dataSource() {
//		return connectionFactory().dataSource();
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan(new String[] { "ge.dt.service.predixapp.model" });
//
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
//
//		return em;
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager(
//			EntityManagerFactory emf) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(emf);
//
//		return transactionManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//
//	Properties additionalProperties() {
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.hbm2ddl.auto", "create");
//		properties.setProperty("hibernate.dialect",
//				"org.hibernate.dialect.PostgreSQLDialect");
//		properties.setProperty("hibernate.show_sql", "true");
//		return properties;
//	}

	// just reference - do not use
	// @Bean
	// public LocalContainerEntityManagerFactoryBean
	// localContainerEntityManagerFactoryBean(
	// DataSource dataSource) throws Exception {
	// LocalContainerEntityManagerFactoryBean em = new
	// LocalContainerEntityManagerFactoryBean();
	// em.setDataSource(dataSource);
	// em.setPackagesToScan(Flight.class.getPackage().getName());
	//
	// JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	// em.setJpaVendorAdapter(vendorAdapter);
	// // em.setJpaProperties(additionalProperties());
	//
	// // em.setPersistenceProvider(new HibernatePersistence());
	// Map<String, String> p = new HashMap<String, String>();
	// p.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
	// p.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES,
	// "initialData.sql");
	// // p.put(org.hibernate.cfg.Environment.DIALECT,
	// // PostgreSQLDialect.class.getName());
	// p.put(org.hibernate.cfg.Environment.DIALECT,
	// "org.hibernate.dialect.PostgreSQLDialect");
	// p.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
	// em.setJpaPropertyMap(p);
	// return em;
	// }

}
