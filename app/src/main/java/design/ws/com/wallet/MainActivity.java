package design.ws.com.wallet;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import customfonts.MyTextView_Roboto_Bold;
import customfonts.MyTextView_Roboto_Medium;
import customfonts.MyTextView_Roboto_Regular;
import gomobile4wallet.Gomobile4wallet;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WrapContentHeightViewPager viewPager;
    Typeface mTypeface;
    MyTextView_Roboto_Regular wallet_dollers;
    MyTextView_Roboto_Medium wallet_account;
    String result = "";
    String dollers = "";
    private boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyTextView_Roboto_Bold wallet_title = findViewById(R.id.wallet_title);
        wallet_dollers = findViewById(R.id.wallet_dollers);
        wallet_account = findViewById(R.id.wallet_trans);
//        Button mSubmit = findViewById(R.id.wallet_submit);

        String token;
        try {
            token = Gomobile4wallet.fnNewWallet();
            result = Gomobile4wallet.getWalletAddress(token);
            dollers = Gomobile4wallet.fnGetBalance(result);
            dollers += " blance";
        } catch (Exception e) {
            e.printStackTrace();
        }
        wallet_dollers.setText(dollers);
        wallet_account.setText(result);
        wallet_title.setOnClickListener(onReflashWalletClick);
//        mSubmit.setOnClickListener(onSubmitClick);

        //setToolbar();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.addTab(tabLayout.newTab().setText("Transaction_History"));
        tabLayout.addTab(tabLayout.newTab().setText("Transaction_Sent"));

        mTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }


        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);

        viewPager = (WrapContentHeightViewPager) findViewById(R.id.pager);
        CategoryPagerAdapter adapter = new CategoryPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(4);
        
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setCurrentTab(int i) {
        viewPager.setCurrentItem(i);
    }

    private View.OnClickListener onReflashWalletClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String origin_token = "1QGZHSDbnoieUmT5x9RwjnaDC5dWkeDjGt";
            String origin_dollers = "";
            try {
                origin_dollers = Gomobile4wallet.fnGetBalance(origin_token);
                origin_dollers += " blance";
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!status) {
                wallet_dollers.setText(origin_dollers);
                wallet_account.setText(origin_token);
                status = true;
            } else {
                wallet_dollers.setText(dollers);
                wallet_account.setText(result);
                status = false;
            }
        }
    };

    private View.OnClickListener onSubmitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDialogSubmit();
        }
    };

    private void onDialogSubmit() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Do you execute the trade ?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
