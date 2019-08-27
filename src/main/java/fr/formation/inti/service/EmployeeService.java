package fr.formation.inti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.formation.inti.dao.EmployeeDaoImpl;
import fr.formation.inti.dao.IEmployeeDao;
import fr.formation.inti.entities.Employee;

@Component("service")
public class EmployeeService {
	
	@Autowired
	private static IEmployeeDao dao;
	
	
	
//	public EmployeeService() {
//		dao = new EmployeeDaoImpl();
//	}
	
	@Autowired
	@Qualifier("session")
	public void persist(Employee e) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(e);
        dao.closeCurrentSessionwithTransaction();
    }
	
	public Integer save(Employee e) {
		Integer id;
        dao.openCurrentSessionwithTransaction();
        id = dao.save(e);
        dao.closeCurrentSessionwithTransaction();
        return id;
    }
    public void update(Employee e) {
        dao.openCurrentSessionwithTransaction();
        dao.update(e);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public Employee findById(Integer id) {
        dao.openCurrentSession();
        Employee emp = dao.findById(id);
        dao.closeCurrentSession();
        return emp;
    }
 
    public void delete(Integer id) {
        dao.openCurrentSessionwithTransaction();
        Employee emp = dao.findById(id);
        dao.delete(emp);
        dao.closeCurrentSessionwithTransaction();
    }
    public List<Employee> findAll() {
        dao.openCurrentSession();
        List<Employee> employees = dao.getAll();
        dao.closeCurrentSession();
        return employees;
    }
 
    
 
    public EmployeeDaoImpl EmployeeDao() {
        return dao;
    }
}
