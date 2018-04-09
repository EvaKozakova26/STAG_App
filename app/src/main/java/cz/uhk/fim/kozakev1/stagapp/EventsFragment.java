package cz.uhk.fim.kozakev1.stagapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.uhk.fim.kozakev1.stagapp.model.CalendarItem;
import cz.uhk.fim.kozakev1.stagapp.services.StagInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    @BindView(R.id.layoutEvents)
    LinearLayout linearLayout;

    StagInterface stagInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://stag-demo.uhk.cz/ws/services/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stagInterface = retrofit.create(StagInterface.class);

        new AsyncTask<String, Void, List<CalendarItem>>() {
            @Override
            protected List<CalendarItem> doInBackground(String... strings) {
                Call<List<CalendarItem>> listCall = stagInterface.lisCalendarItems(strings[0]);
                try {
                    listCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute("2018");

       /* // nove vlakno
        new AsyncTask<String, Integer, List<String>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<String> doInBackground(String... strings) { //nula az n stringů
                //bezi na pozadi, nemuze volat context, cili menit na UI
                System.out.println("doInBack" + strings);
                String nickname = strings[0];
                List<String> list = new ArrayList<>();
                for (int i = 0; i < nickname.length(); i++) {
                    publishProgress((int)((float)i / nickname.length() * 100));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(nickname.substring(i, i + 1));
                }
                return list;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                int percentage = values[0];
                Toast.makeText(getContext(), "procent " + percentage, Toast.LENGTH_LONG).show();
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                for (String s : strings) {
                    System.out.println("OnPOstExecute" + s);
                    TextView textView = new TextView(getContext());
                    textView.setText(s);
                    linearLayout.addView(textView);
                }


                super.onPostExecute(strings);
            }
        }.execute("kozakev1");*/


    }

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, null);
        ButterKnife.bind(this, view);

        TextView title = new TextView(view.getContext());
        title.setText("events");

        return view;
    }

}
