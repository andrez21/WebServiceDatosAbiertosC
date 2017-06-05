package com.andresbasante.webservicedatosabiertosc.datosAPI;

import com.andresbasante.webservicedatosabiertosc.models.Vehiculos;
import com.andresbasante.webservicedatosabiertosc.models.Vias;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Acer on 22/05/2017.
 */
public interface DatosOpenApiService
{
    @GET("rvmt-7x4r.json")
    Call<List<Vehiculos>> obtenerListaVehiculos();

    @GET("qvqk-dtmf.json")
    Call<List<Vias>> obtenerListaVias();
}
