package com.bookmanager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.bookmanager.database.DataHolder;
import com.bookmanager.database.Repository;
import com.bookmanager.models.Book;
import com.bookmanager.utils.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXMLController implements Initializable {

    @FXML
    private Button addBook;

    @FXML
    private TableView<Book> bookList;

    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, String> isbn;
    @FXML
    private TableColumn<Book, String> publishDate;

    @FXML
    private Button deleteBook;

    @FXML
    private Button showBook;

    @FXML
    private Label titleLbl;

    @FXML
    private Button updateBook;

    private Repository repository = new Repository();

    @FXML
    public void AddBookHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addbook.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Stage addbookWindow = new Stage();
        addbookWindow.resizableProperty().setValue(false);
        addbookWindow.setOnCloseRequest(e -> AddBookCloseHandler(e));
        addbookWindow.initModality(Modality.APPLICATION_MODAL);
        addbookWindow.setTitle("Add Book");
        addbookWindow.setScene(scene);
        addbookWindow.show();
    }

    @FXML
    public void DeleteBookHandler(ActionEvent event) {
        Book book = bookList.getSelectionModel().getSelectedItem();
        if (book == null) {
            Utils.CreateAlert(Alert.AlertType.ERROR, "Select Error", "Table Selection Error",
                    "There is nothing for removing").showAndWait();
            return;
        }
        try {
            repository.DeleteBook(book);
            FillTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ShowBookHandler(ActionEvent event) throws IOException {
        Book book = bookList.getSelectionModel().getSelectedItem();
        if (book == null) {
            Utils.CreateAlert(Alert.AlertType.ERROR, "Select Error", "Table Selection Error",
                    "Please select an item for showing").showAndWait();
            return;
        }
        DataHolder.setBook(book);

        Parent root = FXMLLoader.load(getClass().getResource("showbook.fxml"));

        Scene scene = new Scene(root);

        Stage showbookWindow = new Stage();
        showbookWindow.resizableProperty().setValue(false);
        showbookWindow.initModality(Modality.APPLICATION_MODAL);
        showbookWindow.setTitle("Show Book");
        showbookWindow.setScene(scene);
        showbookWindow.show();
    }

    @FXML
    public void UpdateBookHandler(ActionEvent event) throws IOException {
        Book book = bookList.getSelectionModel().getSelectedItem();
        if (book == null) {
            Utils.CreateAlert(Alert.AlertType.ERROR, "Select Error", "Table Selection Error",
                    "Please select an item for showing").showAndWait();
            return;
        }
        DataHolder.setBook(book);
        Parent root = FXMLLoader.load(getClass().getResource("updatebook.fxml"));

        Scene scene = new Scene(root);

        Stage updatebookWindow = new Stage();
        updatebookWindow.resizableProperty().setValue(false);
        updatebookWindow.initModality(Modality.APPLICATION_MODAL);
        updatebookWindow.setOnCloseRequest(e -> FillTable());
        updatebookWindow.setTitle("Update Book");
        updatebookWindow.setScene(scene);
        updatebookWindow.show();
    }

    public void AddBookCloseHandler(final WindowEvent event) {
        FillTable();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        publishDate.setCellValueFactory(new PropertyValueFactory<Book, String>("publishDate"));

        FillTable();
    }

    public void FillTable() {
        ObservableList<Book> books = null;
        try {
            List<Book> booktmp = repository.GetAllBooks();
            if (booktmp != null)
                books = FXCollections.observableArrayList(booktmp);
        } catch (Exception e) {

            e.printStackTrace();
        }
        if (books != null)
            bookList.setItems(books);
        else
            bookList.setItems(null);
    }
}
