package fr.formation.inti.hql;
 
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;
import fr.formation.inti.utils.HibernateUtils;
 
public class HqlSelectUniqueResult {
 
    public static Department getDepartment(Session session, Integer deptId) {
        String sql = "Select d from " + Department.class.getName() + " d "//
                + " where d.deptId= :deptId ";
        Query query = session.createQuery(sql);
        query.setParameter("deptId", deptId);
        return (Department) query.uniqueResult();
    }
    /**
     * Une requête récupère un résultat unique
     * @param session
     * @param empId
     * @return
     */
    public static Employee getEmployee(Session session, Integer empId) {
        String sql = "Select e from " + Employee.class.getName() + " e "//
                + " where e.empId= :empId ";
        Query query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee) query.uniqueResult();
    }
 
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
 
        Session session = factory.getCurrentSession();
 
        try {
            session.getTransaction().begin();
 
            Department dept = getDepartment(session, 1);
            Set<Employee> emps = dept.getEmployees();
 
            System.out.println("Dept Name: " + dept.getName());
            for (Employee emp : emps) {
                System.out.println(" Emp FistName: " + emp.getFirstName());
            }
 
            Employee emp = getEmployee(session, 5);
            System.out.println("Emp Name: " + emp.getFirstName());
 
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
     
}