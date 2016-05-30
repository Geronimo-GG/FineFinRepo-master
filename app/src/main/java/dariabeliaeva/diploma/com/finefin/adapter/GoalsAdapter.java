package dariabeliaeva.diploma.com.finefin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.R;

/**
 * Created by user on 5/29/16.
 */
public class GoalsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY_VIEW = 1;
    private static final int DEFAULT_VIEW = 2;

    private Map<String, String> goals = new HashMap<>();
    private Context context;

    public GoalsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (goals.isEmpty()) {
            return EMPTY_VIEW;
        } else {
            return DEFAULT_VIEW;
        }
    }

    public Map<String, String> getGoals() {
        return goals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case EMPTY_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_empty_view, parent, false);
                return new EmptyViewHolder(view);
            case DEFAULT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_list_item, parent, false);
                return new GoalsViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_list_item, parent, false);
                return new GoalsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GoalsViewHolder){
            setupItemView((GoalsViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return goals.size() > 0 ? goals.size() : 1;
    }

    private void setupItemView(GoalsViewHolder holder, int position) {
        ArrayList<String> keys = new ArrayList<>(goals.keySet());
        holder.todo_text_view.setText(keys.get(position));
        holder.price_text_view.setText(goals.get(keys.get(position)));
    }

    public void setCategories(Map<String, String> categories) {
        this.goals = categories;
        notifyDataSetChanged();
    }

    public void add(String catName, String catPrice){
        goals.put(catName, catPrice);
        notifyDataSetChanged();
    }

    static class GoalsViewHolder extends RecyclerView.ViewHolder{
        private TextView todo_text_view, price_text_view;

        public GoalsViewHolder(View itemView) {
            super(itemView);
            todo_text_view = (TextView) itemView.findViewById(R.id.todo_text_view);
            price_text_view = (TextView) itemView.findViewById(R.id.price_text_view);

        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
