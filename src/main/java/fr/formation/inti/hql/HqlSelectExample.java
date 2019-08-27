package fr.formation.inti.hql;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.utils.HibernateUtils;
 
public class HqlSelectExample {
	/*
	 * Select all employee with name of class Employee
	 */
 
   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
 
       Session session = factory.getCurrentSession();
 
       try {
            
           // All the action with DB via Hibernate
           // must be located in one transaction.
           // Start Transaction.            
           session.getTransaction().begin();
 
    
            
           // Create an HQL statement, query the object.
           // Equivalent to the SQL statement:
           // Select e.* from EMPLOYEE e order by e.EMP_ID
           String sql = "Select e from " + Employee.class.getName() + " e "
                   + " order by e.empId ";
 
   
           // Create Query object.
           Query query = session.createQuery(sql);
 
    
           // Execute query.
           List<Employee> employees = query.list();
 
           for (Employee emp : employees) {
               System.out.println("Emp: " + emp.getFirstName() + " : "
                       + emp.getLastName());
           }
  
           // Commit data.
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           // Rollback in case of an error occurred.
           session.getTransaction().rollback();
       }
   }
    
}