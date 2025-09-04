package jp.ac.meijou.android.s241205019;

import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.stream.Collectors;

import jp.ac.meijou.android.s241205019.databinding.ActivityMain6Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity6 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private ActivityMain6Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        var request = new Request.Builder()
                .url("https://placehold.jp/350x350.png")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                runOnUiThread(() -> binding.imageMain.setImageBitmap(bitmap));

            }
        });

        binding.buttonGet.setOnClickListener(view -> {
            var text = binding.editTextTitle.getText().toString();
            var url = Uri.parse("https://placehold.jp/bf4569/ffffff/700x700.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();

            getImage(url);
        });

    }


    private void getImage(String url){
        var request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                runOnUiThread(() -> binding.imageMain.setImageBitmap(bitmap));
            }
        });
    }



}