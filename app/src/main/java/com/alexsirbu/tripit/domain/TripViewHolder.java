package com.alexsirbu.tripit.domain;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsirbu.tripit.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewPhoto;
    private final TextView textViewTitle;
    private final TextView textViewDestination;
    private final TextView textViewPrice;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
    }

    public ImageView getImageViewPhoto() {
        return imageViewPhoto;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public TextView getTextViewPrice() {
        return textViewPrice;
    }
}
