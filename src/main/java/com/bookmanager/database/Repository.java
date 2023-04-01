package com.bookmanager.database;

import java.util.List;
import com.bookmanager.models.Book;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

public class Repository {
    private final String DATABASE_URL = "jdbc:sqlite:G:/Projects/Java/bookmanagement/database.db";
    private JdbcConnectionSource connectionSource;
    private Dao<Book, Integer> bookDao;

    private void init() throws Exception {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        bookDao = DaoManager.createDao(connectionSource, Book.class);
    }

    public void AddBook(Book book) throws Exception {
        init();
        bookDao.create(book);
        connectionSource.close();
    }

    public void DeleteBook(Book book) throws Exception {
        init();
        bookDao.delete(book);
        connectionSource.close();
    }

    public void UpdateBook(Book book) throws Exception {
        init();
        bookDao.update(book);
        connectionSource.close();
    }

    public Book GetBookById(int id) throws Exception {
        init();
        QueryBuilder<Book, Integer> statement = bookDao.queryBuilder();
        statement.where().eq("id", id);
        List<Book> result = bookDao.query(statement.prepare());
        connectionSource.close();
        if (!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    public List<Book> GetAllBooks() throws Exception {
        init();
        List<Book> books = bookDao.queryForAll();
        connectionSource.close();
        if (!books.isEmpty()) return books;
        else return null;
    }

    
}
