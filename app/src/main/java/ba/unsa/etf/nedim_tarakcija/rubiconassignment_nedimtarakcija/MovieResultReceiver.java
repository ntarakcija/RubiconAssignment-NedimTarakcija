package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class MovieResultReceiver extends ResultReceiver {
    private FragmentMovies receiver;

    public void setReceiver(FragmentMovies receiver) {
        this.receiver = receiver;
    }

    public MovieResultReceiver(Handler handler) {
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
