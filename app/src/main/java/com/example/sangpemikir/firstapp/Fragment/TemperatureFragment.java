package com.example.sangpemikir.firstapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangpemikir.firstapp.MainActivity;
import com.example.sangpemikir.firstapp.R;

import java.util.ArrayList;
import java.util.List;

public class TemperatureFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {
    EditText txtValue;
    TextView lbResult;
    Button btnConvert;
    List<String> categories;
    Spinner spConvertTo, spConvertFrom;
    View viewFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewFragment = inflater.inflate(R.layout.fragment_temperature, container,false);
        ((MainActivity) getActivity()).setActionBarTitle("Temperature");
        initialVariable();
        dropDownSpinner();
        createAdapterSpiner();
        btnConvertClick();

        return viewFragment;
    }

    private void btnConvertClick() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (txtValue.getText().toString().equals("")) {
                    Toast.makeText(v.getContext(), "Please fill the blank form", Toast.LENGTH_LONG).show();
                } else {
                    String txtConvertTo = spConvertTo.getSelectedItem().toString();
                    String convertToSimbol = simbolTemp(txtConvertTo);
                    Double result = calculate();

                    Bundle bundle = new Bundle();
                    bundle.putString("result", result.toString());
                    bundle.putString("convertToSimbol", convertToSimbol);
                    ShowResultFragment showResultFragment = new ShowResultFragment();
                    showResultFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_menu, showResultFragment);
                    fragmentTransaction.commit();
                }
            }

            private Double calculate() {
                double res = 0;
                double inValue = Double.parseDouble(txtValue.getText().toString());
                String txtConvertFrom = spConvertFrom.getSelectedItem().toString();
                String txtConvertTo = spConvertTo.getSelectedItem().toString();

                /*
                *************************
                * Convert from Celcius
                * ***********************
                */

                // C to F
                if (txtConvertFrom.equals("Celcius (\u2103)") && txtConvertTo.equals("Fahrenheit (\u2109)")) {
                    res = 1.8 * inValue + 32;
                }

                // C to K
                if (txtConvertFrom.equals("Celcius (\u2103)") && txtConvertTo.equals("Kelvin (\u212A)")) {
                    res = inValue + 273.15;
                }

                //C to R
                if (txtConvertFrom.equals("Celcius (\u2103)") && txtConvertTo.equals("Réaumur (\u00B0R)")) {
                    res = 0.8 * inValue;
                }

                if (txtConvertFrom.equals("Celcius (\u2103)") && txtConvertTo.equals("Celcius (\u2103)")) {
                    res = inValue;
                }

                /*
                *************************
                * Convert from Reamur
                * ***********************
                */

                // R to C
                if (txtConvertFrom.equals("Réaumur (\u00B0R)") && txtConvertTo.equals("Celcius (\u2103)")) {
                    res = (5/4) * inValue;
                }

                // R to F
                if (txtConvertFrom.equals("Réaumur (\u00B0R)") && txtConvertTo.equals("Fahrenheit (\u2109)")) {
                    res = (9/4) * inValue + 32;
                }

                // R to K
                if (txtConvertFrom.equals("Réaumur (\u00B0R)") && txtConvertTo.equals("Kelvin (\u212A)")) {
                    res = inValue + 273;
                }

                // R to R
                if (txtConvertFrom.equals("Réaumur (\u00B0R)") && txtConvertTo.equals("Réaumur (\u00B0R)")) {
                    res = inValue;
                }

                /*
                *************************
                * Convert from Fahrenheit
                * ***********************
                */

                // F to C
                if (txtConvertFrom.equals("Fahrenheit (\u2109)") && txtConvertTo.equals("Celcius (\u2103)")) {
                    res = (inValue-32) / 1.8;
                }

                // F to R
                if (txtConvertFrom.equals("Fahrenheit (\u2109)") && txtConvertTo.equals("Réaumur (\u00B0R)")) {
                    res = (inValue-32) / 2.25;
                }

                // F to K
                if (txtConvertFrom.equals("Fahrenheit (\u2109)") && txtConvertTo.equals("Kelvin (\u212A)")) {
                    res = (inValue+459.67) / 1.8;
                }

                // F to F
                if (txtConvertFrom.equals("Fahrenheit (\u2109)") && txtConvertTo.equals("Fahrenheit (\u2109)")) {
                    res = inValue;
                }

                /*
                *************************
                * Convert from Kelvin
                * ***********************
                */

                // K to C
                if (txtConvertFrom.equals("Kelvin (\u212A)") && txtConvertTo.equals("Celcius (\u2103)")) {
                    res = inValue - 273.15;
                }

                // K to R
                if (txtConvertFrom.equals("Kelvin (\u212A)") && txtConvertTo.equals("Réaumur (\u00B0R)")) {
                    res = (inValue-273.15) * 0.8;
                }

                // K to F
                if (txtConvertFrom.equals("Kelvin (\u212A)") && txtConvertTo.equals("Fahrenheit (\u2109)")) {
                    res = inValue * 1.8 - 459.67;
                }

                // K to K
                if (txtConvertFrom.equals("Kelvin (\u212A)") && txtConvertTo.equals("Kelvin (\u212A)")) {
                    res = inValue;
                }

                return res;
            }
        });
    }

    private void createAdapterSpiner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spConvertFrom.setAdapter(dataAdapter);
        spConvertTo.setAdapter(dataAdapter);
    }

    private void dropDownSpinner() {
        categories = new ArrayList<String>();
        categories.add("Celcius (\u2103)");
        categories.add("Fahrenheit (\u2109)");
        categories.add("Kelvin (\u212A)");
        categories.add("Réaumur (\u00B0R)");
    }

    private void initialVariable() {
        txtValue = (EditText) viewFragment.findViewById(R.id.txtValue);
        btnConvert = (Button) viewFragment.findViewById(R.id.btnConvert);
        spConvertFrom = (Spinner) viewFragment.findViewById(R.id.spConvertFrom);
        spConvertTo = (Spinner) viewFragment.findViewById(R.id.spConvertTo);
    }

    private String simbolTemp(String txtConvertTo) {
        if (txtConvertTo.equals("Fahrenheit (\u2109)")) {
            return "\u2109";
        }
        if (txtConvertTo.equals("Celcius (\u2103)")) {
            return "\u2103";
        }
        if (txtConvertTo.equals("Kelvin (\u212A)")) {
            return "\u212A";
        }

        return "\u00B0R";
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
