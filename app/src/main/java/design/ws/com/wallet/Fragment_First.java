package design.ws.com.wallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rp on 8/30/2016.
 */
public class Fragment_First extends Fragment {

    private View view;
    RecyclerView testRecyclerView;

    private ArrayList<WalletAddressUtils> walletAddressUtilsList = new ArrayList<WalletAddressUtils>();
    private List<String> list = new ArrayList<String>();
    //自定義recyclerveiw的介面卡
    private FirstListAdapter mFirstListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);

        //對recycleview進行配置
        initRecyclerView();

        initData();
        return view;
    }

    private void initGoodsData() {
//        for (int i=0;i<10;i++){
//            WalletAddressUtils walletAddressUtils =new WalletAddressUtils();
//            walletAddressUtils.setIsTrue("模擬資料"+i);
//            walletAddressUtils.setTransAddress("100"+i);
//            walletAddressUtilsList.add(walletAddressUtils);
//        }
    }

    private void initRecyclerView() {
        //獲取RecyclerView
        testRecyclerView = view.findViewById(R.id.first_recyclerView);
        //获取数据，向适配器传数据，绑定适配器
        list = initData();
        //建立adapter
//        mFirstListAdapter = new FirstListAdapter(getActivity(), list);
        //給RecyclerView設定adapter
        testRecyclerView.setAdapter(mFirstListAdapter);
        //設定layoutManager,可以設定顯示效果，是線性佈局、grid佈局，還是瀑布流佈局
        //引數是：上下文、列表方向（橫向還是縱向）、是否倒敘
        testRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //設定item的分割線
        testRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中沒有item的監聽事件，需要自己在介面卡中寫一個監聽事件的介面。引數根據自定義
        mFirstListAdapter.setOnItemClickListener(new FirstListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, WalletAddressUtils data) {
                //此處進行監聽事件的業務處理
                Toast.makeText(getActivity(),"我是item",Toast.LENGTH_SHORT).show();
            }
        });

        //添加动画
        testRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    protected ArrayList<String> initData() {
        ArrayList<String> mDatas = new ArrayList<String>();
        for (int i = 0; i < 1; i++) {
            mDatas.add("我是商品" + i);
        }
        return mDatas;
    }
}
