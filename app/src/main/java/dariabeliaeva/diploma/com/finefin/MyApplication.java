package dariabeliaeva.diploma.com.finefin;

import android.app.Application;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dari on 3/29/2016.
 */
public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
        Realm.getDefaultInstance();


    }

}

