package com.n1z3r.testapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class JokesTab<LoadJoke> extends Fragment {

    ListView listView;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    EditText txtCount;
    String count;
    private ArrayAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes_tab, container, false);


        final ListView listView = view.findViewById(R.id.list_view);
        txtCount = view.findViewById(R.id.txtCount);
        Button btnReload = view.findViewById(R.id.btnReload);



       btnReload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               count = txtCount.getText().toString();
                new JSONParseTask().execute();
               listView.setAdapter(itemsAdapter);
               arrayList = new ArrayList<>();
           }
       });

            return view;
        }

    private class JSONParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего сайта
            try {
                String str = "http://api.icndb.com/jokes/random/" + count + "";
                URL url = new URL(str);

                Log.d("@@@@", "URL: " + url);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;

        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            JSONObject dataJsonObj = null;
            String secondName = "";

            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray jsonObjects = dataJsonObj.getJSONArray("value");


                for (int i = 0; i < jsonObjects.length(); i++) {
                    JSONObject jsonObject = jsonObjects.getJSONObject(i);
                    String joke = jsonObject.getString("joke");
                    arrayList.add(joke);
                    itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
                    Log.d("@@@@", "joke: " + joke);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
