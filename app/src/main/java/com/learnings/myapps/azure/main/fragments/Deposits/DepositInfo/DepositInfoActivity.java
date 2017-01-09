package com.learnings.myapps.azure.main.fragments.deposits.depositInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.learnings.myapps.azure.DataTransfer;
import com.learnings.myapps.azure.entity.Account;
import com.learnings.myapps.azure.entity.Bank;
import com.learnings.myapps.azure.entity.BankOffer;
import com.learnings.myapps.azure.main.fragments.deposits.customProgressBar.CustomSeekBar;
import com.learnings.myapps.azure.main.fragments.deposits.customProgressBar.ProgressItem;
import com.learnings.myapps.azure.R;
import com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.learnings.myapps.azure.main.fragments.DataContainer.mClient;
import static com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess.FULLSUMM;
import static com.learnings.myapps.azure.main.fragments.processingModules.DepositProcess.PROFIT;


public class DepositInfoActivity extends AppCompatActivity implements DataTransfer{

    DepositInfo_infoFragment infoFragment;
    private Account current_account;
    private BankOffer current_offer;
    private Bank current_bank;
    private CustomSeekBar seekbar;
    private float totalSpan = 100;
    private float blueSpan = 80;
    private float greenSpan = 15;
    private float yellowSpan = totalSpan - blueSpan - greenSpan;
    private ArrayList<ProgressItem> progressItemList;
    TextView tv_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_info);

        infoFragment = new DepositInfo_infoFragment();
        FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, infoFragment, "info");
        ft.commit();

        Intent intent = getIntent();
        String acc_id = intent.getStringExtra("id");
        GetAccountAndOfferById(acc_id);

        final TextView tv_partial_info = (TextView) findViewById(R.id.textView8);
        tv_total = (TextView) findViewById(R.id.textView9);

        seekbar = (CustomSeekBar) findViewById(R.id.customSeekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int cur_pro = seekBar.getProgress();
                float blue;
                float green;
                if (progressItemList != null) {
                    blue = progressItemList.get(0).progressItemPercentage;
                    green = progressItemList.get(1).progressItemPercentage;

                    if (cur_pro <= blue)
                        tv_partial_info.setText("Start funds: " + blueSpan);
                    else if (cur_pro > blue && cur_pro < green + blue)
                        tv_partial_info.setText("Interest funds: " + greenSpan);
                    else
                        tv_partial_info.setText("Taxes: " + yellowSpan);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void GetAccountAndOfferById(final String acc_id) {
        final MobileServiceTable atable = mClient.getTable(Account.class);
        final MobileServiceTable otable = mClient.getTable(BankOffer.class);
        final MobileServiceTable btable = mClient.getTable(Bank.class);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<Account> aresponse = (List) atable.where()
                                                                 .field("id")
                                                                 .eq(acc_id).execute().get();
                    if (aresponse.size() != 1)
                        throw new Exception("Response returned more or less than 1 value");
                    else {
                        final List<BankOffer> oresponse = (List) otable.where()
                                .field("id")
                                .eq(aresponse.get(0).getBankOfferRefRecId()).execute().get();

                        if (oresponse.size() != 1)
                            throw new Exception("A response returned more or less than 1 value");
                        else {
                            final List<Bank> bresponse = (List) btable.where().field("id").eq(oresponse.get(0).getBankRefRecId())
                                    .execute().get();
                            if (bresponse.size() != 1)
                                throw new Exception("A response returned more or less than 1 value");
                            else
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        current_account = aresponse.get(0);
                                        current_offer = oresponse.get(0);
                                        current_bank = bresponse.get(0);
                                        ReceiveData(null);
                                    }
                                });
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
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
        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 100*yellowSpan / totalSpan;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);

        seekbar.initData(progressItemList);
        seekbar.invalidate();

        if (seekbar.getVisibility() == View.INVISIBLE)
            seekbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ReceiveData(Object data_sent) {

        setTitle("-> " + current_offer.getOfferName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TextView tv_accountid = (TextView) findViewById(R.id.textView24);
        TextView tv_depositname = (TextView) findViewById(R.id.textView10);
        TextView tv_interestrate = (TextView) findViewById(R.id.textView11);
        TextView tv_datefrom = (TextView) findViewById(R.id.textView12);
        TextView tv_dateto = (TextView) findViewById(R.id.textView13);
        TextView tv_holder = (TextView) findViewById(R.id.textView14);
        TextView tv_notice = (TextView) findViewById(R.id.textView15);
        tv_accountid.setText("Account ID: " + current_account.id);
        tv_depositname.setText("Deposit name: " + current_offer.getOfferName());
        tv_interestrate.setText("Interest rate: " + current_offer.getInterestRate());
        String datefrom;
        String dateto;
        Calendar c = Calendar.getInstance();
        c.setTime(current_account.getDateFrom());
        c.add(Calendar.MONTH, current_account.getDepositTermMonth());
        try {
            datefrom = sdf.format(current_account.getDateFrom());
            tv_datefrom.setText("From: " + datefrom);
            dateto = sdf.format(c.getTime());
            tv_dateto.setText("To: " + dateto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (current_account.getHolderName() != null)
            tv_holder.setText("Holder: " + current_account.getHolderName());
        else
            tv_holder.setText("");
        if (current_account.getNotice() != null)
            tv_notice.setText("Notice: " + current_account.getNotice());
        else
            tv_notice.setText("");

        CalculatePercents();

        initDataToSeekbar();
    }

    private void CalculatePercents() {
        Float[] indexes;
        if (current_offer.getCapitalize().equals("no"))
            indexes = DepositProcess.GetSimpleAccomulated(current_account.getStartFunds(),
                                                          current_offer.getInterestRate(),
                                                          current_account.getDepositTermMonth()*30);
        else
            indexes = DepositProcess.GetComplexAccomulated(current_account.getStartFunds(),
                                                                current_offer.getInterestRate(),
                                                                current_account.getDepositTermMonth()*30,
                                                                current_offer.getInterestPeriodicity());
        blueSpan = current_account.getStartFunds();
        greenSpan = indexes[PROFIT];
        totalSpan = indexes[FULLSUMM];
        yellowSpan = 0;

        tv_total.setText("Result summ: " + totalSpan);
    }

    public void ReplaceInfoFragment() {
        DepositInfo_inputFragment inputFragment = new DepositInfo_inputFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, inputFragment, "taxes");
        ft.commit();

        Toast.makeText(this, "Show taxes", Toast.LENGTH_SHORT).show();
    }

    public void ShowTaxes(String current_region) {
        DepositInfo_taxesFragment taxesFragment = new DepositInfo_taxesFragment();
        Bundle b = new Bundle();
        taxesFragment.setArguments(b);
        b.putString("region", current_region);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, taxesFragment, "taxes");
        ft.commit();
    }

    public void EditAccount() {
        Toast.makeText(this, "Edit Account", Toast.LENGTH_SHORT).show();
    }
}
