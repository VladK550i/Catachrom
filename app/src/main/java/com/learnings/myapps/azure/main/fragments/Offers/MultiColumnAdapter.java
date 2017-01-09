package com.learnings.myapps.azure.main.fragments.offers;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.learnings.myapps.azure.R;

import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_BANK_NAME;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_CAPITALIZATION;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_MINSTART;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_OFFER_NAME;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_CURRENCY;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_IRATE;
import static com.learnings.myapps.azure.main.fragments.offers.Constants.COLUMN_PERIODICITY;

public class MultiColumnAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    TextView txtFifth;
    TextView txtSixth;
    TextView txtSeventh;
    public MultiColumnAdapter(Activity activity, ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);

            txtFirst=(TextView) convertView.findViewById(R.id.offer_name);
            txtSecond=(TextView) convertView.findViewById(R.id.bank_name);
            txtThird=(TextView) convertView.findViewById(R.id.minstart);
            txtFourth=(TextView) convertView.findViewById(R.id.currency);
            txtFifth=(TextView) convertView.findViewById(R.id.irate);
            txtSixth=(TextView) convertView.findViewById(R.id.periodicity);
            txtSeventh=(TextView) convertView.findViewById(R.id.capitalization);


        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(COLUMN_OFFER_NAME));
        txtSecond.setText(map.get(COLUMN_BANK_NAME));
        txtThird.setText(map.get(COLUMN_MINSTART));
        txtFourth.setText(map.get(COLUMN_CURRENCY));
        txtFifth.setText(map.get(COLUMN_IRATE));
        txtSixth.setText(map.get(COLUMN_PERIODICITY));
        txtSeventh.setText(map.get(COLUMN_CAPITALIZATION));

        return convertView;
    }

}
