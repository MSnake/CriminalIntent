package com.example.alex.criminalintent;

import java.util.UUID;

/**
 * Created by Alex on 22.08.2016.
 */
public class Crime {

    private UUID id;
    private String title;

    public Crime (){
        id = UUID.randomUUID();
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
}
