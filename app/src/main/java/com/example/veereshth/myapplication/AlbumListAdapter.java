package com.example.veereshth.myapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.veereshth.myapplication.databinding.AlbumListItemViewBinding;
import com.example.veereshth.myapplication.models.AlbumResponse;

import java.util.List;

public class AlbumListAdapter  extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder>  {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<AlbumResponse> albumResponseList;

    public AlbumListAdapter(Context context, List<AlbumResponse> albumResponseList) {
        this.context = context;
        this.albumResponseList = albumResponseList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        AlbumListItemViewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.album_list_item_view, viewGroup, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.albumListItem.text.setText(albumResponseList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return albumResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public AlbumListItemViewBinding albumListItem;

        public ViewHolder(AlbumListItemViewBinding albumListItemViewBinding) {
            super(albumListItemViewBinding.getRoot());
            albumListItem = albumListItemViewBinding;
        }
    }
    public void refreshData(List<AlbumResponse> albumResponseList) {
        this.albumResponseList = albumResponseList;

        notifyDataSetChanged();
    }

}
