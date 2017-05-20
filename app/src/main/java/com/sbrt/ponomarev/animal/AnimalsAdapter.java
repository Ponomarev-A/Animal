package com.sbrt.ponomarev.animal;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QuickNick
 */
public class AnimalsAdapter extends RecyclerView.Adapter<AnimalViewHolder> {

    private List<Animal> mAnimals;

    public AnimalsAdapter() {
        this.mAnimals = new ArrayList<>();
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AnimalViewHolder.newHolder(parent);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.bindView(mAnimals.get(position));
    }


    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public void setAnimals(List<Animal> animals) {
        mAnimals.clear();
        mAnimals.addAll(animals);
        notifyDataSetChanged();
    }
}
