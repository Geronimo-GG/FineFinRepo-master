package dariabeliaeva.diploma.com.finefin.charts;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dari on 5/10/2016.
 */
public class DataFiller {

    public PieData generatePieData(Map<String, String> data) {

        int count = 4;

        ArrayList<Entry> entries1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<>(data.keySet());

        for(int i = 0; i < xVals.size(); i++) {
            if (Float.parseFloat(data.get(xVals.get(i))) > 0)
            entries1.add(new Entry(Float.parseFloat(data.get(xVals.get(i))), i));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Categories");
        ds1.setColors(ColorTemplate.PASTEL_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(xVals, ds1);
        //d.setValueTypeface(tf);

        return d;
    }
}
