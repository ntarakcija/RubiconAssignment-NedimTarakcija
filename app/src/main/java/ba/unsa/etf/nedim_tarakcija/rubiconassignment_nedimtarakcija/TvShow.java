package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import java.io.Serializable;

public class TvShow implements Serializable {
    private String title;
    private String overview;
    private String poster;

    TvShow(String title, String overview, String poster) {
        this.title = title;
        this.overview = overview;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
