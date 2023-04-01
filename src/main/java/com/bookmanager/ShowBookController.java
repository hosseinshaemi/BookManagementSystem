package com.bookmanager;

import java.net.URL;
import java.util.ResourceBundle;

import com.bookmanager.database.DataHolder;
import com.bookmanager.models.Book;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ShowBookController implements Initializable {

    @FXML
    private Label authorLbl;

    @FXML
    private Label idLbl;

    @FXML
    private Label isbnLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label pubdateLbl;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Book book = DataHolder.getBook();
        
        idLbl.setText(Long.toString(book.getId()));
        nameLbl.setText(book.getName());
        authorLbl.setText(book.getAuthor());
        isbnLbl.setText(book.getIsbn());
        pubdateLbl.setText(book.getPublishDate());
    }

}
