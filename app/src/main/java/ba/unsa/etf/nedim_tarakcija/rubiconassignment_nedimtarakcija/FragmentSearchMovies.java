package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentSearchMovies extends android.support.v4.app.Fragment implements IFetch {
    private ListView moviesList;
    private ArrayList<Movie> movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_movies, container, false);
        Bundle bundle = getArguments();

        SearchMoviesResultReceiver receiver = new SearchMoviesResultReceiver(new Handler());
        receiver.setReceiver(FragmentSearchMovies.this);

        Intent intent = new Intent(getActivity(), SearchMovies.class);
        intent.putExtra("receiver", receiver);
        intent.putExtra("query", bundle.getString("query"));
        getActivity().startService(intent);

        moviesList = v.findViewById(R.id.lvMoviesSearch);

        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                android.support.v4.app.Fragment f = new FragmentMovieDetails();
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movies.get(position));
                f.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment, f);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    @Override
    public void processData(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case FetchMovies.SERVICE_STARTED:
            case FetchMovies.SERVICE_FINISHED:
                if(resultData != Bundle.EMPTY) {
                    if(getContext() != null) {
                        movies = (ArrayList<Movie>) resultData.getSerializable("movies");
                        MovieAdapter adapter = new MovieAdapter(getContext(), R.layout.movie_row, movies);
                        moviesList.setAdapter(adapter);
                    }
                }
            default:
        }
    }
}
