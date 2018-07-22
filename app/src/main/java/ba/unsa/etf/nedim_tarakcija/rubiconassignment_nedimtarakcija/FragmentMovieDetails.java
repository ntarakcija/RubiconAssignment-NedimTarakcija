package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;

public class FragmentMovieDetails extends Fragment {
    private TextView title;
    private TextView overview;
    private ImageView poster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_details, container, false);

        title = (TextView) v.findViewById(R.id.tvMovieTitleDetails);
        overview = (TextView) v.findViewById(R.id.tvMovieOverviewDetails);
        poster = (ImageView) v.findViewById(R.id.ivMovieThumbnailDetails);

        Bundle bundle = this.getArguments();
        Movie movie = (Movie) bundle.getSerializable("movie");
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        Picasso.get().load(movie.getPoster()).transform(new CropTransformation(poster.getWidth(), 300)).into(poster);

        return v;
    }
}
