package dariabeliaeva.diploma.com.finefin;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dariabeliaeva.diploma.com.finefin.data_models.Categories;

/**
 * Created by andrew on 28.03.16.
 */
public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CategoriesViewHolder> {

    Categories selected;

    ArrayList<Categories> cats = new ArrayList<>();

    CatListAdapter(){}

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), R.layout.cat_text_view, null);

        return new CategoriesViewHolder(v);
    }

    public Categories getSelected() {
        return selected;
    }

    @Override
    public void onBindViewHolder(final CategoriesViewHolder holder, final int position) {
        Categories catItems = cats.get(position);
        holder.spenTextView.setText(catItems.getCat_name());
        holder.image.setImageResource(catItems.getIcon_id());
//
        if (selected == cats.get(position))
        {
            holder.image.setBackgroundResource(R.drawable.gr_circle);
            holder.image.setImageResource(R.drawable.ic_checkmark_white);
            holder.spenTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.base_green));

        }
        else {
            holder.image.setBackgroundResource(R.drawable.circle);
            holder.spenTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.base_gray));


        }

        holder.image.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected == cats.get(position)) {
                            selected = null;
                        }else{
                            selected = cats.get(position);
                        }
                        notifyDataSetChanged();
                    }
                }
        );
        //holder.itemView.getLayoutParams().height = holder.itemView.getWidth();


    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public ArrayList<Categories> getCats() {
        return cats;
    }

    public void setCats(ArrayList<Categories> cats) {
        this.cats = cats;
        notifyItemRangeChanged(0, cats.size());
    }

    public void removeCats(int position){
        this.cats.remove(position);
        notifyItemRemoved(position);
    }

    public void addCats(Categories cat){
        this.cats.add(cat);
        notifyItemInserted(cats.indexOf(cat));
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        public TextView spenTextView;
//        public CardView spenCardView;
        public ImageView image;
        public CategoriesViewHolder(View rootView) {
            super(rootView);
            this.spenTextView = (TextView) rootView.findViewById(R.id.todo_text_view);
//            this.spenCardView = (CardView) rootView.findViewById(R.id.cvCat);
            this.image = (ImageView) rootView.findViewById(R.id.imageView2);
        }
    }
}
