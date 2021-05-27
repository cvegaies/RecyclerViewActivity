package org.izv.ad.recyclerviewactivity.view.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.ad.recyclerviewactivity.R;
import org.jetbrains.annotations.NotNull;

public class StringViewHolder extends RecyclerView.ViewHolder {

    private final TextView itemTextView;

    public StringViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        itemTextView = itemView.findViewById(R.id.tvString);
    }

    public TextView getItemTextView() {
        return itemTextView;
    }
}
