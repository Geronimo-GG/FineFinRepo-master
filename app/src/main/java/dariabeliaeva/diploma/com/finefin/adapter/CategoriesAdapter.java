package dariabeliaeva.diploma.com.finefin.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dariabeliaeva.diploma.com.finefin.R;

/**
 * Created by andrew on 11.02.16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY_VIEW = 1;
    private static final int DEFAULT_VIEW = 2;

    private Map<String, String> categories = new HashMap<>();
    private int[] colors;
    private Context context;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (categories.isEmpty()) {
            return EMPTY_VIEW;
        } else {
            return DEFAULT_VIEW;
        }
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case EMPTY_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_empty_view, parent, false);
                return new EmptyViewHolder(view);
            case DEFAULT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
                return new CategoriesViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
                return new CategoriesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoriesViewHolder){
            setupItemView((CategoriesViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size() > 0 ? categories.size() : 1;
    }

    private void setupItemView(CategoriesViewHolder holder, int position) {
        ArrayList<String> keys = new ArrayList<>(categories.keySet());
        holder.tvCatName.setText(keys.get(position));
        holder.tvCatTotalPrice.setText(categories.get(keys.get(position)) + "$");
        try {
            holder.imageColor.setColorFilter(colors[position]);
        }catch (Exception e){
            holder.imageColor.setVisibility(View.GONE);
        }
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setColors(int[] colors){
        this.colors = colors;

    }

    public void add(String catName, String catPrice){
        categories.put(catName, catPrice);
        notifyDataSetChanged();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCatName, tvCatTotalPrice;
        private ImageView imageColor;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            tvCatName = (TextView) itemView.findViewById(R.id.tvCatName);
            tvCatTotalPrice = (TextView) itemView.findViewById(R.id.tvCatTotalPrice);
            imageColor = (ImageView) itemView.findViewById(R.id.imageColor);

        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
