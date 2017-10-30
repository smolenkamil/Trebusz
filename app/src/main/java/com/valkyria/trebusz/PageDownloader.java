package com.valkyria.trebusz;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Kamil on 30.10.2017.
 */

public class PageDownloader extends AsyncTask<Void,Void,Void> {

    String[] groups;
    String[] links;
    View v;
    TextView test_temporary;
    String test_temporaryv2 = "";

    public PageDownloader(View v) {
        this.v=v;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document doc = Jsoup.connect("http://planzajec.uek.krakow.pl/").get();
            Elements kat = doc.getElementsByAttributeValueStarting("href", "index.php?typ=G");
            groups = new String[kat.size()];
            links = new String[kat.size()];
            for(int i=0;i<kat.size();i++){
                groups[i]=kat.get(i).text();
                links[i]=kat.get(i).attr("href");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        test_temporary = (TextView) v.findViewById(R.id.test_temporary);
        for (String name: groups) {
            test_temporaryv2 += name + "\n\t";
        }
        test_temporary.setText(test_temporaryv2);
    }
}
