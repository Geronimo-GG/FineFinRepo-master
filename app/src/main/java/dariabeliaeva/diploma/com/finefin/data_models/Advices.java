package dariabeliaeva.diploma.com.finefin.data_models;

import io.realm.annotations.PrimaryKey;

/**
 * Created by Dari on 5/13/2016.
 */
public class Advices {

    @PrimaryKey
    private long id;
    private String advice;
    private int counter;

    public Advices(long id, String advice, int counter) {
        this.id = id;
        this.advice = advice;
        this.counter = counter;
    }

    public Advices() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
