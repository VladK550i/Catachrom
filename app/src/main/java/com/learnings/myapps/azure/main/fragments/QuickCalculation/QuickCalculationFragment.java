package com.learnings.myapps.azure.main.fragments.quickCalculation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.learnings.myapps.azure.R;
import com.learnings.myapps.azure.customProgressBar.CustomSeekBar;
import com.learnings.myapps.azure.customProgressBar.ProgressItem;
import com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess;

import java.util.ArrayList;

import static com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess.FULLSUMM;
import static com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess.PROFIT;


public class QuickCalculationFragment extends Fragment {

    private float totalSpan = 100;
    private float blueSpan = 80;
    private float greenSpan = 15;
    private ArrayList<ProgressItem> progressItemList;
    CustomSeekBar seekBar;

    public QuickCalculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Quick calculation");
        View v = inflater.inflate(R.layout.fragment_quick_calculation, container, false);

        final EditText et_start = (EditText) v.findViewById(R.id.editText6);
        final EditText et_ir = (EditText) v.findViewById(R.id.editText7);
        final EditText et_term = (EditText) v.findViewById(R.id.editText8);
        final TextView tv_result = (TextView) v.findViewById(R.id.textView34);
        final TextView tv_seekBarParameter = (TextView) v.findViewById(R.id.textView35);
        seekBar = (CustomSeekBar) v.findViewById(R.id.customSeekBar_quick);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int cur_pro = seekBar.getProgress();
                float blue;
                float green;
                if (progressItemList != null) {
                    blue = progressItemList.get(0).progressItemPercentage;
                    green = progressItemList.get(1).progressItemPercentage;

                    if (cur_pro <= blue)
                        tv_seekBarParameter.setText("Start funds: " + blueSpan);
                    else if (cur_pro > blue && cur_pro < green + blue)
                        tv_seekBarParameter.setText("Interest funds: " + greenSpan);
                }

                if (tv_seekBarParameter.getVisibility() == View.INVISIBLE) {
                    tv_seekBarParameter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button btn_calculate = (Button) v.findViewById(R.id.button10);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float start = Float.valueOf(et_start.getText().toString());
                float ir = Float.valueOf(et_ir.getText().toString());
                int term = Integer.valueOf(et_term.getText().toString());
                Float[] indexes = DepositProcess.GetSimpleAccumulated(start, ir, term*30);
                blueSpan = start;
                greenSpan = indexes[PROFIT];
                totalSpan = indexes[FULLSUMM];

                tv_result.setText("Result summ: " + indexes[FULLSUMM]);
                initDataToSeekbar();

                if (seekBar.getVisibility() == View.INVISIBLE) {
                    seekBar.setVisibility(View.VISIBLE);
                    tv_result.setVisibility(View.VISIBLE);
                }
            }
        });

        return v;
    }

    private void initDataToSeekbar() {
        ProgressItem mProgressItem;
        progressItemList = new ArrayList<>();
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 100*blueSpan / totalSpan;
        mProgressItem.color = R.color.blue;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 100*greenSpan / totalSpan;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);

        seekBar.initData(progressItemList);
        seekBar.invalidate();
    }
}
