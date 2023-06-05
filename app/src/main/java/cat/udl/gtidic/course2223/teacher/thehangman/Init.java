package cat.udl.gtidic.course2223.teacher.thehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Init extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        findViewById(R.id.buttonStartGame).setOnClickListener(v -> startGame());
    }

    private void startGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}