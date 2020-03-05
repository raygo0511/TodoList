package design.ws.com.wallet;

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

import customfonts.MyTextView_Roboto_Medium;
import gomobile4wallet.Gomobile4wallet;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WrapContentHeightViewPager viewPager;
    Typeface mTypeface;
    Button mSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyTextView_Roboto_Medium wallet_account = findViewById(R.id.wallet_trans);
        mSubmit = findViewById(R.id.wallet_submit);
        String token;
        String result = null;
        try {
            token = Gomobile4wallet.fnNewWallet();
            result = Gomobile4wallet.getWalletAddress(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wallet_account.setText(result);
        mSubmit.setOnClickListener(onSubmitClick);

        //setToolbar();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.addTab(tabLayout.newTab().setText("Transaction"));

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

    private View.OnClickListener onSubmitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDialogSubmit();
        }
    };

    private void onDialogSubmit() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("this test")
                .setPositiveButton("Confirm", null)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
