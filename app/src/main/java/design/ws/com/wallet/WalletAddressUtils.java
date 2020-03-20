package design.ws.com.wallet;

import java.io.Serializable;


public class WalletAddressUtils {
    private String mTransAddress;// 交易wallet address
    private String mTransBlance;
    private String mTimeData;

    public WalletAddressUtils(String address, String blance, String time) {
        this.mTransAddress = address;
        this.mTransBlance = blance;
        this.mTimeData = time;
    }

    public String getTransAddress() {
        return mTransAddress;
    }

    public void setTransAddress(String mTransAddress) {
        this.mTransAddress = mTransAddress;
    }

    public String getTransBlance() {
        return mTransBlance;
    }

    public void setTransBlance(String mTransBlance) {
        this.mTransBlance = mTransBlance;
    }

    public String getTimeData() {
        return mTimeData;
    }

    public void setTimeData(String mTimeData) {
        this.mTimeData = mTimeData;
    }
}