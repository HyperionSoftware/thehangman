package cat.udl.gtidic.course2223.teacher.thehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitActivity extends AppCompatActivity {

    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        findViewById(R.id.btnStartGame).setOnClickListener(view -> startGame());
    }

    private void startGame(){
        etUsername = findViewById(R.id.etUsername);
        String nomUsuari = etUsername.getText().toString();
        if (nomUsuari.trim().isEmpty()){
            Toast.makeText(this, "Not valid name", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("key_username", nomUsuari);
        startActivity(i);
    }
}