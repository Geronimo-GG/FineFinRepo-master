package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import io.realm.Realm;
import io.realm.RealmResults;

public class
InFragment extends Fragment {

    CatListAdapter catLA = new CatListAdapter();
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_in, container, false);
        provideListInitialization();
        return rootView;

    }

    Realm realm = Realm.getDefaultInstance();

    private void provideListInitialization() {
        RealmResults<Categories> catItems = realm
                .where(Categories.class)
                .equalTo("type", "income")
                .findAll();
        ArrayList<Categories> catsItemsArrayList = new ArrayList<>();
        catsItemsArrayList.addAll(catItems);

        //catLA = new FinListAdapter();
        catLA.setCats(catsItemsArrayList);
        RecyclerView realmRecyclerView = (RecyclerView) rootView.findViewById(R.id.cat_list);
        realmRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        realmRecyclerView.setAdapter(catLA);
        //realmRecyclerView.setNestedScrollingEnabled(false);


    }

    public Categories getSelected() {
        return catLA.getSelected();
    }

}
