package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.Random;

/**
 * 4 line of Toad speech are added into an arraylist.
 */
public class Monologue {
    // add four lines into dialog array and preprocess the random lines here to be called in monologueAction.

    ArrayList<String> dialog = new ArrayList<>();

    /**
     * Will add all lines into arraylist.
     * Random class is used to print lines randomly based on the index of arraylist.
     * return a line based on the randomly generated index.
     */
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

    /**
     * Will add 3 lines into arraylist.
     * Line number 2 regarding power stars will not be added.
     * Random class is used to print lines randomly based on the index of arraylist.
     * return a line based on the randomly generated index.
     */
    public String playerAtePS() {
        dialog.clear();
        dialog.add("You might need a wrench to smash Koopa's hard shells.");
        dialog.add("The Princess is depending on you! You are our only hope.");
        dialog.add(" Being imprisoned in these walls can drive a fungus crazy :(");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;


    }
    /**
     * Will add 3 lines into arraylist.
     * Line number 1 regarding wrench will not be added.
     * Random class is used to print lines randomly based on the index of arraylist.
     * return a line based on the randomly generated index.
     */
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


