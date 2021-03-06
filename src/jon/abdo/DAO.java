package jon.abdo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class DAO {
	private static SessionFactory factory;

	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// ;
		}

		Configuration configuration = new Configuration();

		// hibernate configuration file
		configuration.configure("hibernate.cfg.xml");

		// setup file for User class
		configuration.addResource("user.hbm.xml");

		// Since version 4.x, service registry is being used
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// Create session factory instance
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static boolean addUser(User user) {
		if (factory == null)
			setupFactory();
		
		// Get current session
		Session hibernateSession = factory.openSession();

		// Begin transaction
		hibernateSession.getTransaction().begin();

		// save this specific record
		int i = (Integer)hibernateSession.save(user);

		// Commit transaction
		hibernateSession.getTransaction().commit();

		hibernateSession.close();

		return true;
	}
	
	public static boolean containsUser(User user){
		if (factory == null)
			setupFactory();
		
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		
		String username="'"+user.getUsername()+"'";
		String hql = "FROM User WHERE username = "+username;
		Query query = hibernateSession.createQuery(hql);
		List results = query.list();
		
		if(results.isEmpty())
			return false;
		
		return true;
	}
	
	public static boolean checkLogin(User user){
		if (factory == null)
			setupFactory();
		
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		
		String username="'"+user.getUsername()+"'";
		String hql = "SELECT password FROM User WHERE username = "+username;
		List query = hibernateSession.createQuery(hql).list();

		if(!query.isEmpty() && query.get(0).equals(user.getPassword()))
			return true;
		
		user.resetPassword();
		return false;
		
	}

	public static List<User> getAllUsers() {
		if (factory == null)
			setupFactory();
		
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();

		// deprecated method & unsafe cast
		List<User> users = hibernateSession.createQuery("FROM User").list();

		hibernateSession.getTransaction().commit();
		hibernateSession.close();

		return users;
	}

	public static void deleteUser(int id) {
		Session hibernateSession = factory.getCurrentSession();
		hibernateSession.beginTransaction();
		
		User user = new User();
		user.setId(id);
		hibernateSession.delete(user);
		
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
	}
}