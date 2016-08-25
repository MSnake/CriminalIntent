package com.example.alex.criminalintent;

import android.provider.ContactsContract;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Alex on 22.08.2016.
 */
public class Crime {

    private UUID id;
    private String title;
    private Date date;
    private boolean solved;

    public Crime (){
        this.id = UUID.randomUUID();
        this.date = new Date();
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }
}
