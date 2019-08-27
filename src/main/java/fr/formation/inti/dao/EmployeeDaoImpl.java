package fr.formation.inti.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.utils.HibernateUtils;

/**
 * Notre classe EmployeeDaoImpl suit le modèle de conception Singleton qui
 * garantit qu'une seule instance de cette classe sera créée dans l'application.
 * Lors de la première création de classe, la méthode getEntityManager () est
 * chargée de créer une instance de EntityManager.
 * 
 * @author 
 *
 */
public class EmployeeDaoImpl implements IEmployeeDao {
	private Session currentSession;
	private Transaction currentTransaction;
	
	public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

	@Override
	public List<Employee> getAll() {
		Query q = getCurrentSession().createQuery("FROM " + Employee.class.getName());		
		List<Employee> employees = q.list();
		return employees;
	}

	@Override
	public void persist(Employee e) {
		getCurrentSession().persist(e);
	}

	@Override
	public Integer save(Employee e) {
		
		return (Integer) getCurrentSession().save(e);
	}

	@Override
	public void update(Employee e) {
		getCurrentSession().update(e);
	}

	@Override
	public void delete(Employee e) {
		getCurrentSession().delete(e);
	}

	@Override
	public Employee findById(Integer id) {
		Employee emp = (Employee) getCurrentSession().get(Employee.class, id);
		return emp;
	}

	@Override
	public void deleteByID(Integer id) {
		try {
			Employee e = findById(id);
			delete(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void close() {
		HibernateUtils.shutdown();
	}

}
