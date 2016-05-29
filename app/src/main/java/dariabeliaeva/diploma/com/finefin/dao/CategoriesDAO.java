package dariabeliaeva.diploma.com.finefin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Dari on 3/29/2016.
 */
public class CategoriesDAO {

    private Realm pRealm = Realm.getDefaultInstance();


    public void addCategory(String name, String type, int icon_id) {
        Categories cat = new Categories();
        cat.setCategory_id(System.currentTimeMillis());
        cat.setCat_name(name);
        cat.setType(type);
        cat.setIcon_id(icon_id);

        pRealm.beginTransaction();
        pRealm.copyToRealmOrUpdate(cat);
        pRealm.commitTransaction();
    }

    public ArrayList<String> getCatNamesOnly (){

        RealmResults<Categories> categoriesList =
                pRealm.where(Categories.class).
                        findAll();
        ArrayList<Categories> categoriesListAll = new ArrayList<>();
        categoriesListAll.addAll(categoriesList);

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < categoriesListAll.size(); i++) {
            Categories cat = categoriesListAll.get(i);
            names.add(cat.getCat_name());
        }

        return names;
    }
//make for outcomes & incomes separetely
    public Map getSumByCategory ()
    {
        RealmResults<Categories> categoriesList =
                pRealm.where(Categories.class).
                        findAll();
        List<Categories> categoriesListAll = new ArrayList<>();
        categoriesListAll.addAll(categoriesList);

       /* RealmResults<Spendings> spenList =
                pRealm.where(Spendings.class).
                        findAll(); */
        RealmList<Spendings> spenAll = new RealmList<>();
        List<Spendings> spendingsList = new ArrayList<>();
        //spenAll.addAll(spenList);

        List<Float> sum = new ArrayList<>();

        Map sumByCat = new HashMap();

        for (int i = 0; i < categoriesListAll.size(); i++) {
            Categories cat = categoriesListAll.get(i);

            float tmp_sum = 0;
            spenAll = cat.getSpendings();
            spendingsList.addAll(spenAll);
            for (int spen = 0; spen < spenAll.size(); spen++) {
                Spendings spendings = spendingsList.get(i);
                tmp_sum += spendings.getPrice();
            }

            sum.add(tmp_sum);
            sumByCat.put(cat.getCat_name(), tmp_sum * (-1));

        }

        return sumByCat;
    }

    public int getIcon (String name){
        Categories cat = new Categories();
        RealmResults<Categories> categories =
                pRealm.where(Categories.class).findAll();
        List<Categories> categoriesAll = new ArrayList<>();
        categoriesAll.addAll(categories);
        for (Categories category : categoriesAll){
            if (category.getCat_name().equals(name)) return category.getIcon_id();
        }
        return categories.get(0).getIcon_id();
    }
}
