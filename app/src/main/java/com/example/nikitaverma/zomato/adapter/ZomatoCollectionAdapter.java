package com.example.nikitaverma.zomato.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city.Collections;
import com.example.nikitaverma.zomato.utility.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter class for Zomato Collection of mainActivity
 */
public class ZomatoCollectionAdapter extends RecyclerView.Adapter<ZomatoCollectionAdapter.ZomatoCollectionViewHolder> implements Constants {

    private final List<Collections> mCollectionsList;
    private final Context mContext;
    private int mResource;

    public ZomatoCollectionAdapter(List<Collections> collectionsList, Context context, int resource) {
        this.mCollectionsList = collectionsList;
        this.mContext = context;
        this.mResource = resource;
    }

    /**
     * Inflate xml file to java
     *
     * @param parent
     * @param viewType
     * @return type of view holder
     */
    @NonNull
    @Override
    public ZomatoCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mResource, parent, false);

        return new ZomatoCollectionViewHolder(itemView);
    }

    /**
     * Bind each data to view
     *
     * @param holder
     * @param position of holder
     */
    @Override
    public void onBindViewHolder(@NonNull final ZomatoCollectionViewHolder holder, int position) {
        holder.title.setText(mCollectionsList.get(position).getCollection().getTitle());
        if (!(mCollectionsList.get(position).getCollection().getImageUrl().isEmpty())) {
            Picasso.with(mContext).load(mCollectionsList.get(position).getCollection().getImageUrl())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .noFade().resize(200, 200)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onError() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                    });
        } else {
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher_foreground));
            holder.progressBar.setVisibility(View.GONE);
        }

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(mContext, mCollectionsList.get(position).getCollection().getTitle() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @return size of listView
     */
    @Override
    public int getItemCount() {
        if (mCollectionsList != null)
            return mCollectionsList.size();
        else
            return 0;
    }

    /**
     * holder to each object in view
     */
    public class ZomatoCollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView imageView;
        private ProgressBar progressBar;
        private ItemClickListener clickListener;

        ZomatoCollectionViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.image_view_col);
            progressBar = view.findViewById(R.id.progressbar);
            view.setOnClickListener(this);
        }

        private void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition());
        }
    }

}
