package com.learnings.myapps.azure.main.fragments.deposits.depositInfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.learnings.myapps.azure.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepositInfo_infoFragment extends Fragment {


    public DepositInfo_infoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deposit_info_info, container, false);
        Button b_showtaxes = (Button) v.findViewById(R.id.button9);
        Button b_editaccount = (Button) v.findViewById(R.id.button8);
        Button b_delete = (Button) v.findViewById(R.id.button7);
        b_showtaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DepositInfoActivity)getActivity()).ShowInputFragment();
            }
        });
        b_editaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DepositInfoActivity)getActivity()).EditAccount();
            }
        });
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DepositInfoActivity)getActivity()).DeleteAccount();
            }
        });
        return v;
    }
}
