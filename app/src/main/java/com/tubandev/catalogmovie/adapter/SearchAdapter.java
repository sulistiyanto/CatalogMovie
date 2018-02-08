package com.tubandev.catalogmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tubandev.catalogmovie.R;
import com.tubandev.catalogmovie.model.Result;

import java.util.List;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private Context mContext;
    private List<Result> resultList;
    private OnItemClickListener listener;

    public SearchAdapter(Context mContext, List<Result> resultList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.resultList = resultList;
        this.listener = listener;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.bindData(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public interface OnItemClickListener {
        void onItemClicked(Result result);
    }

    public class SearchHolder extends RecyclerView.ViewHolder {

        private ImageView imagePoster;
        private TextView txtTitle;
        private TextView txtOverView;
        private TextView txtDate;

        public SearchHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.imageView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtOverView = itemView.findViewById(R.id.txtOverView);
            txtDate = itemView.findViewById(R.id.txtDate);
        }

        public void bindData(final Result result) {
            Glide.with(mContext)
                    .load("http://image.tmdb.org/t/p/w185" + result.getPosterPath())
                    .into(imagePoster);

            txtTitle.setText(result.getTitle());
            txtOverView.setText(result.getOverview());
            txtDate.setText(result.getReleaseDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(result);
                }
            });
        }
    }
}
