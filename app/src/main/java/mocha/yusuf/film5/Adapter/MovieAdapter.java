package mocha.yusuf.film5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import mocha.yusuf.film5.Activity.DetailMovie;
import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movies;

    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movie_title;
        private TextView movie_description;
        private ImageView movie_image;
        private RelativeLayout movie;

        ViewHolder(View view) {
            super(view);
            movie_title = view.findViewById(R.id.movie_title);
            movie_description = view.findViewById(R.id.movie_description);
            movie_image = view.findViewById(R.id.movie_image);
            movie = view.findViewById(R.id.movie);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MovieModel movie = this.movies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.API_IMAGE +movie.getPoster_path())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.movie_image);
        holder.movie_title.setText(movie.getTitle());
        holder.movie_description.setText(movie.getOverview());
        holder.movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithObjectIntent = new Intent(context, DetailMovie.class);
                moveWithObjectIntent.putExtra(DetailMovie.EXTRA_MOVIE, movies.get(holder.getAdapterPosition()));
                context.startActivity(moveWithObjectIntent);
            }
        });
    }

    public void _notifyItemRangeRemoved(int i,int size){
        notifyItemRangeRemoved(i,size);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}
