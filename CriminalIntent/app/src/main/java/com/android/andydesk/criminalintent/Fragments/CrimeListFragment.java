package com.android.andydesk.criminalintent.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andydesk.criminalintent.Crime;
import com.android.andydesk.criminalintent.CrimeLab;
import com.android.andydesk.criminalintent.CrimePagerActivity;
import com.android.andydesk.criminalintent.R;

import java.util.List;

/**
 * Created by AndyDesk on 7/6/2017.
 */

public class CrimeListFragment extends Fragment {
    public static final String UPDATED_CRIME_POSITION_EXTRA = "crime_position";
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private List<Crime> mCrimes;
    public static final int UPDATED_CRIME_RESULT = 0;
    private int mUpdatedCrime_position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Sets the adapter appropriate for the recycler view;
        updateUI();

        return view;
    }

    /**
     * Updates the list of crimes to reflect the data stored.
     */
    private void updateUI() {
        CrimeLab crimeLab  = CrimeLab.get(getContext());
        List<Crime> mCrimes = crimeLab.getCrimesList();

        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(mCrimes);
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        }
        else {
            if(mUpdatedCrime_position != -1) {
                mCrimeAdapter.notifyItemChanged(mUpdatedCrime_position);
            }
            else {
                System.out.println("Nothing was changed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }

        mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        mUpdatedCrime_position = -1;
    }

    /**
     * Inner View holder class that will inflate and own the crime list item layout
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mCrimeTitle;
        TextView mCrimeDate;
        Crime currentCrime;
        ImageView mCrimeSolvedImage;

        public CrimeHolder(LayoutInflater inflater,
                           ViewGroup parent) {
            super(inflater.inflate(R.layout.crime_list_item, parent, false));

            mCrimeTitle = (TextView) itemView.findViewById(R.id.crime_title);
            mCrimeDate = (TextView) itemView.findViewById(R.id.crime_date);
            mCrimeSolvedImage = (ImageView) itemView.findViewById(R.id.crime_solved);

            itemView.setOnClickListener(this);
        }

        public void bind(Crime crime) {
            currentCrime = crime;
            mCrimeTitle.setText(crime.getmTitle());
            mCrimeDate.setText(crime.getDate());
            mCrimeSolvedImage.setVisibility(crime.ismSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent intent = CrimePagerActivity.newIntent(getActivity(), currentCrime.getId());
            startActivity(intent);
            mUpdatedCrime_position = this.getAdapterPosition();
        }
    }


    /**
     * Adapter to hold the list of crime.
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter (List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }


        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }




}
