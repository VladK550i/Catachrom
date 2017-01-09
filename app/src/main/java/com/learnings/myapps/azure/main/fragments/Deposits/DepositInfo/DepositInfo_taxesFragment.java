package com.learnings.myapps.azure.main.fragments.deposits.depositInfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.learnings.myapps.azure.R;
import com.learnings.myapps.azure.entity.Account;
import com.learnings.myapps.azure.entity.BankOffer;
import com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepositInfo_taxesFragment extends Fragment {


    public DepositInfo_taxesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deposit_info_taxes, container, false);

        Bundle b = getArguments();
        String region = b.getString("region");

        TextView tv_preword = (TextView) v.findViewById(R.id.textView26);
        TextView tv_currency = (TextView) v.findViewById(R.id.textView28);
        TextView tv_term = (TextView) v.findViewById(R.id.textView30);

        DepositInfoActivity activity = ((DepositInfoActivity) getActivity());
        BankOffer cur_offer = activity.getCurrent_offer();
        Account cur_account = activity.getCurrent_account();
        float tax;
        switch (region) {
            case "BY":
                tax = DepositProcess.GetTaxBY(cur_account.getStartFunds(), cur_offer.getInterestRate(), cur_account.getDepositTermMonth()*30,
                        cur_account.getOtherFunds(), cur_account.getDateFrom(), cur_offer.getCurrency());
                ((DepositInfoActivity)getActivity()).setYellowSpan(tax);
                Toast.makeText(getContext(), "Current tax: " + tax, Toast.LENGTH_SHORT).show();

                tv_currency.setText("Currency: " + String.valueOf(cur_offer.getCurrency()));
                tv_term.setText("Term (months): " + String.valueOf(cur_account.getDepositTermMonth()));
                break;
            case "RU":
                tax = DepositProcess.GetTaxRU(cur_account.getStartFunds(), cur_offer.getInterestRate(), cur_account.getDepositTermMonth()*30,
                        cur_account.getOtherFunds(), cur_account.getDateFrom(), cur_offer.getCurrency());
                ((DepositInfoActivity)getActivity()).setYellowSpan(tax);
                Toast.makeText(getContext(), "Current tax: " + tax, Toast.LENGTH_SHORT).show();

                tv_currency.setText("Currency: " + String.valueOf(cur_offer.getCurrency()));
                tv_term.setText("Term (months): " + String.valueOf(cur_account.getDepositTermMonth()));
                break;
            default: {
                tv_preword.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Not yet supported!", Toast.LENGTH_SHORT).show();
            }
        }

        return v;
    }

}
