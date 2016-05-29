package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import java.util.Random;

import dariabeliaeva.diploma.com.finefin.dao.AdvicesDAO;
import dariabeliaeva.diploma.com.finefin.dao.SpendingsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Advices;
import io.realm.Realm;
import io.realm.RealmResults;

public class NewMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TabLayout tabs;
    Spinner spinner;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Spends history");

        tabs = (TabLayout) findViewById(R.id.tabs);
        spinner = (Spinner) findViewById(R.id.spinner);

        showRandomAdvice(Realm.getDefaultInstance());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().add(R.id.content_main, new MainList()).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Intent intent;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id != R.id.nav_stat){
            tabs.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        }else{
            tabs.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
        }

        if (id == R.id.nav_home) {
            toolbar.setTitle("Spends history");
            newFragment = new MainList();
            transaction.replace(R.id.content_main, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_new) {

            intent = new Intent(this, AddNoteActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);

        } else if (id == R.id.nav_stat) {
            toolbar.setTitle("Statistic");

            //newFragment = ;
            DiagramContainer diagramContainer = new DiagramContainer();
            transaction.replace(R.id.content_main, diagramContainer);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_plan) {
            toolbar.setTitle("Plan budget");

            //newFragment = ;
            BudgetFragment budgetFragment = new BudgetFragment();
            transaction.replace(R.id.content_main, budgetFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public TabLayout getTabHost() {
        return tabs;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    private void addAllAdvices(Realm realm) {

        AdvicesDAO advicesDAO = new AdvicesDAO();
        realm.beginTransaction();
        realm.clear(Advices.class);
        realm.commitTransaction();
        advicesDAO.addAdvice("Cut your losses short. The ‘it'll come back’ mentality is dangerous. One great way to do this is through the use of stop loss orders. Don't fear them. Use them. They are your best friend.\n");
        advicesDAO.addAdvice("I will tell you how to become rich. Close the doors. Be fearful when others are greedy. Be greedy when others are fearful.\n");
        advicesDAO.addAdvice("No one ever achieved financial security by being weak and scared.Confidence is contagious; it will bring more into your life.\n");
        advicesDAO.addAdvice("Frugality isn’t about cutting your spending on everything. That approach wouldn't last two days. Frugality, quite simply, is about choosing the things you love enough to spend extravagantly on—and then cutting costs mercilessly on the things you don't love.\n");
        advicesDAO.addAdvice("Comparison shop when it comes to choosing a primary financial institution. It's a very basic concept, but one that many people fail to grasp. The big banks are often the default choice, yet smaller and institutions like community banks and credit unions are considerably overlooked.\n");
        advicesDAO.addAdvice("You don't have to start big...small steps over a lifetime really add up. Start funding your emergency fund with $10 a month, start investing with $50 a month. It is more important to get going than to wait for the big amounts of cash!\n");
        advicesDAO.addAdvice("Live under your means. Know exactly what you earn each month and spend less. That's a step beyond living within your means. Take responsibility and choose where your money goes, instead of being influenced by whims, advertising, habits or peer pressure.\n");
        advicesDAO.addAdvice("Know you want it, then wait for wholesale!\n");
        advicesDAO.addAdvice("The most impactful approach to money management is spending less than you owe… By establishing a structured spending plan, which accounts for all expenses, you can focus on eliminating unnecessary expenses and commit your disposable income to building wealth.\n");
        advicesDAO.addAdvice("Invest in yourself. Your career is the engine of your wealth.\n");
        advicesDAO.addAdvice("Run your household like a business and manage your finances like a bank! The lack of money is not our problem it's the mismanagement of life holding us back from maximizing our earning potential.\n");
        advicesDAO.addAdvice("Budget About 30% of Your Income for Lifestyle Spending. This includes movies, restaurants, and happy hours—basically, anything that doesn’t cover basic necessities. By abiding by the 30% rule, you can save and splurge at the same time.\n");
        advicesDAO.addAdvice("Draft a Financial Vision Board. You need motivation to start adopting better money habits, and if you craft a vision board, it can help remind you to stay on track with your financial goals.\n");
        advicesDAO.addAdvice("Set Specific Financial Goals. Use numbers and dates, not just words, to describe what you want to accomplish with your money. How much debt do you want to pay off—and when? How much do you want saved, and by what date?\n");
        advicesDAO.addAdvice("Learn How to Savor. Savoring means appreciating what you have now, instead of trying to get happy by acquiring more things.\n");
        advicesDAO.addAdvice("Shop Solo. Ever have a friend declare, “That’s so cute on you! You have to get it!” for everything you try on? Save your socializing for a walk in the park, instead of a stroll through the mall, and treat shopping with serious attention.\n");
        advicesDAO.addAdvice("Spend on the Real You—Not the Imaginary You. It’s easy to fall into the trap of buying for the person you want to be: chef, professional stylist, triathlete.\n");
        advicesDAO.addAdvice("Start Saving ASAP. Not next week. Not when you get a raise. Not next year. Today. Because money you put in your retirement fund now will have more time to grow through the power of compound growth.\n");
        advicesDAO.addAdvice("Open a Savings Account at a Different Bank Than Where You Have Your Checking Account. If you keep both your accounts at the same bank, it’s easy to transfer money from your savings to your checking. Way too easy. So avoid the problem—and these other money pitfalls.\n");
        advicesDAO.addAdvice("The three basics of a solid financial foundation. Credit card debt paid off.Emergency fund stocked up. Retirement account(s) in existence and growing.Everything else (travel, homeownership, investments) should come after.\n");

    }

    private void showRandomAdvice(Realm realm) {
        RealmResults<Advices> res = realm.where(Advices.class).findAll();

        if (res.size() == 0) return;
        Advices advices = res.get(new Random().nextInt(res.size()));
        new AlertDialog.Builder(this)
                .setMessage(advices.getAdvice())
                .setPositiveButton("Cool, thanks for advice", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

}
