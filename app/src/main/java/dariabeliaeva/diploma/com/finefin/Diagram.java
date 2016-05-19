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

public class Diagram extends Fragment {


    public Diagram() {
        // Required empty public constructor
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_diagram, container, false);
        setChartData();
        return rootView;
    }


    public void setChartData() {
        CategoriesDAO cat = new CategoriesDAO();
        Map data = new HashMap();
        SpendingsDAO spendingsDAO = new SpendingsDAO();
        List<String> names = new ArrayList<>();
        DataFiller df = new DataFiller();
        names = cat.getCatNamesOnly();
        //data = cat.getSumByCategory();

        /*ArrayList<Entry> cats = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            long sum = spendingsDAO.sumByCat(name);
            cats.add(new Entry((float) sum, i));
        }


        PieDataSet pieDataSet = new PieDataSet(cats, "Zero");
//        pieDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//        PieDataSet pieData1 = new PieDataSet(cats1, "One");
//        pieData1.setAxisDependency(YAxis.AxisDependency.LEFT);

        PieChart chart = (PieChart) rootView.findViewById(R.id.chart);
        //chart.setData(pieData);
        //ArrayList<IPieDataSet> dataSets = new ArrayList<IPieDataSet>();
        //dataSets.add(pieDataSet);
        //dataSets.add(pieData1);

//        ArrayList<String> xVals = new ArrayList<>();


        PieData values = new PieData(names, pieDataSet);
        //data.setDataSet(); */
        PieChart mChart = (PieChart) rootView.findViewById(R.id.chart);
        mChart.setDescription("");

//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

//        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText("Hello");
        mChart.setCenterTextSize(10f);
//        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(15f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mChart.setData(df.generatePieData());


        //chart.invalidate();

    }




}
