package com.example.martinmelo.consumiendoapis;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private ArrayAdapter<String> adapter;

    public BlankFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.fragment_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.refresh){
            consumoTask task = new consumoTask();
            task.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_blank, container, false);

        String[] data = {
                "Claxons",
                "Panda",
                "DLD",
                "Juan Solo",
                "The Beatles"
        };


        List<String> fakedata = new ArrayList<String>(Arrays.asList(data));

        //definimos el adapter
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.el_item, R.id.elTexto, fakedata);

        //Inflamos la lista
        ListView listview = (ListView) rootView.findViewById(R.id.lalista);
        listview.setAdapter(adapter);

        //retornarnos el rootView oseasemelese infrlam el fragment
        return rootView;

    }

    public class consumoTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            Log.v("Mensajito","Comenzando");

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStringResponse = null;

            try{
                //url de la API
                URL url = new URL("https://agile-thicket-30819.herokuapp.com/api/vacantes/");

                //abrimos la conexion con el servidor
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Leemos la respuesta del servidor
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null){
                    return null;
                }
                //esto se encarga de leer la respuesta que se encuentra en el inputStream
                reader = new BufferedReader(new InputStreamReader(inputStream));

                //Leemos linea por linea
                String line;
                while((line = reader.readLine()) != null){
                    buffer.append(line + "\n");
                }

                //seguridad
                if (buffer.length() == 0){
                    return null;
                }

                //Al fin tenemos la respuesta, hay que guardar en un string
                //Porque ahorita es un Buffer, me caga

                jsonStringResponse = buffer.toString();
                Log.v("Respuesta", jsonStringResponse);
            }
            catch(Exception e){
                Log.e("error","Error",e);
                return null;
            }
            finally{
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try{
                        reader.close();
                    }
                    catch(final IOException e){
                        Log.e("error","Error cerrando",e);
                    }
                }
            }

            //Aqui vamos a parsear la respuesta del servidor
            try{
                return getDataFromJson(jsonStringResponse);
            }
            catch(JSONException e){
                Log.e("error",e.getMessage(),e);
                e.printStackTrace();
            }
            return null;
        }

        private String[] getDataFromJson(String jsonStringResponse)
        throws JSONException{

            //Declaramos variables globales para no harcodear xD
            final String FIELDS = "fields";
            final String VACANTE = "puesto_solicitante";

            //convertimos y parseamos
            JSONArray todo = new JSONArray(jsonStringResponse);
            //Lista vacia
            String[] resultStrs = new String[todo.length()];

            //hacemos una iteracion para conseguir lo que quiero #mujeres
            for(int i = 0; i < todo.length(); i++){
                JSONObject elemento = todo.getJSONObject(i);
                JSONObject fields = elemento.getJSONObject(FIELDS);
                String vacante = fields.getString(VACANTE);

                resultStrs[i] = vacante;
            }

            return resultStrs;
        }

        @Override
        protected void onPostExecute(String[] result){
            if(result != null){
                adapter.clear();
                adapter.addAll(result);
            }
        }

    }

}
