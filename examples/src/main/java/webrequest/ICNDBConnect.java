package webrequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.URL;

/**
 * Here is a way to use the base class with interface.
 */
public class ICNDBConnect extends RESTCall implements ICNDBApi {

    public ICNDBConnect() throws Exception{
        super(new URL("https://api.icndb.com/"));
    }
    @Override
    public JokeRepsonse getRandomJoke() {
        return this.call(RESTCall.GET, "jokes/random", JokeRepsonse.class);
    }

    public static void main(String[] args) throws Exception{
        ICNDBApi icndb = new ICNDBConnect();
        System.out.println(icndb.getRandomJoke().value.joke);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.icndb.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ICNDBApi2 service = retrofit.create(ICNDBApi2.class);
        Call<String> repos = service.getRandomJoke();
        String s = repos.execute().body();
        System.out.println(s);
    }
}
