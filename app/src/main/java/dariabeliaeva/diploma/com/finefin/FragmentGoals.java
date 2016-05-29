package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dariabeliaeva.diploma.com.finefin.adapter.CategoriesAdapter;


public class FragmentGoals extends Fragment {


    CategoriesAdapter categoriesAdapter;
    View rootView;
    RecyclerView recyclerView;

    public FragmentGoals() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment_goals, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fin_list);
        categoriesAdapter = new CategoriesAdapter(getActivity());
        //categoriesAdapter.
        return rootView;
    }



}
