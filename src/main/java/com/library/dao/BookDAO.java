package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Ajouter un nouveau livre dans la base de données
    public void add(Book book) {
        String sql = "INSERT INTO books (title, author, publisher, year ,isAvailable) VALUES (?, ?, ?, ?,?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setBoolean(5, book.isAvailable());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Livre inséré avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    // Récupérer un livre par son ISBN
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Book book = null;
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                book.setAvailable(resultSet.getBoolean("isAvailable"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du livre : " + e.getMessage());
        }
        
        return book;
    }
    
    // Récupérer tous les livres
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
             
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                book.setAvailable(resultSet.getBoolean("isAvailable"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
        
        return books;
    }

    // Méthode pour mettre à jour un livre
    public void update(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ?, isAvailable = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setBoolean(5, book.isAvailable());
            statement.setInt(6, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un livre
    public void delete(int id) {
        String query = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
