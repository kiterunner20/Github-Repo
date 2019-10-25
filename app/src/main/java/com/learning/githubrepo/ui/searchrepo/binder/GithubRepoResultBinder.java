package com.learning.githubrepo.ui.searchrepo.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.learning.githubrepo.R;
import com.learning.githubrepo.model.GithubRepoData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubRepoResultBinder extends ItemBinder<GithubRepoData, GithubRepoResultBinder.ViewHolder> {

    GithubRepoResultAdapter.ItemSelectedListener listener;

    public GithubRepoResultBinder(GithubRepoResultAdapter.ItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public GithubRepoResultBinder.ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        return new GithubRepoResultBinder.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false));
    }

    @Override
    public void bind(GithubRepoResultBinder.ViewHolder holder, GithubRepoData item) {


        if (item.name() != null) {
            if (item.name().equalsIgnoreCase("kotlin")) {
            }
            holder.tvRepoTitle.setText(item.name());
        }
        if (item.description() != null) {
            holder.tvRepoDescription.setText(item.description());
        }
        if (item.language() != null) {
            holder.tvLanguage.setText(item.language());
        }
        holder.tvStartCount.setText("Starred " + String.valueOf(item.starCount()));
        if (item.owner() != null) {
            holder.tvOwnerName.setText(item.owner());
        }

    }


    @Override
    public boolean canBindData(Object item) {
        return item instanceof GithubRepoData;
    }

    public class ViewHolder extends ItemViewHolder<GithubRepoData> {

        @BindView(R.id.cv_repo_details)
        CardView cvRepoList;
        @BindView(R.id.tv_repo_title)
        TextView tvRepoTitle;
        @BindView(R.id.tv_language)
        TextView tvLanguage;
        @BindView(R.id.tv_owner_name)
        TextView tvOwnerName;
        @BindView(R.id.tv_star_count)
        TextView tvStartCount;
        @BindView(R.id.tv_repo_desc)
        TextView tvRepoDescription;
        @BindView(R.id.btn_fav)
        Button btnFav;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cvRepoList.setOnClickListener(v -> {
                listener.onItemSelected(getItem());
            });

            btnFav.setOnClickListener(v -> {
                listener.onFavClicked(getItem());
            });

        }

    }
}
