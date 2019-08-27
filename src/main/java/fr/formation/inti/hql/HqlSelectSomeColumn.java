package fr.formation.inti.hql;
 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.utils.HibernateUtils;
 /*
  * select all employee but with class Employee
  */
public class HqlSelectSomeColumn {
 
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
 
        Session session = factory.getCurrentSession();
 
        try {
            session.getTransaction().begin();
  
            // Query some columns.
            String sql = "Select e.empId, e.firstName, e.lastName from "
                    + Employee.class.getName() + " e ";
 
            Query query = session.createQuery(sql);
  
            // Execute Query.
            // Get the array of Object
            List<Object[]> datas = query.list();
 
            for (Object[] emp : datas) {
                System.out.println("Emp Id: " + emp[0]);
                System.out.println(" Emp FistName: " + emp[1]);
                System.out.println(" Emp LastName: " + emp[2]);
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