package com.sbrt.ponomarev.animal.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sbrt.ponomarev.animal.R;
import com.sbrt.ponomarev.animal.bean.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QuickNick
 */
public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {

    private List<Animal> mAnimals;
    private Context mContext;

    public AnimalsAdapter(Context context) {
        mAnimals = new ArrayList<>();
        mContext = context;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        itemView.setOnClickListener((View.OnClickListener) mContext);
        return new AnimalViewHolder(itemView);
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

    public Animal getAnimal(int position) {
        return mAnimals.get(position);
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mSpeciesTextView;
        private final TextView mWeightTextView;
        private final TextView mHeightTextView;

        AnimalViewHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
            mSpeciesTextView = (TextView) itemView.findViewById(R.id.species);
            mWeightTextView = (TextView) itemView.findViewById(R.id.weight);
            mHeightTextView = (TextView) itemView.findViewById(R.id.height);
        }

        void bindView(Animal animal) {
            mNameTextView.setText(mContext.getString(R.string.name, animal.getName()));
            mSpeciesTextView.setText(mContext.getString(R.string.species, animal.getSpecies()));
            mWeightTextView.setText(mContext.getString(R.string.weight, animal.getWeight()));
            mHeightTextView.setText(mContext.getString(R.string.height, animal.getHeight()));
        }
    }
}
