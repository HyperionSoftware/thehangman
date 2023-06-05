package cat.udl.gtidic.course2223.teacher.thehangman.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import cat.udl.gtidic.course2223.teacher.thehangman.R;
import cat.udl.gtidic.course2223.teacher.thehangman.views.MainActivity;

public class Init extends AppCompatActivity {

    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        findViewById(R.id.buttonStartGame).setOnClickListener(v -> startGame());
    }

    private void startGame() {
        userName = findViewById(R.id.editTextNomUsuari);
        String nomUsuari = userName.getText().toString();
        if(nomUsuari.trim().isEmpty()){
            Toast.makeText(this, R.string.notValidName, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("key_username", nomUsuari);
        startActivity(intent);
    }
}