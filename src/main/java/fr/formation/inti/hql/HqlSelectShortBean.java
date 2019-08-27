package fr.formation.inti.hql;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.entities.ShortEmpInfo;
import fr.formation.inti.utils.HibernateUtils;
 
/*
 * 
 */
public class HqlSelectShortBean {
 
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
 
        Session session = factory.getCurrentSession();
 
        try {
            session.getTransaction().begin();
  
            // Using constructor of ShortEmpInfo
            String sql = "Select new " + ShortEmpInfo.class.getName()
                    + "(e.empId, e.firstName, e.lastName)" + " from "
                    + Employee.class.getName() + " e ";
 
            Query query = session.createQuery(sql);
  
             
            // Execute query.
            // Get a List of ShortEmpInfo
            List<ShortEmpInfo> employees = query.list();
 
            for (ShortEmpInfo emp : employees) {
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