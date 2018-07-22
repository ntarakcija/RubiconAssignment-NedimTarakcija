package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(@NonNull Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.movie_row, null);
        }

        TextView title = (TextView) v.findViewById(R.id.tvMovieTitle);
        ImageView poster = (ImageView) v.findViewById(R.id.ivMovieThumbnail);

        Movie movie = getItem(position);
        title.setText(movie.getTitle());
        //Picasso.get().load(movie.getPoster()).into(poster);
        Picasso.get().load(movie.getPoster()).transform(new CropCircleTransformation()).into(poster);

        return  v;
    }
}
