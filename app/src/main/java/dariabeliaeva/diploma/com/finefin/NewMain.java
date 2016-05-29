package dariabeliaeva.diploma.com.finefin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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

import dariabeliaeva.diploma.com.finefin.dao.SpendingsDAO;

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

        } else if (id == R.id.nav_send) {

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
}
