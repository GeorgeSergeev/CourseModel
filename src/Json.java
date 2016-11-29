import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Поскольку объект "Студент" хранит в себе список курсов, а объект "Курс" хранит список студентов - возникают сложности с использованием
//библиотеки json от google. Циличные ссылки вызывают переполнение стека в процессе серилизации и данная проблема решается написанием собственного адаптора
//для каждого типа объектов. Решение приведенное ниже более универсально.
public class Json {

    public  void writeJson(String fileName, Object... modelObjects) {
        // if file name is incorrect
        if ((fileName == null) || !fileName.endsWith(".json")) {
            System.out.println("Расширение файла должно быть .json");
            return;
        }

        try (FileWriter out = new FileWriter(fileName)) {
            out.write("{");
            int commaCount = 0;
            int studentId = 0;
            int passingId = 0;
            int courseId = 0;
            int professorId = 0;

            for (Object obj : modelObjects) {
                if (obj == null) {
                    continue;
                }
                ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
                ObjectOutputStream objOut = new ObjectOutputStream(byteArr);
                if (obj instanceof Student) {
                    writeJsonLine(out, objOut, byteArr, obj, "student", ++studentId, commaCount++);
                } else if (obj instanceof PassingCourse) {
                    writeJsonLine(out, objOut, byteArr, obj, "passing", ++passingId, commaCount++);
                } else if (obj instanceof Course) {
                    writeJsonLine(out, objOut, byteArr, obj, "course", ++courseId, commaCount++);
                } else if (obj instanceof Professor) {
                    writeJsonLine(out, objOut, byteArr, obj, "professor", ++professorId, commaCount++);
                }
                objOut.close();
            }
            if (commaCount != 0) {
                out.write("\n");
            }
            out.write("}");
        } catch (IOException e) {
            // Если получаем исключение - удаляем файл
            File file = new File(fileName);
            file.delete();
        }
    }

    public List<Object> readJson(String fileName) {
        List<Object> result = new ArrayList<>();
        if ((fileName == null) || !fileName.endsWith(".json")) {
            return result;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\"");
                if (lineArr.length == 1) {
                    continue;
                }
                String[] bytes = lineArr[3].split(",");
                byte[] byteArr = new byte[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    byteArr[i] = Byte.parseByte(bytes[i]);
                }

                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteArr))) {
                    result.add(in.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }

        return result;
    }

    private void writeJsonLine(FileWriter out, ObjectOutputStream objOut, ByteArrayOutputStream byteArr, Object obj, String keyName, int id, int commaCount) throws IOException {
        writeComma(out, commaCount);
        out.write("\n    \"" + keyName + id + "\":\"");
        objOut.writeObject(obj);
        out.write(toString(byteArr.toByteArray()));
        out.write("\"");
    }

    private void writeComma(FileWriter out, int commaCount) throws IOException {
        if (commaCount != 0) {
            out.write(",");
        }
    }

    private String toString(byte[] arr) {
        // Если массива нет или он пустой
        if (arr == null || arr.length == 0) {
            return "";
        }
        // Если массив не пустой
        StringBuilder strArr = new StringBuilder();
        for (byte b : arr) {
            strArr.append(b).append(",");
        }
        // Удалить лишнюю запятую в конце строки
        strArr.deleteCharAt(strArr.length() - 1);
        return strArr.toString();
    }
}