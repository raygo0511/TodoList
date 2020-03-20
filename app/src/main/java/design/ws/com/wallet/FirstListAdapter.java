package design.ws.com.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirstListAdapter extends RecyclerView.Adapter<FirstListAdapter.ViewHolder> {
    private Context context;
    private List<WalletAddressUtils> walletAddressUtilsList;
    private List<String> mDataSet;
    private String mToken;

    //建立建構函式
    FirstListAdapter(Context context, ArrayList<WalletAddressUtils> data) {
        //將傳遞過來的資料，賦值給本地變數
        this.context = context;//上下文
//        this.mToken = token;
        this.walletAddressUtilsList = data;
//        this.walletAddressUtilsList = list;
    }

    @NonNull
    @Override
    public FirstListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.first_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstListAdapter.ViewHolder viewHolder, int position) {
        WalletAddressUtils walletAddressUtils = walletAddressUtilsList.get(position);
        viewHolder.mItemAddress.setText(walletAddressUtils.getTransAddress());
        viewHolder.mItemBlance.setText(walletAddressUtils.getTransBlance());
        viewHolder.mItemTime.setText(walletAddressUtils.getTimeData());
//        viewHolder.mItemAddress.setText(mDataSet.get(position));
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//        Date curDate = new Date(System.currentTimeMillis());
//        String time = format.format(curDate);
//        viewHolder.mItemTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return walletAddressUtilsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemAddress;
        private TextView mItemBlance;
        private TextView mItemTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemAddress = itemView.findViewById(R.id.item_address);
            mItemBlance = itemView.findViewById(R.id.item_blance);
            mItemTime = itemView.findViewById(R.id.item_time);
        }
    }

    public interface OnItemClickListener {
        /**
         * 介面中的點選每一項的實現方法，引數自己定義
         *
         * @param view 點選的item的檢視
         * @param data 點選的item的資料
         */
        void OnItemClick(View view, WalletAddressUtils data);
    }

    //需要外部訪問，所以需要設定set方法，方便呼叫
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // 新增item
    public void updataData(String note) {
        mDataSet.add(note);
        notifyDataSetChanged();
    }

    public void addData(int position, WalletAddressUtils text) {
        walletAddressUtilsList.add(position, text);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mDataSet.size());
    }

    // 刪除item
    public void delectData(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }
}
