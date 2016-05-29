package dariabeliaeva.diploma.com.finefin.data_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{



    @PrimaryKey
    private String user_name;
    private String user_password;
    private boolean want_advice;
    private boolean want_lock;
    private String user_wallet;


    public User(boolean want_advice, boolean want_lock, String user_wallet, String user_name, String user_password) {
        this.want_advice = want_advice;
        this.want_lock = want_lock;
        this.user_wallet = user_wallet;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public User() {
    }

    public boolean isWant_advice() {
        return want_advice;
    }

    public void setWant_advice(boolean want_advice) {
        this.want_advice = want_advice;
    }

    public boolean isWant_lock() {
        return want_lock;
    }

    public void setWant_lock(boolean want_lock) {
        this.want_lock = want_lock;
    }

    public String getUser_wallet() {
        return user_wallet;
    }

    public void setUser_wallet(String user_wallet) {
        this.user_wallet = user_wallet;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
