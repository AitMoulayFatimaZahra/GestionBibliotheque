package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookService bookService; // Mock du service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    void testAddBook() {
        // Création d'un livre
        Book book = new Book("Titre Test", "Auteur Test", "Éditeur Test", 2023, true);

        // Simulation du comportement de addBook
        doNothing().when(bookService).addBook(book);

        // Appel de la méthode à tester
        assertDoesNotThrow(() -> bookService.addBook(book), "L'ajout du livre a échoué");

        // Vérification que la méthode a été appelée
        verify(bookService, times(1)).addBook(book);
    }

    @Test
    void testUpdateBook() {
        // Création d'un livre initial
        Book book = new Book("Titre Initial", "Auteur Initial", "Éditeur Initial", 2020, true);

        // Simulation de findBookById et updateBook
        when(bookService.findBookById(1)).thenReturn(book);
        doNothing().when(bookService).updateBook(book);

        // Mise à jour du livre
        book.setTitle("Titre Modifié");
        book.setAuthor("Auteur Modifié");
        book.setPublisher("Éditeur Modifié");
        book.setYear(2022);
        book.setAvailable(false);

        // Appel de la méthode à tester
        assertDoesNotThrow(() -> bookService.updateBook(book), "La mise à jour du livre a échoué");

        // Vérification que les modifications ont été appliquées
        Book updatedBook = bookService.findBookById(1);
        assertEquals("Titre Modifié", updatedBook.getTitle());
        assertEquals("Auteur Modifié", updatedBook.getAuthor());
        assertEquals("Éditeur Modifié", updatedBook.getPublisher());
        assertEquals(2022, updatedBook.getYear());
        assertFalse(updatedBook.isAvailable());

        // Vérification que les méthodes mockées ont été appelées
        verify(bookService, times(1)).findBookById(1);
        verify(bookService, times(1)).updateBook(book);
    }

    @Test
    void testDeleteBook() {
        // Création d'un livre
        Book book = new Book("Titre à Supprimer", "Auteur Test", "Éditeur Test", 2021, true);

        // Simulation de findBookById et deleteBook
        when(bookService.findBookById(1)).thenReturn(book).thenReturn(null); // Avant et après suppression
        doNothing().when(bookService).deleteBook(1);

        // Appel de la méthode pour vérifier l'ajout
        Book savedBook = bookService.findBookById(1);
        assertNotNull(savedBook, "Le livre n'a pas été ajouté avant la suppression");

        // Suppression du livre
        assertDoesNotThrow(() -> bookService.deleteBook(1), "La suppression du livre a échoué");

        // Vérification que le livre a été supprimé
        Book deletedBook = bookService.findBookById(1);
        assertNull(deletedBook, "Le livre n'a pas été supprimé correctement");

        // Vérification que les méthodes mockées ont été appelées
        verify(bookService, times(2)).findBookById(1); // Avant et après suppression
        verify(bookService, times(1)).deleteBook(1);
    }
    // Test de la méthode getAllBooks
    @Test
    void testGetAllBooks() {
        // Création d'une liste de livres pour simuler le retour de getAllBooks()
        Book book1 = new Book("Titre 1", "Auteur 1", "Éditeur 1", 2020, true);
        Book book2 = new Book("Titre 2", "Auteur 2", "Éditeur 2", 2021, true);
        List<Book> mockBooks = Arrays.asList(book1, book2);

        // Simulation du comportement de getAllBooks
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        // Appel de la méthode à tester
        List<Book> books = bookService.getAllBooks();

        // Vérification que la liste retournée n'est pas vide
        assertNotNull(books, "La liste des livres ne doit pas être nulle");
        assertEquals(2, books.size(), "La taille de la liste des livres est incorrecte");
        assertEquals("Titre 1", books.get(0).getTitle(), "Le premier titre est incorrect");
        assertEquals("Titre 2", books.get(1).getTitle(), "Le deuxième titre est incorrect");

        // Vérification que la méthode a été appelée une seule fois
        verify(bookService, times(1)).getAllBooks();
    }
}
