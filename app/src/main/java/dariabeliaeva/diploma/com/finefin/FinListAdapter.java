package dariabeliaeva.diploma.com.finefin;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import io.realm.Realm;

/**
 * Created by andrew on 28.03.16.
 */
public class FinListAdapter extends RecyclerView.Adapter<FinListAdapter.SpendingsViewHolder> {

    private Realm realm = Realm.getDefaultInstance();


    ArrayList<Spendings> spendings = new ArrayList<>();
    private static final int[] COLORS = new int[] {
            Color.argb(40, 128, 0, 0),
            Color.argb(40, 255, 0, 0),
            Color.argb(40, 0, 128, 128),
            Color.argb(40, 112,128,144),
            Color.argb(40, 255,239,213),
            Color.argb(40, 72,209,204)
    };


    FinListAdapter(){}

    @Override
    public SpendingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), R.layout.todo_text_view, null);
        return new SpendingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SpendingsViewHolder holder, int position) {
        String formated, formated_today;
        Date date, date_today;
        Spendings spenItem = spendings.get(position);
        holder.spenTextView.setText(spenItem.getName());
        date = spenItem.getDate();
        //date_today = ;

        formated = DateFormat.getDateInstance().format(date);
        if(spenItem.getPrice() > 0) {
            holder.priceTextView.setText("+" + spenItem.getPrice());
            holder.priceTextView.setTextColor(Color.argb(255, 0, 50, 0));

        }
        else {
            holder.priceTextView.setText(spenItem.getPrice() + "");
            holder.priceTextView.setTextColor(Color.argb(255, 102, 0, 0));
        }
        holder.catTextView.setText(spenItem.getCategory());
        holder.dateTextView.setText(formated);
        holder.relativeLayout.setBackgroundColor(
                COLORS[(int) (spenItem.getId() % COLORS.length)]
        );
    }

    @Override
    public int getItemCount() {
        return spendings.size();
    }

    public ArrayList<Spendings> getSpendings() {
        return spendings;
    }

    public void setSpendings(ArrayList<Spendings> spendings) {
        this.spendings = spendings;
        notifyItemRangeChanged(0, spendings.size());
    }

    public void removeSpending(int position){
        this.spendings.remove(position);
        notifyItemRemoved(position);
    }

    public void addSpending(Spendings spending){
        this.spendings.add(spending);
        notifyItemInserted(spendings.indexOf(spending));
    }

    class SpendingsViewHolder extends RecyclerView.ViewHolder {

        public TextView spenTextView;
        public TextView priceTextView;
        public TextView catTextView;
        public RelativeLayout relativeLayout;
        public TextView dateTextView;

        public SpendingsViewHolder(View rootView) {
            super(rootView);
            this.spenTextView = (TextView) rootView.findViewById(R.id.textView);
            this.priceTextView = (TextView) rootView.findViewById(R.id.price_text_view);
            this.catTextView = (TextView) rootView.findViewById(R.id.todo_text_view);
            this.relativeLayout = (RelativeLayout) rootView.findViewById(R.id.list_cell);
            this.dateTextView = (TextView) rootView.findViewById(R.id.date_text_view);
        }
    }

   public long UpdateBalance() {
        long rezTotal;
        rezTotal = realm.where(Spendings.class).sum("price").longValue();
        return rezTotal;
    }
}
