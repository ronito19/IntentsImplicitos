package com.example.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edt_web = null;
    EditText edt_mapa = null;
    EditText edt_telefono = null;
    EditText edt_texto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_web = (EditText) findViewById(R.id.edt_web);
        edt_mapa = (EditText) findViewById(R.id.edt_mapa);
        edt_telefono = (EditText) findViewById(R.id.edt_telefono);
        edt_texto = (EditText) findViewById(R.id.edt_texto);
    }



    public void abrirweb(View view) {
        String textourl = String.valueOf(edt_web.getText());
        if(textourl.isEmpty())
        {
            edt_web.setError(" Introducir una direccion web ");
            return;
        }
        if (!textourl.startsWith("http://") && !textourl.startsWith("https://"))
        {
            //textourl = "http://" + textourl;
         }

        Uri urlweb = Uri.parse(textourl);
        Intent intent = new Intent(Intent.ACTION_VIEW, urlweb);
        startActivity(intent);

    }



    public void abrirmapa(View view) {
        String textomapa = String.valueOf(edt_mapa.getText());
        textomapa = "geo:0,0?q=" + textomapa;
        Uri urlmapa = Uri.parse(textomapa);
        Intent intent = new Intent(Intent.ACTION_VIEW, urlmapa);
        if(intent.resolveActivity(getPackageManager())!=null)
        startActivity(intent);
    }



    public void llamar(View view) {
        String textotelefono = String.valueOf(edt_telefono.getText());
        if(textotelefono.isEmpty())
        {
            edt_telefono.setError(" Pon un numero de telefono ");
            return;
        }
        textotelefono = "tel:" + textotelefono;
        Uri urltelefono = Uri.parse(textotelefono);
        Intent intent = new Intent(Intent.ACTION_DIAL, urltelefono);
        startActivity(intent);
    }



    public void copiarTexto(View view) {
        String texto = String.valueOf(edt_texto.getText());
        if(texto.isEmpty())
        {
            edt_texto.setError(" Debes poner un texto ");
            return;
        }
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.abrir_texto_con)
                .setText(texto)
                .startChooser();
    }
}