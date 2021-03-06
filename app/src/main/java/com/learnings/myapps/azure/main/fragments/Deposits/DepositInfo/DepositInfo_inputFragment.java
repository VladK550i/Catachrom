package com.learnings.myapps.azure.main.fragments.deposits.depositInfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.learnings.myapps.azure.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepositInfo_inputFragment extends Fragment {

    private List<String> regions = Arrays.asList("BY", "RU", "UA");
    private ArrayAdapter regions_adapter;
    private String current_region;

    public DepositInfo_inputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deposit_info_input, container, false);
        final Spinner regions_spinner = (Spinner) v.findViewById(R.id.spinner3);
        regions_adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, regions);
        regions_spinner.setAdapter(regions_adapter);
        regions_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                current_region = regions_adapter.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        regions_spinner.setSelection(0);

        Button btn = (Button) v.findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current_region != null) {
                    ((DepositInfoActivity)getActivity()).ShowTaxesFragment(current_region);
                }
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        DepositInfoActivity activity = ((DepositInfoActivity)getActivity());
        if (activity.getYellowSpan() != -1)
            activity.HideTax();
    }
}
