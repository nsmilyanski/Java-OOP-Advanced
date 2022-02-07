package ABSTRACTION.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {

    private Map<String, Student> studentMap;

    public StudentSystem() {
        this.studentMap = new HashMap<>();
    }

    public void ParseCommand(String[]args) {

        String command = args[0];

        switch (command){
            case "Create":
                createStudents(args);
                break;
            default:
                printStudent(args);
                break;
        }

    }
    public void printStudent(String[] array){
        String name = array[1];
        if (studentMap.containsKey(name)) {
            Student student = studentMap.get(name);
            String view = String.format("%s is %s years old.",student.getName(),student.getAge());

            if (student.getGrade() >= 5.00) {
                view += " Excellent student.";
            }
            else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
                view += " Average student.";
            }
            else {
                view += " Very nice person.";
            }

            System.out.println(view);
        }
    }

    public void createStudents(String[] array){
        String name = array[1];
        int age = Integer.parseInt(array[2]);
        double grade =Double.parseDouble(array[3]);

        Student student = new Student(name, age, grade);
        studentMap.putIfAbsent(name, student);

    }
}
