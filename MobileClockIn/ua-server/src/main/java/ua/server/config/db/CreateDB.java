package ua.server.config.db;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class CreateDB {

	@Autowired
	private Environment environment;

	@Autowired
	protected SessionFactory sessionFactory;

	protected static Session session;

	private FlushMode flushMode = FlushMode.COMMIT;

	// private static final Log logger = LogFactory.getLog(BaseTest.class);

	// private final static String URL =
	// "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
	// private final static String USER = "sa";
	// private final static String PASSWORD = "sa";

	///////////////////

	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = sessionFactory.openSession();
		// getCurrentSession();
		FlushMode flushMode = this.flushMode;
		if (flushMode != null) {
			session.setFlushMode(flushMode);
		}
		return session;
	}

	protected static void closeSession(Session session) {
		SessionFactoryUtils.closeSession(session);
	}

	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// JdbcDataSource ds = new JdbcDataSource();
	// ds.setURL(URL);
	// ds.setUser(USER);
	// ds.setPassword(PASSWORD);
	//
	// SimpleNamingContextBuilder builder = null;
	// try {
	// builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
	// builder.bind("java:jboss/datasources/FDDS", ds);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	public void manageDB(List<Class<?>> classes, boolean label) {
		final StringBuilder script = new StringBuilder();
		String[] creationScript = null;
		Properties dialectProps = new Properties();
		dialectProps.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		Configuration config = new Configuration();
		for (Class<?> cls : classes) {
			config.addAnnotatedClass((Class<?>) cls);
		}

		if (label)
			creationScript = config.generateSchemaCreationScript(Dialect.getDialect(dialectProps));
		else
			creationScript = config.generateDropSchemaScript(Dialect.getDialect(dialectProps));

		for (final String string : creationScript) {
			script.append(string).append(";\n");
		}
		session.createSQLQuery(script.toString()).executeUpdate();
	}
}
