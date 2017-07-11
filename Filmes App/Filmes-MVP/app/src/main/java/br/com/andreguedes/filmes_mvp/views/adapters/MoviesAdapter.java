package br.com.andreguedes.filmes_mvp.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.andreguedes.filmes_mvp.R;
import br.com.andreguedes.filmes_mvp.helpers.ImageHelper;
import br.com.andreguedes.filmes_mvp.pojo.Movie;

/**
 * Created by andreguedes on 25/06/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> filmes) {
        this.context = context;
        this.movies = filmes;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list_layout, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.setFilmes(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public void updateMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFilme;
        private TextView txtNome;

        MoviesViewHolder(View itemView) {
            super(itemView);

            imgFilme = (ImageView) itemView.findViewById(R.id.imgFilme);
            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
        }

        private void setFilmes(Movie movie) {
            if (movie != null) {
                ImageHelper.loadImages(context, movie.getCoverUrl(), imgFilme);
                txtNome.setText(movie.getTitle());
            }
        }

    }

}