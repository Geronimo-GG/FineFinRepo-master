package dariabeliaeva.diploma.com.finefin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;

public class SplashActivity extends AppCompatActivity {

    private View nameScreen, advicesScreen, passwordScreen;
    private FrameLayout rootContainer;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rootContainer = (FrameLayout) findViewById(R.id.root_container);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setupNameScreen();
        setupAdvicesScreen();
        setupPasswordScreen();


    }

    private void setupPasswordScreen() {
        passwordScreen = getLayoutInflater().inflate(R.layout.xml_hello_password, null);
        passwordScreen.setVisibility(View.GONE);

        passwordScreen.findViewById(R.id.btnPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ((EditText) passwordScreen.findViewById(R.id.etPassword)).getText().toString();
                if (password.length() > 0){
                    ((EditText) passwordScreen.findViewById(R.id.etPassword)).setError(null);
                    tutorialFinished();
                }else{
                    ((EditText) passwordScreen.findViewById(R.id.etPassword)).setError("Can't be empty");
                }
//                TODO save
                advicesScreen.setVisibility(View.GONE);
                passwordScreen.setVisibility(View.VISIBLE);
            }
        });

        passwordScreen.findViewById(R.id.button_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialFinished();
            }
        });



        rootContainer.addView(passwordScreen);


    }

    private void tutorialFinished() {
        startActivity(new Intent(this, NewMain.class));
        finish();
    }

    private void setupAdvicesScreen() {
        advicesScreen = getLayoutInflater().inflate(R.layout.xml_hello_advice, null);
        advicesScreen.setVisibility(View.GONE);

        advicesScreen.findViewById(R.id.btnAdvices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) advicesScreen.findViewById(R.id.cbGetAdvices)).isChecked();
//                TODO save
                advicesScreen.setVisibility(View.GONE);
                passwordScreen.setVisibility(View.VISIBLE);
            }
        });

        rootContainer.addView(advicesScreen);

    }

    private void setupNameScreen() {
        nameScreen = getLayoutInflater().inflate(R.layout.activity_hello_name, null);
        nameScreen.findViewById(R.id.btnNameFilled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((EditText) nameScreen.findViewById(R.id.etName)).length() > 0){
                    ((EditText) nameScreen.findViewById(R.id.etName)).setError(null);
                    nameScreen.setVisibility(View.GONE);
                    advicesScreen.setVisibility(View.VISIBLE);
                }else{
                    ((EditText) nameScreen.findViewById(R.id.etName)).setError("Can't be empty");
                }
            }
        });
        rootContainer.addView(nameScreen);

    }
}
