package com.example.aplicacion21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.lang.StringBuilder;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {


    private TextView txtListadeContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtListadeContactos =  (TextView)findViewById(R.id.txtListadeContactos);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws
            JSONException {
        //txtListadeContactos.setText("Resp: " + result );
        String lstPersonas = "";
        JSONObject jsonObj = new JSONObject(result);
        JSONArray lstUsuarios = jsonObj.getJSONArray("users");
        for (int i = 0; i < lstUsuarios.length(); i++) {
            JSONObject personas = lstUsuarios.getJSONObject(i);

            lstPersonas = lstPersonas + "\n" +
                     "Contacto#" + (i+1)
                    + "\nNombre: " + personas.getString("firstName").toString()
            + "\nEdad: " + personas.getString("age").toString()
            + "\nEmail: " + personas.getString("email").toString() + "\n\n";
        }
        txtListadeContactos.setText("Lista de contactos:\n" + lstPersonas);

    }

}