package jp.ac.meijou.android.s241205019;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205019.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205019.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.buttonBento.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        binding.buttonYahoo.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.buttonSend.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            intent.putExtra("intent_text",binding.editTextSend.getText().toString());
            startActivity(intent);
        });

        binding.buttonSend4.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity4.class);
            intent.putExtra("intent_text",binding.editTextSend.getText().toString());
            startActivity(intent);
        });
    }
}