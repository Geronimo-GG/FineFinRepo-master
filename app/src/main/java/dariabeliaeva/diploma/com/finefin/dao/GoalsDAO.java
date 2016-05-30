package dariabeliaeva.diploma.com.finefin.dao;

import dariabeliaeva.diploma.com.finefin.R;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.FinancialGoals;
import io.realm.Realm;

/**
 * Created by user on 5/30/16.
 */
public class GoalsDAO {

    private Realm pRealm = Realm.getDefaultInstance();

    public GoalsDAO(){
        pRealm = Realm.getDefaultInstance();

    }

    public void addGoal (String name, int price, int time)
    {
        FinancialGoals goals = new FinancialGoals();
        CategoriesDAO catDAO = new CategoriesDAO();
        goals.setGoal_id(System.currentTimeMillis());
        goals.setGoal_name(name);
        goals.setGoal_sum(price);
        goals.setGoal_time(time);
        goals.setGoal_monthly_need(price/time);

        catDAO.addCategory(name, "outcome", R.drawable.ic_star_border_white_24px);

        pRealm.beginTransaction();
        pRealm.copyToRealmOrUpdate(goals);
        pRealm.commitTransaction();
    }
}
