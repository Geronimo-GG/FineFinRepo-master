package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dariabeliaeva.diploma.com.finefin.adapter.CustomSpinnerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiagramContainer extends Fragment {

    public final int DAY = 0, WEEK = 1, MONTH = 2, ALL_TIME = 3;

    private View rootView;
    private ViewPager viewPager;
    private TabLayout tabHost;
    private SpinnerAdapter spinnerAdapter;
    private ArrayList<String> items;
    private Diagram fragmentIn, fragmentOut;

    public DiagramContainer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_diagram_container, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        tabHost = ((NewMain) getActivity()).getTabHost();
        tabHost.setTabMode(TabLayout.MODE_SCROLLABLE);
        initSpinner();
        fragmentIn = new Diagram();
        fragmentOut = new Diagram();
        fragmentOut.setOutcomes(true);

        setupViewPager(viewPager);
        tabHost.setupWithViewPager(viewPager);

        return rootView;
    }

    private void initSpinner() {
        Spinner spinner = ((NewMain) getActivity()).getSpinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                fragmentIn.setDate(getDateFor(i));
                fragmentOut.setDate(getDateFor(i));
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                }catch (Exception e){}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                fragmentIn.setDate(getDateFor(DAY));
                fragmentOut.setDate(getDateFor(DAY));
            }
        });

        items = new ArrayList<>();
        items.add("Day");
        items.add("Week");
        items.add("Month");
        items.add("All time");

        spinnerAdapter = new CustomSpinnerAdapter(getActivity(), items);
        spinner.setAdapter(spinnerAdapter);

    }

    private Date getDateFor(int week) {
        Calendar calendar = Calendar.getInstance();
        switch (week){
            case DAY:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;
            case WEEK:
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, -1);
                break;
            default: return null;
        }
        return calendar.getTime();

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(fragmentOut, "Outcomes");
        adapter.addFragment(fragmentIn, "Incomes");
        adapter.addFragment(new Diagram(), "Incomes");
        adapter.addFragment(new Diagram(), "Incomes");
        adapter.addFragment(new Diagram(), "Incomes");
        viewPager.setAdapter(adapter);
    }


    public void setTabHost(TabLayout tabHost) {
        this.tabHost = tabHost;
    }
}
