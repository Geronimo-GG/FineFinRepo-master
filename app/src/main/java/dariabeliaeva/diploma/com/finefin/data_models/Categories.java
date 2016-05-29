package dariabeliaeva.diploma.com.finefin.data_models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dari on 3/20/2016.
 */
public class Categories extends RealmObject {

    enum eType {
        income,
        outcome
    }

    @PrimaryKey
    private long category_id;
    private String cat_name;
    private int icon_id;
    //private String bg_color;
    private String type;
    private RealmList <Spendings> spendings;


    public Categories(long category_id, String cat_name, int icon_id, String type, RealmList<Spendings> spendings) {
        this.category_id = category_id;
        this.cat_name = cat_name;
        this.icon_id = icon_id;
        this.type = type;
        this.spendings = spendings;
    }

    public Categories() {
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    /*public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RealmList<Spendings> getSpendings() {
        return spendings;
    }

    public void setSpendings(RealmList<Spendings> spendings) {
        this.spendings = spendings;
    }
}
