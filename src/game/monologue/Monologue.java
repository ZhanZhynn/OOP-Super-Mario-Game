package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.Random;

public class Monologue {
    // add four lines into dialog array and preprocess the random lines here to be called in monologueAction.

    ArrayList<String> dialog = new ArrayList<>();



    public String allDialog() {
        dialog.clear();
        dialog.add("You might need a wrench to smash Koopa's hard shells.");
        dialog.add("You better get back to finding the Power Stars.");
        dialog.add("The Princess is depending on you! You are our only hope.");
        dialog.add(" Being imprisoned in these walls can drive a fungus crazy :(");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;
    }

    public String playerAtePS() {
        dialog.clear();
        dialog.add("You might need a wrench to smash Koopa's hard shells.");
        dialog.add("The Princess is depending on you! You are our only hope.");
        dialog.add(" Being imprisoned in these walls can drive a fungus crazy :(");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;


    }

    public String playerWithWrench() {
        dialog.clear();
        dialog.add("You better get back to finding the Power Stars.");
        dialog.add("The Princess is depending on you! You are our only hope.");
        dialog.add(" Being imprisoned in these walls can drive a fungus crazy :(");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;

    }
}


