package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class TvShowResultReceiver extends ResultReceiver{
    private FragmentTvShows receiver;

    public void setReceiver(FragmentTvShows receiver) {
        this.receiver = receiver;
    }

    public TvShowResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if(receiver != null) {
            receiver.processData(resultCode, resultData);
        }
    }
}
