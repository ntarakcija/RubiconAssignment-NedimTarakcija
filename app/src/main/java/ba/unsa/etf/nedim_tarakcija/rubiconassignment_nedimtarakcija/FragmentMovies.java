package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMovies extends Fragment implements IFetch {
    private ListView moviesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        MovieResultReceiver receiver = new MovieResultReceiver(new Handler());
        receiver.setReceiver(FragmentMovies.this);

        Intent intent = new Intent(getActivity(), FetchMovies.class);
        intent.putExtra("receiver", receiver);
        getActivity().startService(intent);

        moviesList = v.findViewById(R.id.lvMovies);

        return v;
    }

    @Override
    public void processData(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case FetchMovies.SERVICE_STARTED:
            case FetchMovies.SERVICE_FINISHED:
                if(resultData != Bundle.EMPTY) {
                    ArrayList<Movie> movies = (ArrayList<Movie>) resultData.getSerializable("movies");
                    MovieAdapter adapter = new MovieAdapter(getContext(), R.layout.movie_row, movies);
                    moviesList.setAdapter(adapter);
                }
            default:
        }
    }
}
