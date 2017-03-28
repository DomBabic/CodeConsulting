package hr.etfos.d1babic.codeconsulting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class Base extends RealmObject{

    @SerializedName("items")
    @Expose
    private RealmList<Item> items = null;

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

}
