package cat.udl.gtidic.course2223.teacher.thehangman.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.gtidic.course2223.teacher.thehangman.databinding.ActivityMainBinding;
import cat.udl.gtidic.course2223.teacher.thehangman.models.Game;
import cat.udl.gtidic.course2223.teacher.thehangman.R;
import cat.udl.gtidic.course2223.teacher.thehangman.viewmodels.GameViewModel;

public class MainActivity extends AppCompatActivity {

    Button btnNewLetter;
    //    TextView visibleWord;
    TextView lettersChosen;
    EditText etNewLetter;
    ImageView ivState;
    //    Game game;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userNameRebut = this.getIntent().getExtras().getString("key_username");

//        here is a good place to implement MVVM if someone is interested
        // Obtain the ViewModel component.
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Assign the component to a property in the binding class.
        binding.setViewmodel(gameViewModel);
        binding.setLifecycleOwner(this);

//        initializing views
        btnNewLetter = findViewById(R.id.btnNewLetter);
        btnNewLetter.setOnClickListener(v -> newLetter());
//        visibleWord = findViewById(R.id.tvVisibleWord);
        lettersChosen = findViewById(R.id.tvLettersChosen);
        etNewLetter = findViewById(R.id.etNewLetter);
        ivState = findViewById(R.id.ivState);
        TextView tv = findViewById(R.id.tvUserNameRebut);
        tv.setText(userNameRebut);

//        starting game mechanics
        startGame();
    }

    /**
     * Retorna el Drawable segons l'estat correcte
     */
    private Drawable getDrawableFromState(int state){
        int r_desired = -1;

        switch (state){
            case 0: r_desired = R.drawable.round_0; break;
            case 1: r_desired = R.drawable.round_1; break;
            case 2: r_desired = R.drawable.round_2; break;
            case 3: r_desired = R.drawable.round_3; break;
            case 4: r_desired = R.drawable.round_4; break;
            case 5: r_desired = R.drawable.round_5; break;
            case 6: r_desired = R.drawable.round_6; break;
            case 7: r_desired = R.drawable.round_7; break;
        }
        return ContextCompat.getDrawable(this, r_desired);
    }

    /**
     * Actualitza les views de la pantalla
     */
    private void refreshWords(){
//        visibleWord.setText(gameViewModel.getGame().visibleWord());
        lettersChosen.setText(gameViewModel.getGame().lettersChosen());
        ivState.setImageDrawable(getDrawableFromState(gameViewModel.getGame().getCurrentRound()));
    }

    /**
     * Afegeix la lletra al joc
     */
    private void newLetter(){
        String novaLletra = etNewLetter.getText().toString().toUpperCase();
        etNewLetter.setText("");

//        int validLetter = gameViewModel.getGame().addLetter(novaLletra);
        int validLetter = gameViewModel.addLetter(novaLletra);
        if (validLetter != Game.LETTER_VALIDATION_OK){
            Log.d(Game.TAG, "Lletra no vàlida");
            if (validLetter == Game.LETTER_VALIDATION_NO_VALID_BECAUSE_ALREADY_SELECTED){
                Toast.makeText(this, R.string.notValidBecauseAlreadyChosen, Toast.LENGTH_SHORT ).show();
            }
            if (validLetter == Game.LETTER_VALIDATION_NO_VALID_BECAUSE_SIZE){
                Toast.makeText(this, R.string.notValidBecauseSize, Toast.LENGTH_SHORT ).show();
            }
        }
        Log.d(Game.TAG, "Estat actual: " + gameViewModel.getGame().getCurrentRound());

        refreshWords();
        hideKeyboard();
        checkGameOver();
    }

    /**
     * Revisa si el joc ha acabat i informa via Log (de moment)
     */
    private void checkGameOver(){
        if (gameViewModel.getGame().isPlayerTheWinner()){
            Log.d(Game.TAG, "El jugador ha guanyat!");
        }

        if (gameViewModel.getGame().isGameOver()){
            Log.d(Game.TAG, "El Joc ha acabat");
            btnNewLetter.setEnabled(false);
            etNewLetter.setEnabled(false);
            finish();
        }
    }

    /**
     * Inicia el joc i actualitza l'activitat
     */
    private void startGame(){
//        game = new Game();
        gameViewModel.startGame();
        refreshWords();
    }

    /* -------- METODES AUXILIARS --------- */

    /**
     * Amaga el teclat virtual de la pantalla
     */
    private void hideKeyboard(){
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}