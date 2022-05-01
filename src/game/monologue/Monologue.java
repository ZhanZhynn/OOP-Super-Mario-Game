package game.monologue;

import java.util.HashMap;

public class Monologue {
    // Create a HashMap object called monologueOptions
    HashMap<Integer, String> monologueOptions;

    public Monologue() {
        // Add keys and values (int, speech)
        monologueOptions = new HashMap<>();
        monologueOptions.put(1, "You might need a wrench to smash Koopa's hard shells.");
        monologueOptions.put(2, "You better get back to finding the Power Stars.");
        monologueOptions.put(3, "The Princess is depending on you! You are our only hope.");
        monologueOptions.put(4, "Being imprisoned in these walls can drive a fungus crazy :(");


    }
}


