package com.andresbasante.webservicedatosabiertosc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andresbasante.webservicedatosabiertosc.datosAPI.DatosOpenApiService;
import com.andresbasante.webservicedatosabiertosc.models.Vehiculos;
import com.andresbasante.webservicedatosabiertosc.models.Vias;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public final static String TAG="OpenData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                 retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosVehiculos();
        obtenerDatosVias();
    }

    private void obtenerDatosVias()
    {
        DatosOpenApiService service = retrofit.create(DatosOpenApiService.class);
        Call<List<Vias>> viasRespuestaCall = service.obtenerListaVias();

        viasRespuestaCall.enqueue(new Callback<List<Vias>>()
        {
            @Override
            public void onResponse(Call<List<Vias>> call, Response<List<Vias>> response) {
                if(response.isSuccessful()){
                    List lista = response.body();

                    for(int i=0;i<lista.size();i++)
                    {
                        Vias p = (Vias) lista.get(i);
                        Log.i(TAG," tipo: "+p.getMuncipio()+" clase: "+p.getNombrevia());
                    }

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Vias>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }


    private void obtenerDatosVehiculos()
    {
        DatosOpenApiService service = retrofit.create(DatosOpenApiService.class);
        Call<List<Vehiculos>> vehiculoRespuestaCall = service.obtenerListaVehiculos();

        vehiculoRespuestaCall.enqueue(new Callback<List<Vehiculos>>()
        {
            @Override
            public void onResponse(Call<List<Vehiculos>> call, Response<List<Vehiculos>> response) {
                if(response.isSuccessful()){
                    List lista = response.body();

                    for(int i=0;i<lista.size();i++)
                    {
                        Vehiculos p = (Vehiculos) lista.get(i);
                        Log.i(TAG," tipo: "+p.getParticular()+" clase: "+p.getClase());
                    }

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Vehiculos>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }
}
