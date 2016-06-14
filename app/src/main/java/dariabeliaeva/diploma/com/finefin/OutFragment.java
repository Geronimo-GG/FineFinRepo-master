package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dariabeliaeva.diploma.com.finefin.dao.GoalsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import io.realm.Realm;
import io.realm.RealmResults;

public class OutFragment extends Fragment {

    CatListAdapter catLA = new CatListAdapter();
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_in, container, false);
        provideListInitialization();
        return rootView;

    }

    Realm realm = Realm.getDefaultInstance();

    private void provideListInitialization() {
        RealmResults<Categories> catItems = realm
                .where(Categories.class)
                .equalTo("type", "outcome")
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
