package dariabeliaeva.diploma.com.finefin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dariabeliaeva.diploma.com.finefin.data_models.Budget;

/**
 * Created by Dari on 5/17/2016.
 */
public class BudgetListAdapter {

    class BudgetViewHolder extends RecyclerView.ViewHolder {

        public TextView spenTextView;
        public TextView priceTextView;
        public TextView catTextView;
        public RelativeLayout relativeLayout;
        public TextView dateTextView;

        public BudgetViewHolder(View rootView) {
            super(rootView);
            this.spenTextView = (TextView) rootView.findViewById(R.id.textView);
            this.priceTextView = (TextView) rootView.findViewById(R.id.price_text_view);
            this.catTextView = (TextView) rootView.findViewById(R.id.todo_text_view);
            this.relativeLayout = (RelativeLayout) rootView.findViewById(R.id.list_cell);
//            this.dateTextView = (TextView) rootView.findViewById(R.id.date_text_view);
        }
    }
}
