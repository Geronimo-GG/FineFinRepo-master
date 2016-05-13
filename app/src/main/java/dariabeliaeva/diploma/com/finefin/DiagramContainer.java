package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
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

import java.util.ArrayList;

import dariabeliaeva.diploma.com.finefin.adapter.CustomSpinnerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiagramContainer extends Fragment {

    private View rootView;
    private ViewPager viewPager;
    private TabLayout tabHost;
    private SpinnerAdapter spinnerAdapter;

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


        ArrayList<String> items = new ArrayList<>();
        items.add("Day");
        items.add("Week");
        items.add("Month");
        items.add("All time");

        spinnerAdapter = new CustomSpinnerAdapter(getActivity(), items);
        spinner.setAdapter(spinnerAdapter);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(fragmentOut, "Outcomes");
        adapter.addFragment(fragmentIn, "Incomes");
        viewPager.setAdapter(adapter);
    }


    public void setTabHost(TabLayout tabHost) {
        this.tabHost = tabHost;
    }
}
