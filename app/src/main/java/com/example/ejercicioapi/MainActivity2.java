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

public class MainActivity2 extends AppCompatActivity  implements Asynchtask {
    private String idCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String idCategoria = intent.getStringExtra("ID_CATEGORIA");
        String url = "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + idCategoria;
        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService(url, datos, MainActivity2.this, MainActivity2.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtMostrarSubcategoria);
        String lstNombres="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstNombres = lstNombres + "\n" +
                    banco.getString("id") + " - " +
                    banco.getString("categoria") + " - " +
                    banco.getString("descripcion").toString();
        }
        txtResp.setText("Lista de subcategorías:" + lstNombres);
    }
    public void BtTercerActividad(View view)
    {
        EditText editTextNumber = findViewById(R.id.idsubCategoria);
        String numCategoria = editTextNumber.getText().toString();
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("ID_CATEGORIA", idCategoria);
        intent.putExtra("ID_CATEGORIA2", numCategoria);
        startActivity(intent);
    }

}