package com.arthur.studies.json;

import com.arthur.studies.model.Course;
import com.arthur.studies.model.PassingCourse;
import com.arthur.studies.model.Professor;
import com.arthur.studies.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    public void writeModel(String fileName, Object... modelObjects) {
        // if file name is incorrect
        if ((fileName == null) || !fileName.endsWith(".json")) {
            System.out.println("Incorrect file format -> it must be something like \"/path/to/the/file/test.json\"");
            return;
        }

        try (FileWriter out = new FileWriter(fileName)) {
            out.write("{");
            int commaCount = 0;
            // define ID's for key-names in JSON file
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
            // if we have pairs (key-value) in JSON file
            if (commaCount != 0) {
                out.write("\n");
            }
            out.write("}");
        } catch (IOException e) {
            // if we get IOException then delete the stub of the file
            File file = new File(fileName);
            file.delete();
        }
    }

    public List<Object> readModel(String fileName) {
        List<Object> result = new ArrayList<>();
        // if file name is incorrect then return empty list
        if ((fileName == null) || !fileName.endsWith(".json")) {
            return result;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            // reads JSON file by lines
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

                // reads serialized object
                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteArr))) {
                    result.add(in.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // NOP
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
        // if we have no array or array is empty
        if (arr == null || arr.length == 0) {
            return "";
        }
        // if array is not empty
        StringBuilder strArr = new StringBuilder();
        for (byte b : arr) {
            strArr.append(b).append(",");
        }
        // delete extra comma at the end of string
        strArr.deleteCharAt(strArr.length() - 1);
        return strArr.toString();
    }
}
