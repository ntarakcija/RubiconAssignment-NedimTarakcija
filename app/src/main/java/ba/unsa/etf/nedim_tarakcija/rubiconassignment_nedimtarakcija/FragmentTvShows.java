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

import java.util.ArrayList;

public class FragmentTvShows extends Fragment implements IFetch {
    private ListView showsList;
    private ArrayList<TvShow> shows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        TvShowResultReceiver receiver = new TvShowResultReceiver(new Handler());
        receiver.setReceiver(FragmentTvShows.this);

        Intent intent = new Intent(getActivity(), FetchTvShows.class);
        intent.putExtra("receiver", receiver);
        intent.putExtra("type", "movies");
        getActivity().startService(intent);

        showsList = v.findViewById(R.id.lvShows);
        showsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                android.support.v4.app.Fragment f = new FragmentTvShowDetails();
                Bundle bundle = new Bundle();
                bundle.putSerializable("show", shows.get(position));
                f.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment, (android.support.v4.app.Fragment) f);
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
                    shows = (ArrayList<TvShow>) resultData.getSerializable("movies");
                    TvShowAdapter adapter = new TvShowAdapter(getContext(), R.layout.tvshow_row, shows);
                    showsList.setAdapter(adapter);
                }
            default:
        }
    }
}
