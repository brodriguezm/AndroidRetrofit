package www.bryansteven.ec.ejemploretrofit1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new ConsumeService().execute();

        //Llamada asincrona
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://androidtutorials.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServices services = retrofit.create(APIServices.class);

        Call<Persona> response = services.findUserGET(1);

        response.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                Log.d("Nombre Persona ", response.body().getName());
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }


    // LLamada sincrona
    public static class ConsumeService extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            final String miUrl = "https://androidtutorials.herokuapp.com/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(miUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIServices services = retrofit.create(APIServices.class);

            Call<List<Persona>> response = services.getPersonaPOST();

            response.enqueue(new Callback<List<Persona>>() {
                @Override
                public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                    for(Persona persona : response.body()){
                        Log.d("NICk", persona.getNickName());}
                }

                @Override
                public void onFailure(Call<List<Persona>> call, Throwable t) {
                    Log.e("ERROR", t.getMessage());
                }
            });


            return null;
        }
    }

}
