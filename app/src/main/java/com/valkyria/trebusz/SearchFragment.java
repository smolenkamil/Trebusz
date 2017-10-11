package com.valkyria.trebusz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return  new SearchFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        TextView most_searched = (TextView) v.findViewById(R.id.most_searched);
        Button first = (Button) v.findViewById(R.id.first_searched);
        Button second = (Button) v.findViewById(R.id.second_searched);
        Button third = (Button) v.findViewById(R.id.third_searched);
        Button fourth = (Button) v.findViewById(R.id.fourth_searched);
        Button fifth = (Button) v.findViewById(R.id.fifth_searched);
        Button search = (Button) v.findViewById(R.id.search_button);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Jaapokki-Regular.otf");

        search.setTypeface(font);
        second.setTypeface(font);
        third.setTypeface(font);
        fourth.setTypeface(font);
        fifth.setTypeface(font);
        first.setTypeface(font);
        most_searched.setTypeface(font);

        return v;
    }

}
