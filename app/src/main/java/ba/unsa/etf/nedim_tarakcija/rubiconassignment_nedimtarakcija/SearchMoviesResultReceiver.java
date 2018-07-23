package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

public class SearchMoviesResultReceiver extends android.os.ResultReceiver {

    private FragmentSearchMovies receiver;

    public void setReceiver(FragmentSearchMovies receiver) {
        this.receiver = receiver;
    }

    public SearchMoviesResultReceiver(Handler handler) {
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
