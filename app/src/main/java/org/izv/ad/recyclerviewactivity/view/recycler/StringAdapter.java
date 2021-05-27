package org.izv.ad.recyclerviewactivity.view.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.ad.recyclerviewactivity.R;
import org.izv.ad.recyclerviewactivity.view.viewmodel.OnItemClickListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<StringViewHolder>{

    private ArrayList<String> localDataSet;
    private OnItemClickListener listener;

    public StringAdapter(ArrayList<String> localDataSet, OnItemClickListener listener) {
        this.localDataSet = localDataSet;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StringViewHolder holder, int position) {
        holder.getItemTextView().setText(localDataSet.get(position));
        holder.getItemTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v);
                //position, holder.getItemTextView()
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}