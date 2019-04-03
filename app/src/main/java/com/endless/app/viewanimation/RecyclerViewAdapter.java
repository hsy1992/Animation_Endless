package com.endless.app.viewanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endless.app.R;

/**
 * @author haosiyuan
 * @date 2019/4/3 1:39 PM
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final int[] mColorIntArray;
    Context mContext;
    int mItemCount;

    public RecyclerViewAdapter(Context context, int itemCount) {
        this.mContext = context;
        this.mItemCount = itemCount;
        mColorIntArray = context.getResources().getIntArray(R.array.colors);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleritem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setBackgroundColor(mColorIntArray[i % mColorIntArray.length]);
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
