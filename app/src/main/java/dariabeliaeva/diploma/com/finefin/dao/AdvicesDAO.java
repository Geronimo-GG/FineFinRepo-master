package dariabeliaeva.diploma.com.finefin.dao;

import dariabeliaeva.diploma.com.finefin.data_models.Advices;
import io.realm.Realm;

/**
 * Created by user on 5/29/16.
 */
public class AdvicesDAO {

    private Realm pRealm = Realm.getDefaultInstance();

    public void addAdvice (String text)
    {
        Advices advices = new Advices();
        advices.setId(System.currentTimeMillis());
        advices.setAdvice(text);
        advices.setCounter(0);

        pRealm.beginTransaction();
        pRealm.copyToRealmOrUpdate(advices);
        pRealm.commitTransaction();
    }
}
