package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

public class FetchResultReceiver extends ResultReceiver {

    private static final String TAG = "ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija";
    private FragmentMovies receiver;

    public void setReceiver(FragmentMovies receiver) {
        this.receiver = receiver;
    }

    public FetchResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if(receiver != null) {
            receiver.processReceivedData(resultCode, resultData);
        }
    }
}
