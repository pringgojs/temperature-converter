package com.example.sangpemikir.firstapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangpemikir.firstapp.MainActivity;
import com.example.sangpemikir.firstapp.R;

public class CalculatorFragment extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFrag1 = inflater.inflate(R.layout.activity_calculator, container,false);
        ((MainActivity) getActivity()).setActionBarTitle("Calculator");
        return viewFrag1;
    }
}
