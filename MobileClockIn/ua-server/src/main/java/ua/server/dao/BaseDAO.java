package ua.server.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.PersistenceException;
import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import ua.server.base.util.CollectionUtil;
import ua.server.base.util.StringUtil;
import ua.server.model.entity.IEntity;

//@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDAO extends HibernateDaoSupport{
	
	@Autowired
	protected Properties appProperties;
	
	
	@Autowired
	protected SessionFactory sessionFactory;

	


	public BaseDAO() {
	}
	
	@PostConstruct
	public void init() {
		super.setSessionFactory(sessionFactory);
//		sessionFactory.openSession().setFlushMode(FlushMode.COMMIT);
	}
	

	

	public void refresh(Object entity) {
		getHibernateTemplate().refresh(entity);
	}

	public <T> T getById(Class<T> cls, Serializable id) {
		return getHibernateTemplate().get(cls, id);
	}

	public <T> T getByUuid(Class<T> cls, byte[] uuid) {
		return getByUuid(cls, uuid, "uuid");
	}
	public <T> T getByUuid(Class<T> cls, byte[] uuid, String uuidName) {
		if(StringUtil.isStringEmpty(uuidName))
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        String queryString = "from " + cls.getName() + " c where c." + uuidName + "=?";
        
        return getUnique
        		(queryString, 
        		new Object[]{uuid}); 
        		// new Type[]{UUIDBinaryType.INSTANCE});
		
	}

	public <T> T getByUuid(Class<T> cls, UUID uuid) {
		return getByUuid(cls, uuid, "uuid");
	}
	public <T> T getByUuid(Class<T> cls, UUID uuid, String uuidName) {
		if(StringUtil.isStringEmpty(uuidName))
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String queryString = "from " + cls.getName() + " c where c." + uuidName + "=?";
        
        return getUnique(queryString, 
        		new Object[]{uuid}, 
        		new Type[]{UUIDBinaryType.INSTANCE});
		
	}
	
	
//	@SuppressWarnings({"unchecked"})
//	public <T> T getUnique(String queryString) {
//		return (T)getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString).uniqueResult();
//	}

	@SuppressWarnings({"unchecked"})
	public <T> T getUnique(String queryString) {
		Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
		return (T)query.uniqueResult();
	}

	@SuppressWarnings({"unchecked"})
	public <T> T getUnique(String queryString, Object [] parameters) {
		Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
		setParameters(query, parameters);
		return (T)query.uniqueResult();
	}

	@SuppressWarnings({"unchecked"})
	public <T> T getUnique(String queryString, Object[] parameters, Type[] types) {
		Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameters(parameters, types);
		return (T)query.uniqueResult();
	}



	
	
//	public Object getById(Class<?> cls, Serializable id) {
//		return getHibernateTemplate().get(cls, id);
//	}

//	public Object getByQuery(String query, Object... parameters) {
//		List<?> list = getHibernateTemplate().find(query,parameters);
//		return list != null && list.size() > 0 ? list.get(0) : null;
//	}

	public Object flush(Object entity) {
		getHibernateTemplate().flush();
		return entity;
	}

	/**
	 * 
	 * @param cls

	 */
	@SuppressWarnings({"unchecked"})
	public <T> List<T> findAll(Class<T> cls) {
		final Class<?> clazz = cls;
		try{
//		List<?> l=getHibernateTemplate().find("select d from Department d");
//		return (List<T>)l;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws PersistenceException {
				try{
						Query q = session.createQuery("SELECT h FROM " + clazz.getName()+ " h");
						return q.list();
				}
				catch(Exception e){
						e.printStackTrace();
				}
				return null;
//						ResultList();
			}
		});
		return (List<T>) res;
		}
		catch(Exception e){
//			System.out.print("sdsasfs");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param cls
	 * @param orderBy
	 */
	@SuppressWarnings({"unchecked"})
	public <T> List<T> findAllWithOrder(Class<T> cls, String orderBy) {
		final Class<?> clazz = cls;
		final String order = orderBy;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q;
				if (order != null && !order.equals("")) {
					q = session.createQuery("SELECT h FROM " + clazz.getName()
							+ " h" + " order by h." + order);
				} else {
					q = session.createQuery("SELECT h FROM " + clazz.getName()+ " h");
//					q = session.createQuery("SELECT h FROM " + clazz.getName());
				}
				return q.list();
			}
		});
		return (List<T>) res;
	}

	/**
	 * 
	 * @param cls
	 * @param orderBy
	 * @param start
	 * @param limit
	 */
	@SuppressWarnings({"unchecked"})
	public <T> List<T> findRangeWithOrder(Class<T> cls, String orderBy,
			int start, int limit) {
		final Class<T> clazz = cls;
		final String order = orderBy;
		final int startIndex = start;
		final int endIndex = limit;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT h FROM " + clazz.getName()
						+ " h" + " order by h." + order);
				q.setFirstResult(startIndex);
				q.setMaxResults(endIndex);
				return q.list();
			}
		});
		return (List<T>) res;
	}

	/**
	 * 
	 * @param cls
	 * @param start
	 * @param limit
	 */
	@SuppressWarnings({"unchecked"})
	public <T> List<T> findRange(Class<T> cls, int start, int limit) {
		final Class<T> clazz = cls;
		final int startIndex = start;
		final int endIndex = limit;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT h FROM " + clazz.getName()
						+ " h");
				q.setFirstResult(startIndex);
				q.setMaxResults(endIndex);
				return q.list();
			}
		});
		return (List<T>) res;
	}

	public List<?> findByQuery(String query, String[] paramNames, Object[] paramValues) {
		return (List<?>) getHibernateTemplate().findByNamedParam(query,paramNames,paramValues);
	}

	@SuppressWarnings({"unchecked"})
	public <T> List<T> findByQuery(String query, Object... parameters) {
		return (List<T>) getHibernateTemplate().find(query, parameters);
	}
	
	/**
	 * 
	 * @param query
	 * @param start
	 * @param limit
	 * @param parameters
	 */
	@SuppressWarnings({"unchecked"})
	public <T> List<T> findByQuery(String query, int start, int limit,
			Object... parameters) {
		final String queryString = query;
		final int startIndex = start;
		final int endIndex = limit;
		final Object[] parametersArray = parameters;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery(queryString);
				q.setFirstResult(startIndex);
				q.setMaxResults(endIndex);
				setParameters(q, parametersArray);
				return q.list();
			}
		});
		return (List<T>) res;
	}

	public <T> long countAll(Class<T> cls) {
		final Class<T> clazz = cls;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT count(*) FROM "
						+ clazz.getName() + " h");
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0l;
				return l.get(0);
			}
		});
		return ((Long) res).longValue();
	}

	
	public <T> boolean exists(Class<T> cls,String paramName, Object paramValue)
	{
		String queryString = "from "+cls.getName()+" c where c."+paramName+"=?";
//		String queryString = "from "+cls.getName()+" e where e."+paramName+"=:"+paramName;
		long n=countByQuery(queryString, new Object[]{paramValue});
		return n>0;

//		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {
//
//			public Object  doInHibernate(Session session) throws PersistenceException {
//				Query q = session.createQuery("SELECT count(*) " + queryString);
//				q.setParameter(paramName, paramValue);
//				List<?> l=q.list();
//				if(CollectionUtil.isEmpty(l))
//					return 0l;
//				return l.get(0);
//			}
//		});
//		return ((Long)res).longValue()>0;
		
	}
	
	public <T> Integer getId(Class<T> c,final String paramName, final Object paramValue)
	{
		

		final String queryString = "select c.id from "+c.getName()+" c where c."+paramName+"=:"+paramName;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery(queryString);
				q.setParameter(paramName, paramValue);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return null;
				if(l.size()>1)
					return ua.server.base.util.BaseConstant.NOT_UNIQUE_RESULT;
				return l.get(0);
			}
		});
		return ((Integer)res).intValue();
	}
	
	
	public long countByQuery(String query) {
		final String queryString = query;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT count(*) " + queryString);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0l;
				return l.get(0);
			}
		});
		return ((Long) res).longValue();
	}

	public long countByQuery(String query, Object... parameters) {
		final String queryString = query;
		final Object[] parametersArray = parameters;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT count(*) " + queryString);
				setParameters(q, parametersArray);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0l;
				return l.get(0);
			}
		});
		return ((Long)res).longValue();
	}	
	
	public double min(String attribute, String query,Object...parameters ) {
		final String queryString = query;
		final String minAttribute = attribute;
		final Object[] parametersArray = parameters;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT cast(min("+minAttribute+") as double) " + queryString);
				setParameters(q,parametersArray);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0d;
				return l.get(0);
			}
		});
		return (Double) res;
//		BigDecimal rr = (BigDecimal) res;
//		return rr.longValue();
//		return ((Long)res).longValue();
	}	
	
	public double max(String attribute, String query,Object...parameters ) {
		final String queryString = query;
		final String minAttribute = attribute;
		final Object[] parametersArray = parameters;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT  cast(max("+minAttribute+") as double) " + queryString);
				setParameters(q,parametersArray);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0d;
				return l.get(0);
			}
		});
		if(res ==null)
			return 0d;
//		if(res instanceof String)
//			return Double.valueOf((String)res).doubleValue();
		return  (Double)res;
//		if(res instanceof String)
//			return Long.valueOf((String)res).longValue();
//		return ((Long)res).longValue();
	}	

	public <T> Object max(Class<T> cls, String attribute) {
		final Class<T> clazz = cls;
		final String maxAttribute = attribute;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT max(h." + maxAttribute
						+ ") FROM " + clazz.getName() + " h");
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0;
				return l.get(0);
			}
		});
		return res;
	}

	public double sum(String attribute,String query,Object... parameters) {
		final String maxAttribute = attribute;
		final String queryStr = query;
		final Object[] parametersArray = parameters;
		Object res = getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object  doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("SELECT cast(SUM(" + maxAttribute + ") as double) " + queryStr); 
				setParameters(q, parametersArray);
				List<?> l=q.list();
				if(CollectionUtil.isEmpty(l))
					return 0d;
				return l.get(0);
			}
		});
		return (Double) res;
	}

	public <TEntity extends IEntity> Integer removeAll(Class<TEntity> cls) {
		final Class<?> clazz = cls;
		return (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery("DELETE FROM " + clazz.getName()+ " h");
				return q.executeUpdate();
			}
		});
	}

	public Integer removeByQuery(String query, Object... parameters) {
		final String queryString = query;
		final Object[] parametersArray = parameters;

		return (Integer)getHibernateTemplate().execute(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws PersistenceException {
				Query q = session.createQuery(queryString);
				setParameters(q, parametersArray);
				return q.executeUpdate();				
			}
		});
	}
	
	protected void setParameters(Query q, Object[] values) {
		if (values == null)
			return;
		for (int i = 0; i < values.length; i++) {
			q.setParameter(i , values[i]);
		}
	}
	protected void setParameters(Query q, Object[] values, Type[] types) {
		if (values == null)
			return;
		for (int i = 0; i < values.length; i++) {
			q.setParameter(i , values[i], types[i]);
		}
	}
	
	
	
}
