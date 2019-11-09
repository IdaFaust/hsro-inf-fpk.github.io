package webrequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICNDBApi2 {

    @GET("jokes/random")
    Call<String> getRandomJoke();
}
