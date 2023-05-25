package cat.udl.gtidic.course2223.teacher.thehangman.models;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static final String TAG = "Parcial";

    String[] possibleWords = {"Xiuxiuejar", "Aixopluc", "Caliu", "Tendresa", "Llibertat", "Moixaina", "Amanyagar", "Enraonar", "Ginesta", "Atzavara"};
    List<String> lettersChosen = new ArrayList<>();
    String secretWord;
    private final int LAST_ROUND = 7;

//    incrementa l'estat del joc (del penjat)
    public int currentRound = 0;

//    constants per controlar si la lletra a afegir és vàlida o no
    public static final int LETTER_VALIDATION_OK = 0;
    public static final int LETTER_VALIDATION_NO_VALID_BECAUSE_SIZE = 1;
    public static final int LETTER_VALIDATION_NO_VALID_BECAUSE_ALREADY_SELECTED = 2;

//    atributs que no mostra el game però que si que actualitza
//    estan pensants per poder ésser persists fàcilment
    Boolean playerWon;
    String lettersChosenStr;

    /**
     * Inicialitza el joc escollint la paraula secreta
     */
    public Game(){
        int random = new Random().nextInt(possibleWords.length);
        secretWord = possibleWords[random];
        secretWord = secretWord.toUpperCase();
        Log.d(TAG, "He creat un nou game amb la paraula a descobrir: " + secretWord);
    }

    /**
     * Construeix la lletra visible per ser consumida des de la view
     * @return String amb les lletres ocultes / visibles en funcio de les escollides
     */
    public String visibleWord(){
        String[] lettersToDiscover = secretWord.split("");
        String wordToReturn = "";
        for (String s : lettersToDiscover) {
            wordToReturn += lettersChosen.contains(s) ? s : "_";
            wordToReturn += " ";
        }
        return wordToReturn;
    }

    /**
     * Controla si el joc ha acabat
     * @return true si s'ha acabat
     */
    public boolean isGameOver() {
        if (currentRound >= LAST_ROUND){
            playerWon = false;
            persistData();
            return true;
        }
        return isPlayerTheWinner();
    }

    /**
     * Busca si totes les lletres de la paraula a resoldre han estat insertades
     * @return true quan totes les lletres han estat trobades
     */
    public boolean isPlayerTheWinner(){
        String[] lettersToDiscover = secretWord.split("");
        for (String s : lettersToDiscover) {
            if (!lettersChosen.contains(s)) return false;
        }
        playerWon = true;
        persistData();
        return true;
    }

    /**
     * Persistir dades a Firebase
     */
    private void persistData() {
        String url = "https://dam-parcial1-6183a-default-rtdb.europe-west1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");

        database.getReference("secretWord").setValue(secretWord);
        database.getReference("currentRound").setValue(currentRound);
        database.getReference("playerWon").setValue(playerWon);
        database.getReference("lettersChosenStr").setValue(lettersChosenStr);
    }

    /**
     * Retorna si la lletra es nova, és a dir, que no està inclosa en lettersChosen
     * @param newLetter lletra a verificar si es nova
     * @return si la lletra ja ha estat afegida anteriorment
     */
    public boolean isNewLetter(String newLetter){
        return !lettersChosen.contains(newLetter);
    }

    /**
     * Afegeix la lletra si es vàlida
     * @param letter a insertar
     * @return true si la lletra es valida
     */
    public int addLetter(String letter){
        if (letter.length() != 1) return LETTER_VALIDATION_NO_VALID_BECAUSE_SIZE;
        if (!isNewLetter(letter)) return LETTER_VALIDATION_NO_VALID_BECAUSE_ALREADY_SELECTED;

        lettersChosen.add(letter.toUpperCase());
        if (!isALetterExpected(letter)) currentRound++;

        lettersChosenStr = lettersChosen.toString();
        Log.d(TAG, lettersChosenStr);

        return LETTER_VALIDATION_OK;
    }

    /**
     * retorna si es una lletra nova descoberta o no
     * @param letter String amb la nova lletra
     * @return si la lletra es bona
     */
    private boolean isALetterExpected(String letter){
        return secretWord.contains(letter);
    }

    /**
     * Converteix a string les lletres escollides
     * @return String amb les lletres escollides
     */
    public String lettersChosen(){
        return String.join(", ", lettersChosen);
    }

    /**
     * Informa la ronda de joc
     * @return int amb el número de ronda del joc
     */
    public int getCurrentRound() {
        return currentRound;
    }
}
