package jp.ac.meijou.android.s241205019;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s241205019.databinding.ActivityMain7Binding;

public class MainActivity7 extends AppCompatActivity {

    private ConnectivityManager connectivityManager;
    private ActivityMain7Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();

        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void updateTransport(Network network){
        var caps = connectivityManager.getNetworkCapabilities(network);

        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                transport = "Mobileつうしん";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                transport = "Wi-Fi";
            } else {
                transport = "そのほか";
            }

            binding.textTransport.setText(transport);
        }
    }

    private void updateIpAddress(Network network) {
        var linkProperties = connectivityManager.getLinkProperties(network);

        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));

            binding.textLink.setText(addresses);
        }
    }
}