package cat.udl.gtidic.course2223.teacher.thehangman.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.gtidic.course2223.teacher.thehangman.models.Game;

public class GameViewModel extends ViewModel {
    Game game;
    private final MutableLiveData<String> visibleWord = new MutableLiveData<>();
    public LiveData<String> getVisibleWord() {
        return visibleWord;
    }
    public Game getGame() {
        return game;
    }

    public void startGame(){
        game = new Game();
    }

    public int addLetter(String novaLletra) {
        int value = game.addLetter(novaLletra);
        visibleWord.setValue(game.visibleWord());
        return value;
    }
}
