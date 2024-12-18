package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentDAO studentDAO;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        // Mock de StudentDAO
        studentDAO = mock(StudentDAO.class);
        studentService = new StudentService(studentDAO);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student(1, "John Doe", "john@example.com");

        // Appeler la méthode de service
        studentService.addStudent(student);

        // Vérifier que la méthode addStudent du DAO est appelée une fois
        verify(studentDAO, times(1)).addStudent(student);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(1, "John Doe", "john@example.com");

        // Appeler la méthode de service
        studentService.updateStudent(student);

        // Vérifier que la méthode updateStudent du DAO est appelée une fois
        verify(studentDAO, times(1)).updateStudent(student);
    }

    @Test
    public void testDeleteStudent() {
        int studentId = 1;

        // Appeler la méthode de service
        studentService.deleteStudent(studentId);

        // Vérifier que la méthode deleteStudent du DAO est appelée une fois
        verify(studentDAO, times(1)).deleteStudent(studentId);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(1, "John Doe", "john@example.com"));
        mockStudents.add(new Student(2, "Jane Smith", "jane@example.com"));

        // Configurer le mock du DAO pour retourner la liste simulée
        when(studentDAO.getAllStudents()).thenReturn(mockStudents);

        // Appeler la méthode de service
        List<Student> students = studentService.getAllStudents();

        // Vérifier que la méthode getAllStudents du DAO est appelée une fois
        verify(studentDAO, times(1)).getAllStudents();

        // Vérifier que la liste retournée contient les étudiants attendus
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("Jane Smith", students.get(1).getName());
    }
}
