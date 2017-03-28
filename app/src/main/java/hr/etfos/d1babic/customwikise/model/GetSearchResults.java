package hr.etfos.d1babic.customwikise.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public interface GetSearchResults {

    @GET("v1")
    Call<Base> getItemsFromAPI(@Query("q")String searchTerm, @Query("cx") String searchEngine, @Query("key") String
            apiKey);
}
