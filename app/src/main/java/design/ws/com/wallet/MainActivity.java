package design.ws.com.wallet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import customfonts.MyTextView_Roboto_Bold;
import customfonts.MyTextView_Roboto_Medium;
import customfonts.MyTextView_Roboto_Regular;
import gomobile4wallet.Gomobile4wallet;

public class MainActivity extends AppCompatActivity {
    private static final int SHOW_MESSAGE = 0;
    private Toolbar toolbar;
    private WrapContentHeightViewPager viewPager;
    Typeface mTypeface;
    MyTextView_Roboto_Regular wallet_dollers;
    MyTextView_Roboto_Medium wallet_account;
    String result = "";
    String dollers = "";
    String mBlockWallet = "Mv+PAwEBBldhbGxldAH/kAABAgEKUHJpdmF0ZUtleQH/kgABCVB1YmxpY0tleQEKAAAALv+RAwEBClByaXZhdGVLZXkB/5IAAQIBCVB1YmxpY0tleQH/lAABAUQB/5YAAAAv/5MDAQEJUHVibGljS2V5Af+UAAEDAQVDdXJ2ZQEQAAEBWAH/lgABAVkB/5YAAAAK/5UFAQL/mAAAAEf/kAEBARljcnlwdG8vZWxsaXB0aWMucDI1NkN1cnZl/5kDAQEJcDI1NkN1cnZlAf+aAAEBAQtDdXJ2ZVBhcmFtcwH/nAAAAFP/mwMBAQtDdXJ2ZVBhcmFtcwH/nAABBwEBUAH/lgABAU4B/5YAAQFCAf+WAAECR3gB/5YAAQJHeQH/lgABB0JpdFNpemUBBAABBE5hbWUBDAAAAP4Bb/+a/70BASEC/////wAAAAEAAAAAAAAAAAAAAAD///////////////8BIQL/////AAAAAP//////////vOb6racXnoTzucrC/GMlUQEhAlrGNdiqOpPns+u9VXaYhrxlHQawzFOw9jvOPD4n0mBLASECaxfR8uEsQkf4vOblY6RA8ncDfYEt6zOg9KE5RdiYwpYBIQJP40Li/hp/m47n60p8D54WK84zV2sxXs7LtkBoN79R9QH+AgABBVAtMjU2AAABIQJE7D85n1lWHk8xAU4jTHuGaSaQ5HFRWv4QxyMH+b6KjQEhApgTuSssrsn7+UdZsDehdzG7YJZj9rdF2z9AdRZSAr4BAAEhAvlHTrwwux4LaKsbVFIPWAO71Z0eQUAMLdryCJOsuktvAAFAROw/OZ9ZVh5PMQFOI0x7hmkmkORxUVr+EMcjB/m+io2YE7krLK7J+/lHWbA3oXcxu2CWY/a3Rds/QHUWUgK+AQA=";
    private boolean status = false;
    private boolean isFirst = true;
    String isTrue;
    AutoCompleteTextView mEditBlance, mEditWalletAddress;
    SharedPreferences pref;
    private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyTextView_Roboto_Bold wallet_title = findViewById(R.id.wallet_title);
        wallet_dollers = findViewById(R.id.wallet_dollers);
        wallet_account = findViewById(R.id.wallet_trans);
        mEditBlance = findViewById(R.id.activity_Blances);
        mEditWalletAddress = findViewById(R.id.activity_WalletAddress);
        Button mSubmit = findViewById(R.id.wallet_submit);

        String token;
        try {
            token = Gomobile4wallet.fnNewWallet();
            result = Gomobile4wallet.getWalletAddress(token);
            dollers = Gomobile4wallet.fnGetBalance(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wallet_dollers.setText(dollers);
        wallet_account.setText(result);
        wallet_title.setOnClickListener(onSwitchWalletAddressClick);
        mSubmit.setOnClickListener(onSubmitClick);

        initTablayout();

        pref = getSharedPreferences("blockAppFile", MODE_PRIVATE);
        pref.edit().putString("blockWallet", mBlockWallet).apply();
    }

    private void initTablayout() {
        //setToolbar();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        tabLayout.addTab(tabLayout.newTab().setText("Transaction_History"));
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

//        viewPager = findViewById(R.id.pager);
//        CategoryPagerAdapter adapter = new CategoryPagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.setOffscreenPageLimit(4);

//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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

    // 切換Wallet 帳戶
    private View.OnClickListener onSwitchWalletAddressClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String origin_token = "1QGZHSDbnoieUmT5x9RwjnaDC5dWkeDjGt";
            String origin_dollers = "";
            try {
                origin_dollers = Gomobile4wallet.fnGetBalance(origin_token);
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
//            onDialogSubmit();
            String blockAddress = pref.getString("blockWallet", "not value");
            String dollers = mEditBlance.getText().toString();
            String account = mEditWalletAddress.getText().toString();

            try {
                String result = Gomobile4wallet.fnNewTransaction(blockAddress, account, dollers);
                Log.d("測試 result", result);
                String a[] = result.split(",");
                isTrue = (a[0]);
                String transToken = (a[1]);
                if (isFirst) {
                    isFirst = false;
                    if (isTrue.equals("true")) {
                        Toast.makeText(MainActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, SubActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("transToken",transToken);
                        intent.putExtras(bundle);
//                        startActivityForResult(intent, SHOW_MESSAGE) ;
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "提供交易資訊不正確", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (isTrue.equals("true")) {
                        Toast.makeText(MainActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, SubActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("update", true);
                        bundle.putString("transToken", transToken);
                        intent.putExtras(bundle);
//                        startActivityForResult(intent, SHOW_MESSAGE) ;
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "提供交易資訊不正確", Toast.LENGTH_SHORT).show();
                    }
                }
                mEditBlance.getText().clear();
                mEditWalletAddress.getText().clear();
                new Thread(runnable).start();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "交易失敗", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };

    private void onDialogSubmit() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_second, null);
        final AutoCompleteTextView mEditBlance = mView.findViewById(R.id.second_Blances);
        final AutoCompleteTextView mEditWalletAddress = mView.findViewById(R.id.second_WalletAddress);
        mBuilder.setPositiveButton("Transaction", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String blockAddress = pref.getString("blockWallet", "not value");
                String dollers = mEditBlance.getText().toString();
                String account = mEditWalletAddress.getText().toString();

                try {
                    String result = Gomobile4wallet.fnNewTransaction(blockAddress, account, dollers);
                    Log.d("測試 result", result);
                    String a[] = result.split(",");
                    String bool = (a[0]);
                    String transToken = (a[1]);
                    if (bool.equals("true")) {
                        Toast.makeText(MainActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, SubActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("transToken",transToken);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "提供交易資訊不正確", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "交易失敗", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        mBuilder.setNegativeButton("Cancel", null);
        mBuilder.setView(mView);
        AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    if (!status) {
                        status = true;
                        String origin_token = "1QGZHSDbnoieUmT5x9RwjnaDC5dWkeDjGt";
                        String origin_dollers = "";
                        try {
                            origin_dollers = Gomobile4wallet.fnGetBalance(origin_token);
                            wallet_dollers.setText(origin_dollers);
                            wallet_account.setText(origin_token);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        status = false;
                        try {
                            dollers = Gomobile4wallet.fnGetBalance(result);
                            wallet_dollers.setText(dollers);
                            wallet_account.setText(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    Thread.sleep(1000);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (!status) {
//                                status = true;
//                                String origin_token = "1QGZHSDbnoieUmT5x9RwjnaDC5dWkeDjGt";
//                                String origin_dollers = "";
//                                try {
//                                    origin_dollers = Gomobile4wallet.fnGetBalance(origin_token);
//                                    wallet_dollers.setText(origin_dollers);
//                                    wallet_account.setText(origin_token);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                status = false;
//                                try {
//                                    dollers = Gomobile4wallet.fnGetBalance(result);
//                                    wallet_dollers.setText(dollers);
//                                    wallet_account.setText(result);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    });
//                }
//            } catch (InterruptedException e) {
//            }
//        }
//    };
}
