package design.ws.com.wallet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;


public class WalletAddressUtils implements Serializable {
    private String mIsTrue;// 是否回傳成功
    private String mtransAddress;// 交易wallet address
    private String mTimeData;

    public WalletAddressUtils(String address) {
        this.mtransAddress = address;
    }

    public String getIsTrue() {
        return mIsTrue;
    }

    public void setIsTrue(String isTrue) {
        this.mIsTrue = isTrue;
    }

    public String getTransAddress() {
        return mtransAddress;
    }

    public void setTransAddress(String transAddress) {
        this.mtransAddress = transAddress;
    }

    public String getTimeData() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.mTimeData = fmt.format(mTimeData);
        return mTimeData;
    }
}