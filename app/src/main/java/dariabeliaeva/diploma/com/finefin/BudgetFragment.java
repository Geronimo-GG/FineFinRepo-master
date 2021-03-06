package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.adapter.BudgetAdapter;
import dariabeliaeva.diploma.com.finefin.adapter.CategoriesAdapter;
import dariabeliaeva.diploma.com.finefin.adapter.CustomSpinnerAdapter;
import dariabeliaeva.diploma.com.finefin.data_models.Budget;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import dariabeliaeva.diploma.com.finefin.data_models.User;
import dariabeliaeva.diploma.com.finefin.utils.Convertations;
import io.realm.Realm;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {


    BudgetAdapter categoriesAdapter;
    CustomSpinnerAdapter spinnerAdapter;
    RecyclerView categoriesList;
    View rootView;
    TextView tvLeftIncome;
    EditText etMonthlyIncome;
    FloatingActionButton fabAdd;
    float monthlyIncome;
//    Spinner catsSpinner;
    Realm realm;
    SharedPreferences sharedPreferences;
    Gson gson = new Gson();


    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_budget, container, false);
        realm = Realm.getDefaultInstance();
        findViewsById();
        if (etMonthlyIncome.length() == 0)fabAdd.hide();
        setUiListeners();

        categoriesAdapter = new BudgetAdapter(getActivity());
        categoriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoriesList.setAdapter(categoriesAdapter);
        initSwipeToRemove();
        sharedPreferences = getActivity().getSharedPreferences("budget", Context.MODE_PRIVATE);

        ArrayList<String> categoriesNames = new ArrayList<>();
        ArrayList<Categories> categories = new ArrayList<>();
        categories.addAll(realm
                .where(Categories.class)
                .equalTo("type", "outcome")
                .findAll());
        for (Categories category : categories){
            categoriesNames.add(category.getCat_name());
        }

        spinnerAdapter = new CustomSpinnerAdapter(getActivity(), categoriesNames);
        etMonthlyIncome.setText(sharedPreferences.getString("monthly_income", ""));
        initWithData();
        return rootView;
    }

    private void initWithData() {
        Map<String, String> map = gson.fromJson(sharedPreferences.getString("budget_data", ""), HashMap.class);
        if (map == null) map = new HashMap<>();
        categoriesAdapter.setCategories(map);
        updateIncome();
    }

    private void initSwipeToRemove() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                categoriesAdapter.removeByKey(((BudgetAdapter.CategoriesViewHolder) viewHolder).tvCatName.getText());
                sharedPreferences.edit().putString("budget_data", gson.toJson(categoriesAdapter.getCategories())).apply();
                initWithData();


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(categoriesList);
    }

    private void setUiListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdditionDialog();

            }
        });

        etMonthlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if (charSequence.length() > 0) fabAdd.show();
                    else fabAdd.hide();
                    sharedPreferences.edit().putString("monthly_income", etMonthlyIncome.getText().toString()).apply();
                    setMonthlyIncome(Float.parseFloat(charSequence.toString()));
                    updateIncome();

                }
                catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showAdditionDialog() {
        final Spinner catsSpinner = new Spinner(getActivity());
        final EditText etCatPrice = new EditText(getActivity());
        etCatPrice.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etCatPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
        catsSpinner.setAdapter(spinnerAdapter);

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.addView(etCatPrice);
        linearLayout.addView(catsSpinner);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int pad = Convertations.dpToPx(16, getActivity());
        linearLayout.setPadding(pad, pad, pad, pad);


        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setView(linearLayout);
        b.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (etMonthlyIncome.length() > 0 && etCatPrice.length() > 0){
                    addNewCategory(Float.parseFloat(etCatPrice.getText().toString()), catsSpinner.getSelectedItem().toString());
                    updateIncome();
                }
            }
        });
        b.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        b.show();



    }

    private void findViewsById() {
        categoriesList = (RecyclerView) rootView.findViewById(R.id.fin_list);
        etMonthlyIncome = (EditText) rootView.findViewById(R.id.monthly_incomes);
//        etCatPrice = (EditText) rootView.findViewById(R.id.cat_sum);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        catsSpinner = (Spinner) rootView.findViewById(R.id.planets_spinner);
        tvLeftIncome = (TextView) rootView.findViewById(R.id.tvIncomeLeft);
    }

    private void updateIncome(){
        tvLeftIncome.setText((monthlyIncome - getLeftIncome()) + "");
    }

    private float getLeftIncome(){
        float leftIncome = 0;
        for (String value : categoriesAdapter.getCategories().values()){
            leftIncome+=Float.parseFloat(value);
        }
        return leftIncome;
    }

    private void setMonthlyIncome(float income){
        monthlyIncome = income;
    }

    private void addNewCategory(float price, String catName){
        categoriesAdapter.add(catName, price + "");
        sharedPreferences.edit().putString("budget_data", gson.toJson(categoriesAdapter.getCategories())).apply();
        sharedPreferences.edit().putString("monthly_income", etMonthlyIncome.getText().toString()).apply();


    }

}
