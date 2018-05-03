package www.bryansteven.ec.ejemploretrofit1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dell on 02/05/2018.
 */

public interface APIServices {

    @GET ("usersFake")
    Call<List<Persona>> getPersonaGET();

    @POST("usersFake")
    Call<List<Persona>> getPersonaPOST();

    //https://androidtutorials.herokuapp.com/findUser?id=1
    @GET("findUser")
    Call<Persona> findUserGET(@Query("id") int idUser);
}
