package com.example.martinmelo.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Llist extends Fragment {


    public Fragment_Llist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //declaro mis datos
        String[] data = {
                "Pizza",
                "Pastes",
                "Hamburguesas",
                "Aigre",
                "Maruchan",
                "Atun",
                "Chilaquiles",
                "Tacos de canasta"

        };
        //casteo mis datos a una lista de datos
        List<String> fakeData = new ArrayList<String>(Arrays.asList(data));

        //Definimos el Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.item,R.id.elTexto,fakeData);

        View rootView =  inflater.inflate(R.layout.fragment_fragment__llist, container, false);
        //encontrremos la vista
        ListView listView = (ListView) rootView.findViewById(R.id.laLista);
        listView.setAdapter(adapter);
        return rootView;

    }

}
