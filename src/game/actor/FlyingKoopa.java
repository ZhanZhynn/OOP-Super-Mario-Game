package game.actor;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Speakable;

import java.util.ArrayList;
import java.util.Random;

public class FlyingKoopa extends Koopa implements Speakable {
    ArrayList<String> dialog = new ArrayList<>();

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
    }

    @Override
    public String allDialog() {
        dialog.clear();
        dialog.add("Pam pam pam!");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;
    }
}
