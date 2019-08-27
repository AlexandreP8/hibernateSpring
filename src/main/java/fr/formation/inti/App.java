package fr.formation.inti;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.inti.service.EmployeeService;

/**
 * Hello world!
 *
 */
public class App {
	private static ClassPathXmlApplicationContext appContext;

	public static void main(final String[] args) {
		appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		try {
			EmployeeService service = appContext.getBean(EmployeeService.class);
			service.findAll();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
