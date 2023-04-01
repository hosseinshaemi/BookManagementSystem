package com.bookmanager;

import java.net.URL;
import java.util.ResourceBundle;

import com.bookmanager.database.DataHolder;
import com.bookmanager.database.Repository;
import com.bookmanager.models.Book;
import com.bookmanager.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateBookController implements Initializable {

    @FXML
    private TextField authorFld;

    @FXML
    private TextField isbnFld;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField pubdateFld;

    @FXML
    private Button updatebookBtn;

    private Repository repository = new Repository();

    @FXML
    void UpdateBookHandler(ActionEvent event) {
        Book book = DataHolder.getBook();
        book.setName(nameFld.getText());
        book.setAuthor(authorFld.getText());
        book.setIsbn(isbnFld.getText());
        book.setPublishDate(pubdateFld.getText());

        try {
            repository.UpdateBook(book);
            Utils.CreateAlert(Alert.AlertType.CONFIRMATION, "Success", book.getName(), "Successfully updated")
                    .showAndWait();
        } catch (Exception e) {
            Utils.CreateAlert(Alert.AlertType.ERROR, "Update", "Update Error", "Cannnot Update")
                    .showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Book book = DataHolder.getBook();
        authorFld.setText(book.getAuthor());
        isbnFld.setText(book.getIsbn());
        nameFld.setText(book.getName());
        pubdateFld.setText(book.getPublishDate());
    }

}
