package com.ahmed.customapp.QuranKareem.QuranApi;


import com.ahmed.customapp.QuranKareem.QuranApi.Models.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("radio/radio_ar.json")
    Call<RadioResponse> getRadioChannels();

}

