package com.example.nikitaverma.zomato.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.model.get_list_of_categories.Category;
import com.example.nikitaverma.zomato.view.common.MainActivity;

import java.util.List;

/**
 * Adapter class for category List of Navigation Menu
 */
public class CategoryListForNavigationMenuAdapter extends RecyclerView.Adapter<CategoryListForNavigationMenuAdapter.CategoryListForNavigationMenuHolder> {
    private final List<Category> mCategoryList;
    private final Context mContext;
    private final MainActivity mMainActivity;

    public CategoryListForNavigationMenuAdapter(List<Category> categoryList, Context context) {
        this.mCategoryList = categoryList;
        this.mContext = context;
        this.mMainActivity = (MainActivity) context;
    }

    /**
     * Inflate xml file to java
     *
     * @param parent
     * @param viewType
     * @return holder
     */
    @NonNull
    @Override
    public CategoryListForNavigationMenuAdapter.CategoryListForNavigationMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_view_content, parent, false);

        return new CategoryListForNavigationMenuHolder(itemView);
    }

    /**
     * Bind each data to view
     *
     * @param holder
     * @param position of holding item
     */
    @Override
    public void onBindViewHolder(@NonNull final CategoryListForNavigationMenuAdapter.CategoryListForNavigationMenuHolder holder, int position) {
        holder.categoryList.setText("" + mCategoryList.get(position).getCategories().getName());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                mMainActivity.listOfNavigationClickedData(mCategoryList.get(position).getCategories().getName());

            }
        });
    }
    //  }

    /**
     * @return size of listView
     */
    @Override
    public int getItemCount() {
        if (mCategoryList != null)
            return mCategoryList.size();
        else
            return 0;
    }

    /**
     * holder to each object in view
     */
    public class CategoryListForNavigationMenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView categoryList;
        private ItemClickListener clickListener;

        CategoryListForNavigationMenuHolder(View view) {
            super(view);
            categoryList = view.findViewById(R.id.category_textview);
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
