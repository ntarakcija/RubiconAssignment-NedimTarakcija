package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class SearchTvShows extends IntentService {
    private static final String TAG = "ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija";
    public static final int SERVICE_STARTED = 1;
    public static final int SERVICE_FINISHED = 2;
    public static final int SERVICE_ERROR = 0;

    public SearchTvShows() {
        super("SearchShows");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        receiver.send(SERVICE_STARTED, Bundle.EMPTY);

        ArrayList<TvShow> shows = new ArrayList<>();
        String base = "https://api.themoviedb.org/3/search/tv?api_key=e768697e64a1a5079ec23d9fc0f150d7&language=en-US&query=";
        String query = intent.getStringExtra("query");
        String attributes = "&page=1&include_adult=false";
        String link​ = base + query + attributes;
        try {
            URL url = new URL(link​);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            InputStream is = urlConnection.getInputStream();
            StreamToStringConverter converter = new StreamToStringConverter();
            String result = converter.convertStreamToString(is);

            JSONObject jo = new JSONObject(result);
            JSONArray items = jo.optJSONArray("results");

            int length = items.length(); // Faster for arrays that contains a lot of elements
            for (int i = 0; i < length; i++) {
                if(i == 10) break;
                JSONObject tvShow = items.optJSONObject(i);
                String title = tvShow.optString("original_name");
                String overview = tvShow.optString("overview");
                String posterWidth = "500";
                String poster = "http://image.tmdb.org/t/p/w" + posterWidth + "/" + tvShow.optString("poster_path");
                shows.add(new TvShow(title, overview, poster));
            }

            Bundle bundle = new Bundle();
            bundle.putSerializable("shows", shows);
            receiver.send(SERVICE_FINISHED, bundle);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
