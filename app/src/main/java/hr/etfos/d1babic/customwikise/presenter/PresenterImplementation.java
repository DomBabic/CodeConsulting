package hr.etfos.d1babic.customwikise.presenter;

import java.util.List;

import hr.etfos.d1babic.customwikise.model.Base;
import hr.etfos.d1babic.customwikise.model.Item;
import hr.etfos.d1babic.customwikise.view.MainView;
import io.realm.Realm;
import retrofit2.Response;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class PresenterImplementation implements Presenter {

    private MainView view;
    private Base data;
    private Response<Base> response;

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void setData(Base data) {
        this.data = data;
    }

    @Override
    public void setResponse(Response<Base> response) {
        this.response = response;
    }

    @Override
    public void setRealmObjectData() {
        data = response.body();
    }

    @Override
    public void getRealmData() {
        Realm realm = Realm.getDefaultInstance();
        List<Item> items = realm.where(Item.class).findAll();
        view.setAdapterItems(items);
    }
}
