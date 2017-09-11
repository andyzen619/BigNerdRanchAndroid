package com.android.andydesk.criminalintent.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.android.andydesk.criminalintent.Crime;
import com.android.andydesk.criminalintent.CrimeLab;
import com.android.andydesk.criminalintent.R;

import java.util.List;
import java.util.UUID;

import static android.widget.CompoundButton.*;
import static com.android.andydesk.criminalintent.CrimePagerActivity.mViewPager;
import static com.android.andydesk.criminalintent.CrimePagerActivity.newIntent;

/**
 * Created by AndyDesk on 7/3/2017.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitleField;
    private Button mCrimeDateButton;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private Button mFirstButton;
    private Button mLastButton;
    private List<Crime> mCrimeList;
    private static final String DIALOG_DATE = "DialogDate";
    private Button mDateButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        UUID crimeId = (UUID) bundle.getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mCrimeList = CrimeLab.get(getActivity()).getCrimesList();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mCrimeTitleField = (EditText) view.findViewById(R.id.crime_title);
        mCrimeTitleField.setText(mCrime.getmTitle());
        mCrimeTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mCrimeDateButton = (Button) view.findViewById(R.id.crime_date);
        mCrimeDateButton.setText(mCrime.getDate().toString());
        mCrimeDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });


        mFirstButton = (Button) view.findViewById(R.id.first_button);
        if(mCrime.getId() == mCrimeList.get(0).getId()) {
            mFirstButton.setEnabled(false);
        }
        mFirstButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                newIntent(getActivity(), mCrime.getId());
            }
        });
        mLastButton = (Button) view.findViewById(R.id.last_button);
        if(mCrime.getId() == mCrimeList.get(mCrimeList.size() -1 ).getId()) {
            mLastButton.setEnabled(false);
        }
        mLastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimeList.size() - 1);
                newIntent(getActivity(), mCrime.getId());
            }
        });
        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mDateButton.setEnabled(true);
        mDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });
        return view;
    }

    /**
     * Sets up the arguments for a fragment before an instance of it is returned.
     * @param crimeId to be stored in the arguments
     * @return a new fragment with the new arguments stored
     */
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
