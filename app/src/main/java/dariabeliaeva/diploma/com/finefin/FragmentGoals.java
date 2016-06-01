package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.adapter.CategoriesAdapter;
import dariabeliaeva.diploma.com.finefin.dao.GoalsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.FinancialGoals;
import dariabeliaeva.diploma.com.finefin.utils.Convertations;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class FragmentGoals extends Fragment {


    CategoriesAdapter categoriesAdapter;
    View rootView;
    RecyclerView recyclerView;
    Realm realm;
    FloatingActionButton fab;
    GoalsDAO goalsDAO;

    public FragmentGoals() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment_goals, container, false);
        realm = Realm.getDefaultInstance();
        goalsDAO = new GoalsDAO();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fin_list);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        categoriesAdapter = new CategoriesAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoriesAdapter);
        setUiListeners();

        /*ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //realm.beginTransaction();
                //categoriesAdapter.ge().get(viewHolder.getAdapterPosition()).removeFromRealm();
                //realm.commitTransaction();
                //finListAdapter.removeSpending(viewHolder.getAdapterPosition());

            }
        };*/

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initAdapterWithData();

    }

    private void setUiListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showAdditionDialog();
                startActivity(new Intent(getActivity(), AddFinancialGoalActivity.class));
            }
        });
    }

    private void showAdditionDialog() {
        final EditText etGoalName = new EditText(getActivity());
        final EditText etGoalPrice = new EditText(getActivity());
        final EditText etGoalTime = new EditText(getActivity());
        etGoalName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etGoalPrice.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etGoalTime.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etGoalTime.setInputType(InputType.TYPE_CLASS_NUMBER);
        etGoalPrice.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.addView(etGoalName);
        linearLayout.addView(etGoalPrice);
        linearLayout.addView(etGoalTime);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int pad = Convertations.dpToPx(16, getActivity());
        linearLayout.setPadding(pad, pad, pad, pad);


        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setView(linearLayout);
        b.setPositiveButton("Add goal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (etGoalName.length() > 0 && etGoalPrice.length() > 0 && etGoalTime.length() > 0){
                    goalsDAO.addGoal(etGoalName.getText().toString(), Integer.parseInt(etGoalPrice.getText().toString()), Integer.parseInt(etGoalTime.getText().toString()));
                    initAdapterWithData();
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

    private void initAdapterWithData() {
        RealmResults<FinancialGoals> results = realm.where(FinancialGoals.class).findAll();
        ArrayList<FinancialGoals> goals = new ArrayList<>(results);
        Map<String, String> map = new HashMap<>();
        for (FinancialGoals financialGoal : goals){
            map.put(financialGoal.getGoal_name(), financialGoal.getGoal_sum() + "");
        }
        categoriesAdapter.setCategories(map);
    }


}
