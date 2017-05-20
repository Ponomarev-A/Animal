package com.sbrt.ponomarev.animal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user15 on 20.05.2017.
 */

class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final TextView mNameTextView;
    private final TextView mSpeciesTextView;
    private final TextView mWeightTextView;
    private final TextView mHeightTextView;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        mNameTextView = (TextView) itemView.findViewById(R.id.name);
        mSpeciesTextView = (TextView) itemView.findViewById(R.id.species);
        mWeightTextView = (TextView) itemView.findViewById(R.id.weight);
        mHeightTextView = (TextView) itemView.findViewById(R.id.height);
    }

    public static AnimalViewHolder newHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new AnimalViewHolder(itemView);
    }

    public void bindView(Animal animal) {
        mNameTextView.setText(animal.getName());
        mSpeciesTextView.setText(animal.getSpecies());
        mWeightTextView.setText(String.valueOf(animal.getWeight()));
        mHeightTextView.setText(String.valueOf(animal.getHeight()));
    }
}
