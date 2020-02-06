package com.example.ahmadbarazi_lab8.database;

import android.provider.BaseColumns;

public class BookContract {

    public BookContract(){}

    public static final class BookEntry implements BaseColumns{
        public static final String TABLE_NAME = "Books";
        public static final String COLUMN_NAME = "bookname";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
