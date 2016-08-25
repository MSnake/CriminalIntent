package com.example.alex.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Alex on 22.08.2016.
 */
public class CrimeFragment extends Fragment {

    private Crime crime;
    private TextView titleField;
    private Button dateButton;
    private CheckBox checkSolved;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_crime,parent,false);
        titleField = (TextView) v.findViewById(R.id.crime_title);
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Пока не трогаем
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //Действие при изменении поля
                crime.setTitle(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Пока не трогаем
            }
        });

        dateButton = (Button) v.findViewById(R.id.crime_date);

        Date d = new Date();
        dateButton.setText(crime.getDate().toString());
        dateButton.setEnabled(false);

        checkSolved = (CheckBox) v.findViewById(R.id.crime_solved);
        checkSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Флаг раскрытия преступления
                crime.setSolved(b);
            }
        });



        return v;
    }
}
