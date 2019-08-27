package fr.formation.inti.hql;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import fr.formation.inti.entities.Employee;
import fr.formation.inti.utils.HibernateUtils;
 
/*
 * Select employee with name of class Employee
 * with department 1 (choose)
 */
public class HqlSelectExample2 {
 
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
 
        Session session = factory.getCurrentSession();
 
        try {
 
            // All the action with DB via Hibernate
            // must be located in one transaction
            // Start Transaction.                
            session.getTransaction().begin();
             
            // Create an HQL statement, query the object.
            // HQL with parameters.            
            // Equivalent to the SQL statement:
            // Select e.* from EMPLOYEE e cross join DEPARTMENT d
            // where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;        
            String sql = "Select e from " + Employee.class.getName() + " e "
                    + " where e.department.deptId=:deptId ";
  
            // Create query object.
            Query query = session.createQuery(sql);
 
            query.setParameter("deptId", 1);
 
  
            // Execute query.
            List<Employee> employees = query.list();
 
            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpId() + " : "
                        + emp.getFirstName());
            }
  
            // Commit data
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }
}