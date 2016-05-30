package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.adapter.CategoriesAdapter;
import dariabeliaeva.diploma.com.finefin.charts.DataFiller;
import dariabeliaeva.diploma.com.finefin.dao.CategoriesDAO;
import dariabeliaeva.diploma.com.finefin.dao.SpendingsDAO;

public class    Diagram extends Fragment {

    private boolean outcomes;
    private RecyclerView categoriesList;
    private float sum = 0;
    private Date date;

    public Diagram() {
        // Required empty public constructor
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_diagram, container, false);
        categoriesList = (RecyclerView) rootView.findViewById(R.id.categories_list);
        return rootView;
    }

    private void initCategoriesList(Map<String, String> imuttData, int[] colors) {
        Map<String, String> data = new HashMap<>();
        if (outcomes) {
            for (String key : imuttData.keySet()){
                if (Float.parseFloat(imuttData.get(key)) < 0){
                    data.put(key, imuttData.get(key));
                }
            }
        }else{
            for (String key : imuttData.keySet()){
                if (Float.parseFloat(imuttData.get(key)) > 0){
                    data.put(key, imuttData.get(key));
                }
            }
        }

        sum = sumData(data);


        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity());
        categoriesAdapter.setColors(colors);
        categoriesAdapter.setCategories(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        categoriesList.setLayoutManager(linearLayoutManager);
        categoriesList.setNestedScrollingEnabled(true);
        categoriesList.setAdapter(categoriesAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

    }



    public void initData() {
        CategoriesDAO cat = new CategoriesDAO();
        SpendingsDAO spendingsDAO = new SpendingsDAO();
        Map<String, String> data = new HashMap<>();
        DataFiller df = new DataFiller();


        ArrayList<String> categoriesNames = cat.getCatNamesOnly();
        for(String categoryName : categoriesNames){
            data.put(categoryName, spendingsDAO.sumByCat(categoryName, date) + "");
        }
        HashMap<String, String> bufData = new HashMap<>(data);

        if (outcomes) data = invertValues(data);
        PieChart mChart = (PieChart) rootView.findViewById(R.id.chart);
        mChart.setDescription("");


        mChart.setCenterTextSize(10f);
        mChart.getLegend().setEnabled(false);
        mChart.setDrawSliceText(false);

        mChart.setHoleRadius(45);
        mChart.setTransparentCircleRadius(0);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        PieData pieData = df.generatePieData(data, outcomes);
        initCategoriesList(bufData, pieData.getColors());

        if (pieData.getYValueSum() == 0) mChart.setCenterText("Empty");
        mChart.setData(pieData);
        mChart.invalidate();



    }

    private float sumData(Map<String, String> data) {
        float sum = 0;
        for (String key : data.keySet()){
            sum += Math.abs(Float.parseFloat(data.get(key)));
        }
        return sum;
    }

    private Map<String, String> invertValues(Map<String, String> data) {
        for (String key : data.keySet()){
            float value = Float.parseFloat(data.get(key));
                data.put(key, "" + value * -1);

        }
        return data;
    }

    public boolean isOutcomes() {
        return outcomes;
    }

    public void setOutcomes(boolean outcomes) {
        this.outcomes = outcomes;
        if (rootView != null) initData();    }

    public void setDate(Date date) {
        this.date = date;
        if (rootView != null) initData();

    }
}
