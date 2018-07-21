package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

public class FetchMovies extends IntentService {

    private static final String TAG = "ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija";

    public FetchMovies() {
        super("FetchMovies");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        receiver.send(1, Bundle.EMPTY);

        Bundle bundle = new Bundle();
        bundle.putString("string", "Hello! I'm extra.");
        receiver.send(2, bundle);

        Log.i(TAG, "Service started!");
    }
}
