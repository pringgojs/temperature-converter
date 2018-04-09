package com.example.sangpemikir.firstapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sangpemikir.firstapp.MainActivity;
import com.example.sangpemikir.firstapp.R;

public class ShowResultFragment extends android.support.v4.app.Fragment {
    TextView txtResult;
    TextView txtConvertTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewFragment = inflater.inflate(R.layout.activity_show_result, container,false);
        ((MainActivity) getActivity()).setActionBarTitle("Result");
        txtResult = (TextView) viewFragment.findViewById(R.id.txtResult);
        txtConvertTo = (TextView) viewFragment.findViewById(R.id.txtConvertTo);
        Bundle bundle = getArguments();
        txtResult.setText(bundle.getString("result"));
        txtConvertTo.setText(bundle.getString("convertToSimbol"));

        getActivity().setTitle("Result"); // set new title
        return viewFragment;
    }
}
