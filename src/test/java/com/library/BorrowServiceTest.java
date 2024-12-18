package com.library;

import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.dao.BookDAO;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.model.Book;
import com.library.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class BorrowServiceTest {

    @Mock
    private BorrowDAO borrowDAO;

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private BookDAO bookDAO;

    private BorrowService borrowService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        borrowService = new BorrowService(borrowDAO);
    }

    @Test
    public void testBorrowBook() {
        // Créer un objet Student mock
        Student student = new Student(1, "John Doe", "john.doe@example.com");

        // Créer un objet Book mock
        Book book = new Book("Java Programming", "John Smith","william swariz", 2004, true);

        // Créer un objet Borrow mock
        Borrow borrow = new Borrow(1, student, book, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000));

        // Appeler la méthode borrowBook
        borrowService.borrowBook(borrow);

        // Vérifier si la méthode save du BorrowDAO a été appelée une fois
        verify(borrowDAO, times(1)).save(borrow);
    }

    @Test
    public void testGetAllBorrows() {
        // Créer des objets Student et Book mock
        Student student = new Student(1, "John Doe", "john.doe@example.com");
        Book book = new Book( "Java Programming", "John Smith","william swariz", 2004, true);

        // Créer un objet Borrow mock
        Borrow borrow = new Borrow(1, student, book, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000));

        // Simuler l'appel de la méthode getAllBorrows
        when(borrowDAO.getAllBorrows()).thenReturn(List.of(borrow));

        // Appeler la méthode getAllBorrows et vérifier le résultat
        List<Borrow> borrows = borrowService.getAllBorrows();
        verify(borrowDAO, times(1)).getAllBorrows();
        assert borrows.size() == 1;
        assert borrows.get(0).getStudent().getName().equals("John Doe");
    }
}
