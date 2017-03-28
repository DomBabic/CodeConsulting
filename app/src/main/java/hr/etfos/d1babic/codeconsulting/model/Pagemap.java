package hr.etfos.d1babic.codeconsulting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class Pagemap extends RealmObject{

    @SerializedName("cse_thumbnail")
    @Expose
    private RealmList<CseThumbnail> cseThumbnail = null;

    public RealmList<CseThumbnail> getCseThumbnail() {
        return cseThumbnail;
    }

    public void setCseThumbnail(RealmList<CseThumbnail> cseThumbnail) {
        this.cseThumbnail = cseThumbnail;
    }

}
