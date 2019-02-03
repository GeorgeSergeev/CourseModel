package web.model;

import java.util.List;

import model.uml.Process;

/**
 * CRUD-методами € пользуюсь только в web-приложении,
 * потому что только там (при приЄме желаемого состо€ни€)
 * возникает необходимость пересмотреть сразу весь список
 * процессов. ƒесктопное приложение работает в непосредственной
 * св€зи с моделью, поэтому такого, чтобы оно в offline-режиме
 * сумело кардинально изменить состо€ние, не бывает
 * @author Arsen Pan
 */

public class CRUD {

	public static void createProcess(KeyPair keypair) {
		keypair.getCourse().addStudent(keypair.getStudent());
	}

	/**
	 * »звините, что им€ не конвенциональное (не начинающеес€ на get), € пошЄл
	 * на это ради того, чтобы подчеркнуть идеологию CRUD
	 * 
	 * @param course
	 * @param student
	 * @return
	 */
	public static Process readProcess(KeyPair keypair) {
		return keypair.getCourse().getProcessByStudent(keypair.getStudent());
	}

	public static void updateProcess(List<Integer> marks, KeyPair keypair) {
		List<Integer> ourMarks = readProcess(keypair).marks;
		ourMarks.clear();
		ourMarks.addAll(marks);
	}

	public static void deleteProcess(KeyPair keypair) {
		keypair.getCourse().deleteStudent(keypair.getStudent());
	}

}
