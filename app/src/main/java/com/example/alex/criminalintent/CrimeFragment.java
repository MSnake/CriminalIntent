package com.example.alex.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import java.util.UUID;

/**
 * Created by Alex on 22.08.2016.
 */
public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID="com.example.alex.criminalintent.crimeFragment";
    private static final String DIALOG_DATE="date";
    private Crime crime;
    private TextView titleField;
    private Button dateButton;
    private CheckBox checkSolved;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID newID = null;
        if (getArguments().getSerializable(EXTRA_CRIME_ID) == null)
        {
            crime = new Crime();
        }
        else
        {
            newID = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
            crime = CrimeData.getCrimes(getActivity()).getCrime(newID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_crime,parent,false);
        titleField = (TextView) v.findViewById(R.id.crime_title);
        titleField.setText(crime.getTitle());
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

        dateButton.setText(crime.getDate().toString());
        dateButton.setEnabled(true);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(fm,DIALOG_DATE);

            }
        });

        checkSolved = (CheckBox) v.findViewById(R.id.crime_solved);
        checkSolved.setChecked(crime.isSolved());
        checkSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Флаг раскрытия преступления
                crime.setSolved(b);
            }
        });
        return v;
    }

    public static CrimeFragment newInstance(UUID crimeID)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeID);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }


}
