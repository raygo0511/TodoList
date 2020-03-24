package design.ws.com.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<WalletAddressUtils> dataSet;
    private FirstListAdapter firstListAdapter;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Bundle bundle = this.getIntent().getExtras();
        String results[] = bundle.getStringArray("transList");
        String arrayString = Arrays.toString(results);
        dataSet = new ArrayList<>();
        pref = getSharedPreferences("transFile", MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", true);

        if (isFirst) {
            pref.edit().putString("item", arrayString).apply();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", false).apply();

            String transItem[] = arrayString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            String transAddress = transItem[0];
            String transBlance = transItem[1];
            String transTime = getTransTime();
            pref.edit().putString("itemTime", transTime).apply();
            dataSet.add(new WalletAddressUtils(transAddress, transBlance, transTime));
        } else {
            pref.edit().putString("item2", arrayString).apply();
            String oldList = pref.getString("item", "not value");
            String oldItem[] = oldList.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            String oldAddress = oldItem[0];
            String oldBlance = oldItem[1];
            String oldTime = pref.getString("itemTime", "not value");
            String transItem[] = arrayString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            String transAddress = transItem[0];
            String transBlance = transItem[1];
            String transTime = getTransTime();
            dataSet.add(new WalletAddressUtils(oldAddress, oldBlance, oldTime));
            firstListAdapter.addData(0, new WalletAddressUtils(transAddress, transBlance, transTime));
        }


        mRecyclerView = findViewById(R.id.trans_recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //設定item的分割線
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //添加动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        firstListAdapter = new FirstListAdapter(this, dataSet);
        mRecyclerView.setAdapter(firstListAdapter);
    }

    public View.OnClickListener addTransOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String test = "test";
//            firstListAdapter.addData(0, test);
        }
    };

    public String getTransTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String time = format.format(curDate);
        return time;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
//            bundle.putStringArray();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
