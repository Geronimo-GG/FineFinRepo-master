package dariabeliaeva.diploma.com.finefin;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import dariabeliaeva.diploma.com.finefin.dao.GoalsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.FinancialGoals;

public class AddFinancialGoalActivity extends AppCompatActivity {

    int goalPrice;
    String goalName;
    int goalTime;
    FinancialGoals finGoals = new FinancialGoals();
    GoalsDAO goalsDAO = new GoalsDAO();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_financial_goal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        final EditText goal_price = (EditText) findViewById(R.id.goal_price);
        final EditText goal_name = (EditText) findViewById(R.id.goal_name);
        final EditText goal_time = (EditText) findViewById(R.id.goal_time);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (goal_name.getText().toString().length() == 0){
                    Snackbar.make(fab, "Please fill name", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (goal_price.getText().toString().length() == 0){
                    Snackbar.make(fab, "Please fill price", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                goalPrice = Integer.parseInt(goal_price.getText().toString());
                goalName = goal_name.getText().toString();
                goalTime = Integer.parseInt(goal_time.getText().toString());


                goalsDAO.addGoal(goalName, goalPrice, goalTime);
                finish();

            }
        });
    }
}
