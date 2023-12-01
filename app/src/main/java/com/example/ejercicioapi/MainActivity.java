package com.example.ejercicioapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService x= new
                WebService("https://uealecpeterson.net/turismo/categoria/getlistadoCB",
                datos, MainActivity.this, MainActivity.this);
        x.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtMostrarVista1);
        String lstNombres="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstNombres = lstNombres + "\n" +
                    banco.getString("id") + " - " +
                    banco.getString("descripcion").toString();
        }
        txtResp.setText("Respuesta de categorías" + lstNombres);
    }

    public void BtSegundaActividad(View view)
    {
        EditText editTextNumber = findViewById(R.id.IdCategoria);
        String numCategoria = editTextNumber.getText().toString();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("ID_CATEGORIA", numCategoria);
        startActivity(intent);
    }

}