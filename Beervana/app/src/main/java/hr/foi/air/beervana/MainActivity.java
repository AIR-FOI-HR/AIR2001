package hr.foi.air.beervana;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Username;
    TextView Password;
    Button clientLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = (TextView) findViewById(R.id.etUsername);
        Password = (TextView) findViewById(R.id.etPassword);
        clientLogin = (Button) findViewById(R.id.btnClientLogin);
        clientLogin.setBackgroundColor(Color.parseColor("#F9D379"));
    }
}