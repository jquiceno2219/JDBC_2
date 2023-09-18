package org.example;

import org.example.domain.models.Student;
import org.example.mapping.dtos.StudentDTO;
import org.example.mapping.dtos.TeacherDTO;
import org.example.mapping.mappers.TeacherMapper;
import org.example.repository.Repository;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.conexion.ConexionBD;
import org.example.domain.models.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Connection conn = ConexionBD.getInstance()) {
            TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();
            StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
            String option = "0";
            do{

                System.out.println("\n Menu:" +
                        "\n1. Teachers" +
                        "\n2. Students" +
                        "\n3. Subjects" +
                        "\n4. Grades" +
                        "\n5. Exit");
                option = scan.next();
                switch(option){
                    case "1": {
                        String optionTeacher = "0";
                        do{
                            System.out.println("\n Menu:" +
                                    "\n1. Teachers List" +
                                    "\n2. Teachers Add" +
                                    "\n3. Teachers Update" +
                                    "\n4. Teachers Delete" +
                                    "\n5. Teachers ById" +
                                    "\n6. Exit");
                            optionTeacher = scan.next();
                            switch(optionTeacher) {
                                case "1": {
                                    teacherRepository.list().forEach(System.out::println);
                                    break;
                                }
                                case "2": {
                                    System.out.println("Add teacher");
                                    System.out.println("Name");
                                    String nameS = scan.next();
                                    System.out.println("Email");
                                    String emailS = scan.next();

                                    Teacher teacher = Teacher.builder()
                                            .name(nameS)
                                            .email(emailS).build();
                                    teacherRepository.save(teacher);
                                    System.out.println("Teacher saved");
                                    break;
                                }
                                case "3": {
                                    System.out.println("Update teacher");
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    System.out.println("Name");
                                    String nameS = scan.next();
                                    System.out.println("Email");
                                    String emailS = scan.next();
                                    Teacher teacher = Teacher.builder()
                                            .id(idS)
                                            .name(nameS)
                                            .email(emailS).build();
                                    teacherRepository.save(teacher);
                                    System.out.println("Teacher updated");
                                    break;
                                }
                                case "4": {
                                    System.out.println("Delete");
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    teacherRepository.delete(idS);
                                    System.out.println("Teacher deleted");
                                    break;
                                }
                                case "5": {
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    System.out.println(teacherRepository.byId(idS));
                                    break;
                                }
                            }
                        }while(!optionTeacher.equals("6"));
                        break;
                    }
                    case "2": {
                        String optionStudent = "0";
                        do{
                            System.out.println("\n Menu:" +
                                    "\n1. Teachers List" +
                                    "\n2. Teachers Add" +
                                    "\n3. Teachers Update" +
                                    "\n4. Teachers Delete" +
                                    "\n5. Teachers ById" +
                                    "\n6. Exit");
                            optionStudent = scan.next();
                            switch(optionStudent) {
                                case "1": {
                                    studentRepository.list().forEach(System.out::println);
                                    break;
                                }
                                case "2": {
                                    System.out.println("Add student");
                                    System.out.println("Name");
                                    String nameS = scan.next();
                                    System.out.println("Email");
                                    String emailS = scan.next();
                                    System.out.println("Semester");
                                    String semesterS = scan.next();
                                    Student student = Student.builder()
                                            .name(nameS)
                                            .email(emailS)
                                            .semester(semesterS).build();
                                    studentRepository.save(StudentDTO);
                                    System.out.println("Student saved");
                                    break;
                                }
                                case "3": {
                                    System.out.println("Update teacher");
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    System.out.println("Name");
                                    String nameS = scan.next();
                                    System.out.println("Email");
                                    String emailS = scan.next();
                                    Teacher teacher = Teacher.builder()
                                            .id(idS)
                                            .name(nameS)
                                            .email(emailS).build();
                                    teacherRepository.save(teacher);
                                    System.out.println("Teacher updated");
                                    break;
                                }
                                case "4": {
                                    System.out.println("Delete");
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    teacherRepository.delete(idS);
                                    break;
                                }
                                case "5": {
                                    System.out.println("Id");
                                    Long idS = scan.nextLong();
                                    System.out.println(teacherRepository.byId(idS));
                                    break;
                                }
                            }
                        }while(!optionStudent.equals("6"));
                        break;
                    }
                    case "3": {

                        break;
                    }
                    case "4":{

                        break;
                    }
                }
            }while (!option.equals("5"));


        } catch (Exception e) {

        }
    }
}