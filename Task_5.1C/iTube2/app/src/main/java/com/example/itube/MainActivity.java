package com.example.itube;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button buttonPlay, buttonSave, buttonViewPlaylist;
    private WebView webView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewPlaylist = findViewById(R.id.buttonViewPlaylist);
        webView = findViewById(R.id.webView);
        dbHelper = new DatabaseHelper(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVideo();
            }
        });

        buttonViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlaylistActivity.class));
            }
        });
    }

    private void playVideo() {
        String url = editTextUrl.getText().toString().trim();
        if (!url.isEmpty()) {
            String videoId = extractVideoId(url);
            if (videoId != null) {
                String embedUrl = "https://www.youtube.com/embed/" + videoId + "?autoplay=1";
                webView.loadUrl(embedUrl);
            } else {
                Toast.makeText(this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveVideo() {
        String url = editTextUrl.getText().toString().trim();
        if (!url.isEmpty()) {
            dbHelper.insertVideo(url);
            Toast.makeText(this, "Video saved to playlist", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
        }
    }

    private String extractVideoId(String url) {
        try {
            if (url.contains("youtube.com/watch?v=")) {
                return url.split("v=")[1].split("&")[0];
            } else if (url.contains("youtu.be/")) {
                return url.split(".be/")[1].split("\\?")[0];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
