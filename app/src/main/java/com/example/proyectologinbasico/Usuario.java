package com.example.proyectologinbasico;

import android.os.Bundle;
//Importo librerias para el video
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
//Importo librerias para el webView
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//Importo librerias para reproducir sonidos.
import android.media.MediaPlayer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class Usuario extends AppCompatActivity {

    @Override
    //Defino la Actividad usuario para levantar la interfaz grafica
    //Levanto el activity Usuario
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        //configuracion para reproducir el video de manera local.
        VideoView videoView = findViewById(R.id.VideoView);
        //Construyo una URI para el video seleccionado.
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.remix;
        //Convierto la cadena String a un URI
        Uri uri = Uri.parse(videoPath);
        //Asigno la uri al video
        videoView.setVideoURI(uri);

        //Agrego los controles de repoducción de Media.
        MediaController mediaController = new MediaController(this);
        //Enlazo el video con los controles de media.
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        //Reproduza el video al iniciar la aplicacion.
        videoView.start();

        //Configuramos el webView para cargar un video YT.
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        //Habilito Javascript para cargar un contenido dinamico.
        webSettings.setJavaScriptEnabled(true);
        //Agrego URL del video de YT en Formato Embed.
        String videoUrl = "https://www.youtube.com/embed/QdBZY2fkU-0";
        //Aseguro que carge el video al abrir el navegador en la APP.
        webView.setWebViewClient(new WebViewClient());
        //Cargo el video de la URL.
        webView.loadUrl(videoUrl);

        //Configura el botón para reproducir un sonido al presionarlo.
        findViewById(R.id.Sonido).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creo un reproductor de media para reproducir el sonido.
                MediaPlayer mediaPlayer = MediaPlayer.create(Usuario.this, R.raw.song);
                //Inicio la reproduccion del sonido.
                mediaPlayer.start();
                //Libero recursos una vez terminado el sonido.
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Libero recursos.
                        mediaPlayer.release();
                    }
                });
            }
        });
    }
}
