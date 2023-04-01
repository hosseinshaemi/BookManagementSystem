package com.bookmanager;

import com.bookmanager.database.Repository;
import com.bookmanager.models.Book;
import com.bookmanager.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private Button addbookBtn;

    @FXML
    private TextField authorFld;

    @FXML
    private TextField isbnFld;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField pubdateFld;

    private Repository repository = new Repository();

    @FXML
    public void AddBookHandler(ActionEvent event) {
        Book b = new Book();

        String bookName = nameFld.getText();
        String authorName = authorFld.getText();
        String isbn = isbnFld.getText();
        String pubdate = pubdateFld.getText();

        nameFld.clear();
        authorFld.clear();
        isbnFld.clear();
        pubdateFld.clear();

        b.setName(bookName);
        b.setAuthor(authorName);
        b.setIsbn(isbn);
        b.setPublishDate(pubdate);

        try {
            repository.AddBook(b);
            Utils.CreateAlert(Alert.AlertType.CONFIRMATION, "Success", bookName, "Successfully added").showAndWait();
        } catch (Exception e) {
            Utils.CreateAlert(Alert.AlertType.ERROR, "Duplicate", "Duplicate input", "This book is exist")
                    .showAndWait();
            e.printStackTrace();
        }
    }

}
