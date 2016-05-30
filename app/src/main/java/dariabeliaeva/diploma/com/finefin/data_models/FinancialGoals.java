package dariabeliaeva.diploma.com.finefin.data_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dari on 5/16/2016.
 */
public class FinancialGoals extends RealmObject {

    @PrimaryKey
    private long goal_id;
    private String goal_name;
    private long goal_sum;
    private int goal_time;
    private long goal_monthly_need;

    public FinancialGoals() {
    }

    public FinancialGoals(long goal_id, String goal_name, long goal_sum) {
        this.goal_id = goal_id;
        this.goal_name = goal_name;
        this.goal_sum = goal_sum;
    }

    public FinancialGoals(long goal_id, String goal_name, long goal_sum, int goal_time, long goal_monthly_need) {
        this.goal_id = goal_id;
        this.goal_name = goal_name;
        this.goal_sum = goal_sum;
        this.goal_time = goal_time;
        this.goal_monthly_need = goal_monthly_need;
    }

    public long getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(long goal_id) {
        this.goal_id = goal_id;
    }

    public String getGoal_name() {
        return goal_name;
    }

    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }

    public long getGoal_sum() {
        return goal_sum;
    }

    public void setGoal_sum(long goal_sum) {
        this.goal_sum = goal_sum;
    }

    public int getGoal_time() {
        return goal_time;
    }

    public void setGoal_time(int goal_time) {
        this.goal_time = goal_time;
    }

    public long getGoal_monthly_need() {
        return goal_monthly_need;
    }

    public void setGoal_monthly_need(long goal_monthly_need) {
        this.goal_monthly_need = goal_monthly_need;
    }
}
