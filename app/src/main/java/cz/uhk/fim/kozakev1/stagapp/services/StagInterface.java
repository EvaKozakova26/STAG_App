package cz.uhk.fim.kozakev1.stagapp.services;

import java.util.List;

import cz.uhk.fim.kozakev1.stagapp.model.CalendarItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kozakev1 on 09.04.2018.
 */

public interface StagInterface {

    @GET("kalendar/getHarmonogramRoku?rok=(year)&outputFormat=json")
    Call<List<CalendarItem>> lisCalendarItems(@Path("year") String year);



}
