package com.example.bicta;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.net.Uri;
public class movementpage extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movementpage);
        webview = (WebView)findViewById(R.id.webview);
        webView();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1="1";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formbody = new FormBody.Builder().add("number1", value1).build();
                Request request = new Request.Builder().url("http://192.168.100.10:8000/").post(formbody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(movementpage.this, "Network not found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        TextView textView = findViewById(R.id.textView2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });

            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1="2";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formbody = new FormBody.Builder().add("number1", value1).build();
                Request request = new Request.Builder().url("http://192.168.100.10:8000/").post(formbody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(movementpage.this, "Network not found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        TextView textView = findViewById(R.id.textView2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1="3";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formbody = new FormBody.Builder().add("number1", value1).build();
                Request request = new Request.Builder().url("http://192.168.100.10:8000/").post(formbody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(movementpage.this, "Network not found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        TextView textView = findViewById(R.id.textView2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1="4";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formbody = new FormBody.Builder().add("number1", value1).build();
                Request request = new Request.Builder().url("http://192.168.100.10:8000/").post(formbody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(movementpage.this, "Network not found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        TextView textView = findViewById(R.id.textView2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });
            }
        });
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1="5";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formbody = new FormBody.Builder().add("number1", value1).build();
                Request request = new Request.Builder().url("http://192.168.100.10:8000/").post(formbody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(movementpage.this, "Network not found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        TextView textView = findViewById(R.id.textView2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });
            }
        });



    }
    private void webView(){
        //Habilitar JavaScript (Videos youtube)
        webview.getSettings().setJavaScriptEnabled(true);
        //Handling Page Navigation
        webview.setWebViewClient(new MyWebViewClient());
        //Load a URL on WebView
        webview.loadUrl("http://192.168.100.10:8000/");
    }
    @Override public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
    // Subclase WebViewClient() para Handling Page Navigation
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("192.168.100.10:8000")) { //Force to open the url in WEBVIEW
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
