package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FetchData extends IntentService {

    private static final String TAG = "ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija";
    public static final int SERVICE_STARTED = 1;
    public static final int SERVICE_FINISHED = 2;
    public static final int SERVICE_ERROR = 0;

    public FetchData() {
        super("FetchData");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        receiver.send(SERVICE_STARTED, Bundle.EMPTY);

        if(intent.getStringExtra("type").equals("movies")) {
            ArrayList<Movie> movies = new ArrayList<>();
            String link​ = "https://api.themoviedb.org/3/movie/top_rated?api_key=e768697e64a1a5079ec23d9fc0f150d7&language=en-US&page=1";

            try {
                URL url = new URL(link​);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                InputStream is = urlConnection.getInputStream();
                String result = convertStreamToString(is);

                JSONObject jo = new JSONObject(result);
                JSONArray items = jo.optJSONArray("results");

                int length = items.length(); // Faster for arrays that contains a lot of elements
                for (int i = 0; i < length; i++) {
                    if(i == 10) break;
                    JSONObject movie = items.optJSONObject(i);
                    String title = movie.optString("title");
                    String overview = movie.optString("overview");
                    String posterWidth = "500";
                    String poster = "http://image.tmdb.org/t/p/w" + posterWidth + "/" + movie.optString("poster_path");
                    movies.add(new Movie(title, overview, poster));
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("movies", movies);
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

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
