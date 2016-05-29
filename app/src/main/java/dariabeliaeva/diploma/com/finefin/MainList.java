package dariabeliaeva.diploma.com.finefin;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dariabeliaeva.diploma.com.finefin.dao.AdvicesDAO;
import dariabeliaeva.diploma.com.finefin.dao.CategoriesDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Advices;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;
import dariabeliaeva.diploma.com.finefin.data_models.Spendings;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainList extends Fragment {


    public MainList() {
        // Required empty public constructor
    }

    FinListAdapter finListAdapter;
    private Realm realm;
    Date dPicker = new Date();
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main_list, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(myIntent);
            }

            //Snackbar.make(view, "Add later", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show();
            //}
        });

        realm = Realm.getDefaultInstance();

        List<Spendings> spendings = new ArrayList<Spendings>();

        dPicker.getDate();

//        spendings.add(new Spendings(1, "no transport ever", 10, dPicker, "Transport"));
//        spendings.add(new Spendings(2, "salary is mine now", 12, dPicker, "Salary"));
        //cats.add(new Spendings(3, "soda", 9.5, "09-23-2015", ));
        //cats.add(new Spendings(4, "juice", 20, "09-23-2012", ));
        //cats.add(new Spendings(5, "vodka", 90, "12-23-2012", ));

        realm.beginTransaction();
//        for (Spendings sp : cats) {
        realm.copyToRealmOrUpdate(spendings);
//
        realm.commitTransaction();


        addAllCategories(realm);
        addAllAdvices(realm);

        return rootView;

    }




    @Override
    public void onResume() {
        super.onResume();
        provideListInitialization();
    }

    private void provideListInitialization() {
        RealmResults<Spendings> spenItems = realm
                .where(Spendings.class)
                .findAll();
        ArrayList<Spendings> spenItemsArrayList = new ArrayList<>();
        spenItemsArrayList.addAll(spenItems);
        final long rezult;

        finListAdapter = new FinListAdapter();
        Collections.reverse(spenItemsArrayList);
        finListAdapter.setSpendings(spenItemsArrayList);
        RecyclerView realmRecyclerView = (RecyclerView) rootView.findViewById(R.id.fin_list);
        realmRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        realmRecyclerView.setAdapter(finListAdapter);
        rezult = finListAdapter.UpdateBalance();
        final TextView txtBal = (TextView) rootView.findViewById(R.id.tvBalance);
        txtBal.setText("Balance: " + rezult);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                realm.beginTransaction();
                finListAdapter.getSpendings().get(viewHolder.getAdapterPosition()).removeFromRealm();
                realm.commitTransaction();
                finListAdapter.removeSpending(viewHolder.getAdapterPosition());
                float balance = finListAdapter.UpdateBalance();
                txtBal.setText("Balance: " + balance);

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(realmRecyclerView);
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

    private void addAllCategories(Realm realm)
    {
        CategoriesDAO cat = new CategoriesDAO();
        realm.beginTransaction();
        realm.clear(Categories.class);
        realm.commitTransaction();
        cat.addCategory("Extra", "income", R.drawable.ic_attach_money_white_24px);
        cat.addCategory("Gift", "income", R.drawable.ic_card_giftcard_white_24px);
        cat.addCategory("Salary", "income", R.drawable.ic_monetization_on_white_24px);
        cat.addCategory("Travel", "outcome", R.drawable.ic_local_airport_white_24px);
        cat.addCategory("Food & Drinks", "outcome", R.drawable.ic_local_dining_white_24px);
        cat.addCategory("Bills", "outcome", R.drawable.ic_list_white_24px);
        cat.addCategory("Family", "outcome", R.drawable.ic_people_outline_white_24px);
        cat.addCategory("Fun", "outcome", R.drawable.ic_theaters_white_24px);
        cat.addCategory("Home", "outcome", R.drawable.ic_home_white_24px);
        cat.addCategory("Utilities", "outcome", R.drawable.ic_settings_input_component_white_24px);
        cat.addCategory("Shopping", "outcome", R.drawable.ic_shopping_cart_white_24px);
        cat.addCategory("Healthcare", "outcome", R.drawable.ic_local_hospital_white_24px);
        cat.addCategory("Clothing", "outcome", R.drawable.ic_accessibility_white_24px);
        cat.addCategory("Other", "outcome", R.drawable.ic_local_atm_white_24px);
        cat.addCategory("Transport", "outcome", R.drawable.ic_directions_subway_white_24px);
        cat.addCategory("Education", "outcome", R.drawable.ic_school_white_24px);
        cat.addCategory("Drinks", "outcome", R.drawable.ic_local_bar_white_24px);
        cat.addCategory("Hobbies", "outcome", R.drawable.ic_color_lens_white_24px);
        cat.addCategory("Rent", "outcome", R.drawable.ic_hotel_white_24px);
        cat.addCategory("Gifts", "outcome", R.drawable.ic_card_giftcard_white_24px);
        cat.addCategory("Savings", "outcome", R.drawable.ic_attach_money_white_24px);
        cat.addCategory("Beauty", "outcome", R.drawable.ic_content_cut_white_24px);

    }


}
