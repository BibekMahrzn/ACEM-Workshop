package com.rajesh.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rajesh.movieapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends BaseAdapter {

    ArrayList<Movie> movies = new ArrayList<>();
    LayoutInflater inflater;
    private final String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
    private Context mContext;


    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.mContext = context;
        this.movies = movies;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Movie movie = movies.get(position);
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.single_movie_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvMovieTitle.setText(movie.getTitle());
        Glide.with(mContext).load(getImageUri(movies.get(position).getPosterPath())).into(holder.imgPoster);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MovieDetailActivity.MOVIES_OBJECT, movies.get(position));
                intent.putExtra(MovieDetailActivity.MOVIE_OBJECT_BUNDLE, bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public void addData(List<Movie> list) {
        movies.clear();
        movies.addAll(list);
        notifyDataSetChanged();
    }


    public static class ViewHolder {

        TextView tvMovieTitle;
        ImageView imgPoster;
        View cardView;

        public ViewHolder(View view) {
            tvMovieTitle = (TextView) view.findViewById(R.id.tv_movie_title);
            imgPoster = (ImageView) view.findViewById(R.id.img_movie_poster);
            cardView = view.findViewById(R.id.card_view);
        }
    }

    public String getImageUri(String uri) {
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }
}
