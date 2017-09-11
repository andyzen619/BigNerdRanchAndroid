package com.android.andydesk.criminalintent.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.andydesk.criminalintent.R;

/**
 * Created by AndyDesk on 9/6/2017.
 */

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_label)
                .setPositiveButton(android.R.string.ok, null)
                .setView(view)
                .create();
    }
}
