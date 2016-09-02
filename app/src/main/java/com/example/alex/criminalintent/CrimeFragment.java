package com.example.alex.criminalintent;

import android.app.Activity;
import android.content.Intent;
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

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Alex on 22.08.2016.
 */
public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID="com.example.alex.criminalintent.crimeFragment";
    private static final String DIALOG_DATE="date";
    private static final String DIALOG_TIME="time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private Crime crime;
    private TextView titleField;
    private Button dateButton;
    private Button timeButton;
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

        updateDate();
        dateButton.setEnabled(true);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dateFragment = DatePickerFragment.newInstance(crime.getDate());
                dateFragment.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dateFragment.show(fm,DIALOG_DATE);

            }
        });

//        timeButton = (Button) v.findViewById(R.id.crime_time);
//        updateTime();
//        timeButton.setEnabled(true);
//        timeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                TimePickerFragment timeFragment = TimePickerFragment.newInstance(crime.getDate());
//                timeFragment.setTargetFragment(CrimeFragment.this,REQUEST_TIME);
//                timeFragment.show(fm,DIALOG_TIME);
//            }
//        });




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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE)
        {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            updateDate();
        }

//        if (requestCode == REQUEST_TIME)
//        {
//            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
//            crime.setTime(date);
//            updateTime();
//        }
    }

    private void updateDate()
    {
        dateButton.setText(crime.getDate().toString());
    }

    private void updateTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(crime.getDate());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        timeButton.setText(""+hours+":"+minutes);

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
