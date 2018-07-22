package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropTransformation;

public class FragmentTvShowDetails extends Fragment {
    private TextView title;
    private TextView overview;
    private ImageView poster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_show_details, container, false);

        title = (TextView) v.findViewById(R.id.tvShowTitleDetails);
        overview = (TextView) v.findViewById(R.id.tvShowOverviewDetails);
        poster = (ImageView) v.findViewById(R.id.ivShowThumbnailDetails);

        Bundle bundle = this.getArguments();
        TvShow show = (TvShow) bundle.getSerializable("show");
        title.setText(show.getTitle());
        overview.setText(show.getOverview());
        Picasso.get().load(show.getPoster()).transform(new CropTransformation(poster.getWidth(), 300)).into(poster);

        return v;
    }
}
