package dariabeliaeva.diploma.com.finefin;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dariabeliaeva.diploma.com.finefin.dao.CategoriesDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import io.realm.Realm;


public class FinListAdapter extends RecyclerView.Adapter<FinListAdapter.SpendingsViewHolder> {

    private Realm realm = Realm.getDefaultInstance();

    ArrayList<Spendings> spendings = new ArrayList<>();
    private static final int[] COLORS = ColorTemplate.COLORFUL_COLORS;

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
        int icon_id;
        CategoriesDAO categoriesDAO = new CategoriesDAO();
        Spendings spenItem = spendings.get(position);
        holder.spenTextView.setText(spenItem.getName());
        icon_id = categoriesDAO.getIcon(spenItem.getCategory());
        date = spenItem.getDate();
        //date_today = ;

        formated = DateFormat.getDateInstance().format(date);

        if(spenItem.getPrice() > 0) {
            holder.priceTextView.setText("+" + spenItem.getPrice());
            holder.priceTextView.setTextColor(Color.parseColor("#00c27a"));

        }
        else {
            holder.priceTextView.setText(spenItem.getPrice() + "");
            holder.priceTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimary));
        }
        holder.catTextView.setText(spenItem.getCategory());
        holder.imageView.setImageResource(icon_id);
        holder.dateTextView.setText(formated);
//        holder.relativeLayout.setBackgroundColor(
//                COLORS[0]
//        );
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
        public ImageView imageView;
        public TextView dateTextView;

        public SpendingsViewHolder(View rootView) {
            super(rootView);
            this.spenTextView = (TextView) rootView.findViewById(R.id.textView);
            this.priceTextView = (TextView) rootView.findViewById(R.id.price_text_view);
            this.catTextView = (TextView) rootView.findViewById(R.id.todo_text_view);
            this.relativeLayout = (RelativeLayout) rootView.findViewById(R.id.list_cell);
            this.imageView = (ImageView) rootView.findViewById(R.id.imageView);
            this.dateTextView = (TextView) rootView.findViewById(R.id.tvDate);
        }
    }

   public long UpdateBalance() {
        long rezTotal;
        rezTotal = realm.where(Spendings.class).sum("price").longValue();
        return rezTotal;
    }
}
