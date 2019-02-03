package web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.uml.Process;
import model.uml.Science;
import model.uml.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import web.model.CRUD;
import web.model.KeyPair;

/**
 * Десериализацией создаём не процесс, а, так сказать, образ процесса: парный
 * ключ плюс оценки
 * 
 * @author Arsen Pan
 */
public class Deserialization {
	private static KeyPair deserialize(JSONObject process) {
		Science course = Science.valueOf(process.getString("course"));
		Student student = Student.getById(process.getInt("student"));
		return new KeyPair(course, student);
	}

	private static List<Integer> deserializeMarks(JSONArray marks) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < marks.length(); i++) {
			result.add(marks.getInt(i));
		}
		return result;
	}

	private static HashMap<KeyPair, List<Integer>> deserialize(
			JSONArray processes) {
		HashMap<KeyPair, List<Integer>> result = new HashMap<KeyPair, List<Integer>>();
		for (int i = 0; i < processes.length(); i++) {
			JSONObject process = (JSONObject) processes.get(i);
			result.put(deserialize(process), deserializeMarks(process
					.getJSONArray("marks")));
		}
		return result;
	}

	/**
	 * С помощью CRUD-методов попробуем минимальным количеством операций
	 * привести состояние модели к состоянию processes
	 * 
	 * @param processes
	 */
	static void applyJSON(JSONArray processes) {
		HashMap<KeyPair, List<Integer>> desiredState = deserialize(processes);
		Collection<KeyPair> keypairs = desiredState.keySet();
		// Для начала поудаляем лишние процессы
		ArrayList<Process> ourProcesses = Student.getAllProcesses();
		for (Process process : ourProcesses) {
			KeyPair keypair = new KeyPair(process.getCourse(), process
					.getStudent());
			if (!keypairs.contains(keypair)) {
				CRUD.deleteProcess(keypair);
			}
		}
		// Теперь добавим недостающие
		for (KeyPair keypair : keypairs) {
			if (keypair.getStudent().isNotEnvolved(keypair.getCourse())) {
				CRUD.createProcess(keypair);
			}
		}
		// Теперь проапдейтим все процессы
		for (KeyPair keypair : keypairs) {
			CRUD.updateProcess(desiredState.get(keypair), keypair);
		}
	}
}
