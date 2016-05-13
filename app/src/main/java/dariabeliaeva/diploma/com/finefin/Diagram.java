package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.realm.implementation.RealmPieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.charts.DataFiller;
import dariabeliaeva.diploma.com.finefin.dao.CategoriesDAO;
import dariabeliaeva.diploma.com.finefin.dao.SpendingsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;


/**
 * A simple {@link Fragment} subclass.
 */
public class Diagram extends Fragment {

    private boolean outcomes;

    public Diagram() {
        // Required empty public constructor
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_diagram, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setChartData();

    }



    public void setChartData() {
        CategoriesDAO cat = new CategoriesDAO();
        SpendingsDAO spendingsDAO = new SpendingsDAO();
        Map<String, String> data = new HashMap<>();
        DataFiller df = new DataFiller();

        ArrayList<String> categoriesNames = cat.getCatNamesOnly();
        for(String categoryName : categoriesNames){
            data.put(categoryName, spendingsDAO.sumByCat(categoryName) + "");
        }


        PieChart mChart = (PieChart) rootView.findViewById(R.id.chart);
        mChart.setDescription("description");

        mChart.setCenterText("Hello");
        mChart.setCenterTextSize(10f);

        mChart.setHoleRadius(45);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mChart.setData(df.generatePieData(data, outcomes));



    }

    public boolean isOutcomes() {
        return outcomes;
    }

    public void setOutcomes(boolean outcomes) {
        this.outcomes = outcomes;
    }
}
