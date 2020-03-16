package design.ws.com.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gomobile4wallet.Gomobile4wallet;


public class Fragment_Second extends Fragment {

    private View view;
    private AutoCompleteTextView editBlances;
    private AutoCompleteTextView editWalletAddress;
//    private Button mSend;
    private FirstListAdapter mFirstListAdapter;

    public Fragment_Second() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        editBlances = view.findViewById(R.id.second_Blances);
        editWalletAddress = view.findViewById(R.id.second_WalletAddress);

//        mSend = view.findViewById(R.id.second_submit);
//        mSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getActivity().getSharedPreferences("blockAppFile", Context.MODE_PRIVATE);
//                String blockAddress = sp.getString("blockWallet", "not value");
//                String dollers = editBlances.getText().toString();
//                String account = editWalletAddress.getText().toString();
//
//                try {
//                    String result = Gomobile4wallet.fnNewTransaction(blockAddress, account, dollers);
//                    Log.d("測試 result", result);
//                    List<String> myList = new ArrayList<String>(Arrays.asList(result.split(",")));
//                    Log.d("測試 List : ", myList.toString());
//                    mFirstListAdapter.addData(myList.size());
//                    Toast.makeText(getActivity(), "交易成功", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(getActivity(), "交易失敗", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
