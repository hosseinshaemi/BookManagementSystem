package com.bookmanager.database;

import com.bookmanager.models.Book;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class SetupDatabase {
    private final static String DATABASE_URL = "jdbc:sqlite:G:/Projects/Java/bookmanagement/database.db";

    public void doMain() throws Exception {
        JdbcConnectionSource connectionSource  = null;
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            setupDatabase(connectionSource);
        } finally {
            if (connectionSource != null) connectionSource.close();
        }
    }

    public void setupDatabase(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
    }
}
