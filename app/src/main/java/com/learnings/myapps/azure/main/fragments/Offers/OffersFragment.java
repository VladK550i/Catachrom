package com.learnings.myapps.azure.main.fragments.offers;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.learnings.myapps.azure.R;
import com.learnings.myapps.azure.entity.Bank;
import com.learnings.myapps.azure.entity.BankOffer;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.learnings.myapps.azure.main.fragments.DataContainer.mClient;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_BANK_NAME;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_OFFER_NAME;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_CURRENCY;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_IRATE;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_PERIODICITY;


public class OffersFragment extends Fragment {

    private ArrayList<HashMap<String, String>> list;
    private MultiColumnAdapter adapter;

    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Offers");
        View v = inflater.inflate(R.layout.fragment_offers, container, false);

        ListView listView=(ListView) v.findViewById(R.id.lv_offers);
        list = new ArrayList<>();

        adapter = new MultiColumnAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(getActivity(), Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
            }

        });

        SelectOffers(v);
        return v;
    }

    private void SelectOffers(final View v) {
        final MobileServiceTable otable = mClient.getTable(BankOffer.class);
        final MobileServiceTable btable = mClient.getTable(Bank.class);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Runnable emptyListRunnable = new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = (TextView) v.findViewById(R.id.textView);
                            tv.setText("We have no offers yet.");
                        }
                    };
                    Runnable populatedListRunnable = new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = (TextView) v.findViewById(R.id.textView);
                            tv.setVisibility(View.INVISIBLE);
                        }
                    };
                    final List<BankOffer> oresponse = (List) otable.execute().get();
                    final List<Bank> bresponse = (List) btable.execute().get();
                    Runnable sendDataRunnable = new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (BankOffer offer: oresponse) {
                                        String cur_bank_name = "";
                                        for (Bank b : bresponse) {
                                            if (b.id.equals(offer.getBankRefRecId()))
                                                cur_bank_name = b.getBankName();
                                        }
                                        HashMap<String,String> temp = new HashMap<>();
                                        temp.put(COLUMN_OFFER_NAME, offer.getOfferName());
                                        temp.put(COLUMN_BANK_NAME, cur_bank_name);
                                        temp.put(COLUMN_CURRENCY, offer.getCurrency());
                                        temp.put(COLUMN_IRATE, String.valueOf(((int) offer.getInterestRate())));
                                        temp.put(COLUMN_PERIODICITY, String.valueOf(offer.getInterestPeriodicity()));
                                        list.add(temp);
                                    }
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            });
                        }
                    };

                    if (oresponse == null || oresponse.size() == 0) {
                        getActivity().runOnUiThread(emptyListRunnable);
                    } else {
                        getActivity().runOnUiThread(populatedListRunnable);
                    }
                    getActivity().runOnUiThread(sendDataRunnable);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
