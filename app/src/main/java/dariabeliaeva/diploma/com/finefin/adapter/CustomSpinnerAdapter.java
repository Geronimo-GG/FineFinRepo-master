package dariabeliaeva.diploma.com.finefin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dariabeliaeva.diploma.com.finefin.R;

/**
 * Created by andrew on 13.05.16.
 */
public class CustomSpinnerAdapter extends BaseAdapter {

    ArrayList<String> items = new ArrayList<>();
    Context context;

    public CustomSpinnerAdapter(Context context, ArrayList<String> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.spinner_item, null).findViewById(R.id.tvText);
        tv.setText(items.get(i));
        return tv;
    }
}
