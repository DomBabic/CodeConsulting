package hr.etfos.d1babic.codeconsulting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class CseThumbnail extends RealmObject{

    @SerializedName("src")
    @Expose
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}