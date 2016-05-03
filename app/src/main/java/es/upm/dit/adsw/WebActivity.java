package es.upm.dit.adsw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebActivity extends AppCompatActivity {
    private static final String TAG = WebActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Bundle extras = getIntent().getExtras();
        String url = extras.getString("url");
        if (url == null) {
            Log.e(TAG, "Falta parámetro url");
            finish();
        }
        WebView web = (WebView) findViewById(R.id.web);
        // para que navegue en la misma página sin abrir página nueva
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        // Deberíamos usar esto http://developer.android.com/intl/es/training/implementing-navigation/temporal.html
        Button boton = (Button) findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebActivity.this, ActivityListSimpleAdapter.class);
                startActivity(intent);
            }
        });
    }
}
