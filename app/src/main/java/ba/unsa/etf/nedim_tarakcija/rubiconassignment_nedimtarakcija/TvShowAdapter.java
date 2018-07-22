package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TvShowAdapter extends ArrayAdapter<TvShow> {
    public TvShowAdapter(@NonNull Context context, int resource, ArrayList<TvShow> shows) {
        super(context, resource, shows);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.tvshow_row, null);
        }

        TextView title = (TextView) v.findViewById(R.id.tvShowTitle);
        ImageView poster = (ImageView) v.findViewById(R.id.ivShowThumbnail);

        TvShow show = getItem(position);
        title.setText(show.getTitle());
        Picasso.get().load(show.getPoster()).into(poster);
        Picasso.get().load(show.getPoster()).transform(new CropCircleTransformation()).into(poster);

        return  v;
    }
}
