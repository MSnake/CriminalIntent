package com.example.alex.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Alex on 25.08.2016.
 */
public class CrimeData {

    private static CrimeData crimes;
    private Context appContext;
    private ArrayList<Crime> crimeList;

    private CrimeData(Context appContext)
    {
        this.appContext = appContext;
        crimeList = new ArrayList<>();

        //Тестовое заполнение crimeList
        for (int i=0;i<100;i++)
        {
            Crime cr = new Crime();
            boolean solve=false;
            if ((i%2) == 0) solve = true;
            cr.setTitle("Crime # "+i);
            cr.setSolved(solve);
            crimeList.add(cr);
        }

    }

    public static CrimeData getCrimes(Context context){
        if (crimes == null){
            crimes = new CrimeData(context.getApplicationContext());
        }
        return crimes;
    }


    public Crime getCrime(UUID id){
        for (Crime crime:crimeList)
        {
            if (crime.getId().equals(id)){
                return crime;
            }
        }

        return null;
    }

    public void setCrimeList(ArrayList<Crime> crimeList) {
        this.crimeList = crimeList;
    }

    public ArrayList<Crime> getCrimeList() {
        return crimeList;
    }
}
