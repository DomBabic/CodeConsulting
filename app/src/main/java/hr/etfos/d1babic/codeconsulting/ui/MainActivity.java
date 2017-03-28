package hr.etfos.d1babic.codeconsulting.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.etfos.d1babic.codeconsulting.R;
import hr.etfos.d1babic.codeconsulting.model.Base;
import hr.etfos.d1babic.codeconsulting.model.GetSearchResults;
import hr.etfos.d1babic.codeconsulting.model.Item;
import hr.etfos.d1babic.codeconsulting.presenter.Presenter;
import hr.etfos.d1babic.codeconsulting.presenter.PresenterImplementation;
import hr.etfos.d1babic.codeconsulting.ui.adapter.ListViewAdapter;
import hr.etfos.d1babic.codeconsulting.utility.Constants;
import hr.etfos.d1babic.codeconsulting.view.MainView;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.input_field)
    EditText inputField;

    @BindView(R.id.list_view)
    ListView listView;

    @OnClick(R.id.search_button)
    public void fetchData() {
        if(!inputField.getText().toString().isEmpty() && checkNetworkStatus()) {
            initRetrofit();
            initCall();

            call.enqueue(new Callback<Base>() {
                @Override
                public void onResponse(Call<Base> call, final Response<Base> response) {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.deleteAll();
                            realm.copyToRealm(response.body());
                            initDataObject();
                            presenter.setData(base);
                            presenter.setResponse(response);
                            presenter.setRealmObjectData();
                        }
                    });

                    presenter.getRealmData();
                }

                @Override
                public void onFailure(Call<Base> call, Throwable t) {

                }
            });
        }
    }

    private Realm realm;
    private Base base;

    private Retrofit retrofit;
    private Call<Base> call;
    private GetSearchResults getSearchResults;

    private ListViewAdapter adapter;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRealm();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPresenter();
        initAdapter();
    }

    public void initRealm() {
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public void initPresenter() {
        presenter = new PresenterImplementation();
        presenter.setView(this);
    }

    public void initAdapter() {
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        if(!realm.isEmpty()) {
            presenter.getRealmData();
        }
    }

    public boolean checkNetworkStatus() {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public void initDataObject() {
        base = realm.createObject(Base.class);
    }

    public void initRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create
                ()).build();
    }

    public void initCall() {
        getSearchResults = retrofit.create(GetSearchResults.class);
        call = getSearchResults.getItemsFromAPI(inputField.getText().toString(), Constants.CX, Constants.API_KEY);
    }

    @Override
    public void setAdapterItems(List<Item> data) {
        adapter.setAdapterItems(data);
    }
}
