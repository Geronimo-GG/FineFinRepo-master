package dariabeliaeva.diploma.com.finefin.data_models;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dari on 5/16/2016.
 */
public class Budget {

    private long monthly_income;
    private long difference_balance;
    private RealmList<Spendings> plan_to_spend;

    public Budget(long monthly_income, long difference_balance, RealmList<Spendings> plan_to_spend) {
        this.monthly_income = monthly_income;
        this.difference_balance = difference_balance;
        this.plan_to_spend = plan_to_spend;
    }

    public Budget() {
    }

    public long getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(long monthly_income) {
        this.monthly_income = monthly_income;
    }

    public long getDifference_balance() {
        return difference_balance;
    }

    public void setDifference_balance(long difference_balance) {
        this.difference_balance = difference_balance;
    }

    public RealmList<Spendings> getPlan_to_spend() {
        return plan_to_spend;
    }

    public void setPlan_to_spend(RealmList<Spendings> plan_to_spend) {
        this.plan_to_spend = plan_to_spend;
    }
}
