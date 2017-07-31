package sev.alvioneurope.coursemodel.impl;

//Центр хеш-ключей
interface DAOkeys {
	int prime = 31;

	default int hashCodeProfessor(String name, String address) {
		return getKeyProfessor(name, address);
	}
	
	static int getKeyProfessor(String name, String address) {
		int result = 1;
		result = prime * result + name.hashCode();
		result = prime * result + address.hashCode();
		return result;
	}

	default int hashCodeStudent(Integer bookNum) {
		return getKeyStudent(bookNum);
	}

	static int getKeyStudent(Integer bookNum) {
		return  prime * bookNum;
	}

	default int hashCodeCourse(int number) {
		return getKeyCourse(number);
	}

	static int getKeyCourse(int number) {
		return  prime * number;
	}

	default int hashCodeSession(int keyCourse, int keyStudent) {
		return getKeyPrimary(keyCourse, keyStudent);
	}

	static int getKeyPrimary(int keyCourse, int keyStudent) {
		int result = 1;
		result = prime * result + keyCourse;
		result = prime * result + keyStudent;
		return result;
	}

	static int keyDEPO(int keyHash, Class<?> clazz) {
		return prime * keyHash + clazz.getName().hashCode();
	}
}
