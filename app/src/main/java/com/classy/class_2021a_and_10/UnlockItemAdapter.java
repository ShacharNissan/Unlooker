package com.classy.class_2021a_and_10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UnlockItemAdapter  extends RecyclerView.Adapter<UnlockItemAdapter.UnlockItemHolder> {
    private ArrayList<UnlockItem> items;

    public static class UnlockItemHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mTextView;

        public UnlockItemHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.unlock_item_img);
            mTextView = itemView.findViewById(R.id.unlock_item_text);
        }
    }

    public UnlockItemAdapter(ArrayList<UnlockItem> items){
        this.items = items;
    }

    @Override
    public UnlockItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unlock_date_item,parent, false);
        UnlockItemHolder holder = new UnlockItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(UnlockItemHolder holder, int position) {
        UnlockItem currentItem = this.items.get(position);

        holder.mTextView.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
