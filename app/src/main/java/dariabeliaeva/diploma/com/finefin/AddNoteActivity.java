package dariabeliaeva.diploma.com.finefin;

import android.app.DatePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import dariabeliaeva.diploma.com.finefin.dao.SpendingsDAO;
import dariabeliaeva.diploma.com.finefin.data_models.Categories;

public class AddNoteActivity extends AppCompatActivity {

    private int spenSum;
    private Date spenDate;
    private String spenDesc;
    private String catName;
    private OutFragment outFrag = new OutFragment();
    private InFragment inFrag = new InFragment();
    private TabLayout tab;
    private SpendingsDAO spenDAO = new SpendingsDAO();
    private Date date = new Date();
    private Toolbar toolbar;
    private EditText expText;
    private EditText descText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        tab = (TabLayout) findViewById(R.id.tabHost);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        final TextView editTxt = (TextView) findViewById(R.id.tvData);
        expText = (EditText) findViewById(R.id.tvExp);
        descText = (EditText) findViewById(R.id.description);





        //Date date = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpDlg = new DatePickerDialog(AddNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTxt.setText(String.valueOf(dayOfMonth)+"."+String.valueOf(monthOfYear+1)+"."+year);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                date = new Date(calendar.getTimeInMillis());
            }
        }, mYear, mMonth, mDay
        );

        editTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dpDlg.show();

            }
        });
        setupViewPager(viewPager);
        tab.setupWithViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_spending_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send){
            if (expText.getText().toString().length() == 0){
                Snackbar.make(expText, "Please fill price", Snackbar.LENGTH_SHORT).show();
                return true;
            }
            spenSum = Integer.parseInt(expText.getText().toString());
            spenDate = date;
            spenDesc = descText.getText().toString();
            Categories selected;
            if (tab.getSelectedTabPosition() == 0){
                selected = outFrag.getSelected();
            }else{
                selected = inFrag.getSelected();
            }
            if (selected == null) selected = outFrag.getSelected();
            if (selected == null) selected = inFrag.getSelected();

            catName = selected.getCat_name();
            if (selected.getType().equals("outcome")) {
                spenSum = spenSum * (-1);
            }

            spenDAO.addSpendings(spenDesc, spenSum, spenDate, catName, selected);
            finish();
            return true;
        }
        return false;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(outFrag, "Outcomes");
        adapter.addFragment(inFrag, "Incomes");
        viewPager.setAdapter(adapter);
    }
}
