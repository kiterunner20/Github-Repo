package com.learning.githubrepo.ui.favorite.binder;

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
import com.learning.githubrepo.model.db.FavoriteRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteRepoBinder extends ItemBinder<FavoriteRepo, FavoriteRepoBinder.ViewHolder> {

    FavoriteRepoAdapter.ItemSelectedListener listener;

    public FavoriteRepoBinder(FavoriteRepoAdapter.ItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public FavoriteRepoBinder.ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        return new FavoriteRepoBinder.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_list, parent, false));
    }

    @Override
    public void bind(FavoriteRepoBinder.ViewHolder holder, FavoriteRepo item) {


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
        return item instanceof FavoriteRepo;
    }

    public class ViewHolder extends ItemViewHolder<FavoriteRepo> {

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
        @BindView(R.id.btn_unf)
        Button unFav;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cvRepoList.setOnClickListener(v -> {
                listener.onItemSelected(getItem());
            });

            unFav.setOnClickListener(v -> {
                listener.onFavClicked(getItem());
            });

        }

    }
}
