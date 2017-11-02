package com.valkyria.trebusz;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 30.10.2017.
 */

public class PageDownloader extends AsyncTask<Void, Void, Void> {

    final static String HOME_PAGE_URL = "http://planzajec.uek.krakow.pl/";
    //Tablica nazw kierunków
    String[] direction;
    //Tablica linków kierunków
    String[] directionLinks;
    //Lista nazw grup wszystkich kierunków
    List<String> group = new ArrayList<String>(100);
    //Lista linków grup wszystkich kierunków
    List<String> groupLinks = new ArrayList<String>(100);
    //View przekazywane od fragmentu albo activity
    View v;
    //Zmienna przechowująca link do dekodowania
    String linkToDecode;


    //Konstruktor przypisujący View od fragmentu albo activity
    public PageDownloader(View v) {
        this.v = v;
    }


    //Metoda dziedziczona po abstrakcyjnek klasie AsyncTask, która pozwala wykonywać kod w tle
    @Override
    protected Void doInBackground(Void... voids) {

        try {

            //Pobieranie strony UEK PLAN i wybranie z niej linków które są kierunkami
            Document doc = Jsoup.connect(HOME_PAGE_URL).get();
            Elements kat = doc.getElementsByAttributeValueStarting("href", "index.php?typ=G&grupa=K");

            //inicjalizacja tablicy i przypisanie jej rozmiaru równego ilości elementów "kat"
            direction = new String[kat.size()];
            directionLinks = new String[kat.size()];

            //pętla dopisująca do tablicy direction nazwy kierunkow i dekodujaca linki oraz zapisujaca je do tablicy directionLinks
            for (int i = 0; i < kat.size(); i++) {
                direction[i] = kat.get(i).text();
                linkToDecode = kat.get(i).attr("href");
                directionLinks[i] = URLDecoder.decode(linkToDecode, "UTF-8");
            }

            //pętla, która z każdego linku directionLinks pobiera nazwy i linki grup z każdego kierunku i dopisuje je do list group i groupLinks
            for (String link : directionLinks) {
                Connection.Response response = Jsoup.connect(HOME_PAGE_URL + link).execute();
                doc = response.parse();
                kat = doc.getElementsByAttributeValueStarting("href", "index.php?typ=G");
                for (int i = 0; i < kat.size(); i++) {
                    group.add(kat.get(i).text());
                    groupLinks.add(kat.get(i).attr("href"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Metoda dziedziczona po abstrakcynej klasie AsyncTask, która jest wywoływana po skończeniu się doInBackgroun
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Wczytanie LinearLayout do obiektu, który jest stworzony statycznie w XML layout/fragment_timetable.xml
        LinearLayout tabLo = (LinearLayout) v.findViewById(R.id.timetableLayout);
        //Pętla wyświetlająca w Moim Terminarzu jakieś testowe informacje
        for (int i = 0; i < 15; i++) {
            //Tworze dynamicznie TextView
            TextView groupView = new TextView(v.getContext());
            //Nadaje mu dynamicznie Parametry
            groupView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            groupView.setText(group.get(i) + "  :  " + groupLinks.get(i));
            groupView.setTextSize(20);
            //Dodaje go do obiektu LinearLayout stworzonego statycznie w XML layout/fragment_timetable.xml
            tabLo.addView(groupView);
        }
    }

}
