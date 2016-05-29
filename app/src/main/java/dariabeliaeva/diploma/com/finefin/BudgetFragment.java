package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dariabeliaeva.diploma.com.finefin.adapter.CategoriesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {


    CategoriesAdapter categoriesAdapter;
    RecyclerView categoriesList;
    View rootView;
    EditText etMonthlyIncome, etCatPrice;
    FloatingActionButton fabAdd;
    int monthlyIncome;



    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_budget, container, false);
        categoriesAdapter = new CategoriesAdapter(getActivity());

        findViewsById();
        setUiListeners();

        categoriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoriesList.setAdapter(categoriesAdapter);
        return rootView;
    }

    private void setUiListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etMonthlyIncome.length() > 0 && etCatPrice.length() > 0){
                    addNewCategory(Float.parseFloat(etCatPrice.getText().toString()));
                }
            }
        });
    }

    private void findViewsById() {
        categoriesList = (RecyclerView) rootView.findViewById(R.id.fin_list);
        etMonthlyIncome = (EditText) rootView.findViewById(R.id.monthly_incomes);
        etCatPrice = (EditText) rootView.findViewById(R.id.cat_sum);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fab);
    }

    private void setMonthlyIncome(int income){
        monthlyIncome = income;
    }

    private void addNewCategory(float price){
        categoriesAdapter.add("test", price + "");
    }

}
