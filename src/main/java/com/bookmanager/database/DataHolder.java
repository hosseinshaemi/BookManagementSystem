package com.bookmanager.database;

import com.bookmanager.models.Book;

public class DataHolder {

    private static Book book;

    public static Book getBook() {
        return book;
    }

    public static void setBook(Book b) {
        book = b;
    }

}
