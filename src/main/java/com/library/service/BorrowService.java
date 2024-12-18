
package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

import java.util.List;

public class BorrowService {

    private BorrowDAO borrowDAO;

    // Constructeur avec BorrowDAO
    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }

    // Méthode pour emprunter un livre
    public void borrowBook(Borrow borrow) {
        // Sauvegarde de l'emprunt dans la base de données
        borrowDAO.save(borrow);
    }

    // Afficher les emprunts (méthode fictive, à adapter)
    public void displayBorrows() {
        System.out.println("Liste des emprunts...");
        // Afficher les emprunts enregistrés (adapté selon votre DAO)
    }

    public List<Borrow> getAllBorrows() {
        try {
            // Appeler la méthode du DAO pour récupérer les emprunts
            List<Borrow> borrows = borrowDAO.getAllBorrows();
            System.out.println("Emprunts récupérés avec succès.");
            return borrows;
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des emprunts : " + e.getMessage());
            return null;
        }
    }
}
