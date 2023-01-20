package com.alexsirbu.tripit.domain;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsirbu.tripit.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private Long id;
    private final ImageView imageViewPhoto;
    private final TextView textViewTitle;
    private final TextView textViewDestination;
    private final TextView textViewPrice;
    private final ImageButton favouriteButton;
    private final AppCompatImageButton readOnlyButton;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
        textViewTitle = itemView.findViewById(R.id.editTextTitle);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        favouriteButton = itemView.findViewById(R.id.imageButtonFavourite);
        readOnlyButton = itemView.findViewById(R.id.imageButtonRo);
        itemView.setClickable(true);
    }

    public AppCompatImageButton getReadOnlyButton() {
        return readOnlyButton;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageButton getFavouriteButton() {
        return favouriteButton;
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
