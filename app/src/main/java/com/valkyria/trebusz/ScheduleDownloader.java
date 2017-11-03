package com.valkyria.trebusz;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 03.11.2017.
 */

public class ScheduleDownloader extends AsyncTask<Void, Void, Void> {

    final static String HOME_PAGE_URL = "http://planzajec.uek.krakow.pl/";
    View v;
    Document doc;
    Elements kat, lat;

    List<String> termin = new ArrayList<String>(20);
    List<String> dzienGodzina = new ArrayList<String>(20);
    List<String> przedmiot = new ArrayList<String>(20);
    List<String> typ = new ArrayList<String>(20);
    List<String> nauczyciel = new ArrayList<String>(20);
    List<String> sala = new ArrayList<String>(20);

    public ScheduleDownloader(View v) {
        this.v = v;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            doc = Jsoup.connect(HOME_PAGE_URL + "index.php?typ=G&id=100821&okres=1").get();
            kat = doc.getElementsByTag("tr");
            for (int i = 0; i < kat.size(); i++) {
                lat = kat.get(i).children();
                termin.add(lat.get(0).text());
                dzienGodzina.add(lat.get(1).text());
                przedmiot.add(lat.get(2).text());
                typ.add(lat.get(3).text());
                nauczyciel.add(lat.get(4).text());
                sala.add(lat.get(5).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        LinearLayout tabLo = (LinearLayout) v.findViewById(R.id.timetableLayout);
        for(int i=1;i<kat.size();i++) {
            TextView groupView = new TextView(v.getContext());
            groupView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            StringBuilder sb = new StringBuilder();
            sb.append("Termin:  ");
            sb.append(termin.get(i));
            sb.append("\nDzień|Godzina:  ");
            sb.append(dzienGodzina.get(i));
            sb.append("\nPrzedmiot:  ");
            sb.append(przedmiot.get(i));
            sb.append("\nTyp:  ");
            sb.append(typ.get(i));
            sb.append("\nNauczyciel:  ");
            sb.append(nauczyciel.get(i));
            sb.append("\nSala:  ");
            sb.append(sala.get(i));
            sb.append("\n");
            groupView.setText(sb.toString());
            groupView.setTextSize(20);
            tabLo.addView(groupView);
        }
    }
}
