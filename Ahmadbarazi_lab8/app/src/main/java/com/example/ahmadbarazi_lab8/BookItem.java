package com.example.ahmadbarazi_lab8;

public class BookItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public BookItem(int imageResourse, String text1, String text2) {
        mImageResource = imageResourse;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
