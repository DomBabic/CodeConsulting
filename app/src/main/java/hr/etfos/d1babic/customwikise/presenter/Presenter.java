package hr.etfos.d1babic.customwikise.presenter;

import hr.etfos.d1babic.customwikise.model.Base;
import hr.etfos.d1babic.customwikise.view.MainView;
import retrofit2.Response;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public interface Presenter {

    void setView(MainView view);
    void setData(Base data);
    void setResponse(Response<Base> response);
    void setRealmObjectData();
    void getRealmData();

}
