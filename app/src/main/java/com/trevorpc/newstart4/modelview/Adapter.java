package com.trevorpc.newstart4.modelview;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trevorpc.newstart4.R;
import com.trevorpc.newstart4.databinding.ItemLayoutBinding;
import com.trevorpc.newstart4.model.object.Response;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Response> responseList;


    public void upDateList(List<Response> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_layout,
                viewGroup,
                false);
        MyViewHolder holder = new MyViewHolder(itemLayoutBinding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Response response = responseList.get(i);
        myViewHolder.itemLayoutBinding.setResponse(response);

    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemLayoutBinding;

        public MyViewHolder(@NonNull ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }
}
