module bookmanagement {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires org.xerial.sqlitejdbc;

    opens com.bookmanager to javafx.fxml;
    opens com.bookmanager.models to ormlite.jdbc;
    exports com.bookmanager;
    exports com.bookmanager.models;
}