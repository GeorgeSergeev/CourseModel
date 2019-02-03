package model;

import java.util.ArrayList;

import model.uml.ProfessorException;
import model.uml.Professor;
import model.uml.Science;
import model.uml.Student;

/**
 * Из-за того, что в Java энумерация не может наследоваться,
 * я из нашей троицы классов констант Student, Professor, Science
 * только класс Science смог сделать энумерацией.
 * Professor и Student ведь отнаследованы от класса Man.
 * Иннициализацию констант мне приходится производить здесь
 * 
 * @author Arsen Pan
 *
 */

public class Model {
	static {
		new Student("Smith John", "Beverly Hills, Lenin St. 7", "+7(978)8698438",
				"smith@inbox.ru", 4.3f);
		new Student("Miller Helene", "London, Abbey Road 1", "+38(050)8698439",
				"zaya@yahoo.com", 3.1f);
		new Student("Feynman Richard", "New York, Nothern 9", "+1(978)1098409",
				"richard@yandex.ru", 3.5f);
		new Student("Stone Piter", "St. Petersburg, Astronaut Armstrong St. 2",
				"+7(978)1658439", "piter@yandex.ru", 4.1f);
		
		ArrayList<Professor> professors = new ArrayList<Professor>();
		try {
			professors.add(new Professor("Albert Einstein",
					"Karl Marx Stadt, Liberty St. 38", "+38(097)8909091", 10,
					Science.COURSE1));
			professors.add(new Professor("Von Braun",
					"Moscow, Red Square 16", "+7(978)8709001", 18,
					Science.COURSE3));
			professors.add(new Professor("Marie Curie",
					"Simferopol, Crimean 2", "+1(978)8001091", 8,
					Science.COURSE4));
		} catch (ProfessorException e) {
			e.printStackTrace();
		}
	}
}
