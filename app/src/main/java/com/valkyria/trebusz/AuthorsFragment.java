package com.valkyria.trebusz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sebastian Migacz on 11.10.2017.
 */

public class AuthorsFragment extends Fragment {

    public AuthorsFragment() {
    }

    public static AuthorsFragment newInstance() {
        return new AuthorsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authors, container, false);
    }

}