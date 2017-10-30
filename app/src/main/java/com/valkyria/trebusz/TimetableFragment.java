package com.valkyria.trebusz;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TimetableFragment extends Fragment {

    SharedPreferences dataSP;
    String groupNumber;

    public TimetableFragment() {
    }

    public static TimetableFragment newInstance() {
        return new TimetableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_timetable, container, false);

        getGroupNumber(v);

        setFont(v);

        PageDownloader page = new PageDownloader(v);
        page.execute();




        return v;
    }




    private void setFont(View v) {
        TextView title = (TextView) v.findViewById(R.id.timetable_title);
        TextView group_number = (TextView) v.findViewById(R.id.group_number);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Jaapokkienchance-Regular.otf");

        group_number.setTypeface(font);
        title.setTypeface(font);
    }

    private void getGroupNumber(View v) {
        dataSP = this.getActivity().getSharedPreferences("Kwestionariusz", Context.MODE_PRIVATE);
        groupNumber = dataSP.getString("grupa", "Restart App");
        TextView txt = (TextView) v.findViewById(R.id.group_number);
        txt.setText(groupNumber);
    }

}
