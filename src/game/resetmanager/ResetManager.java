package game.resetmanager;

import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 *
 * @author Hee Zhan Zhynn (31989403) & FIT2099 Teaching Team
 * @version 1
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
//        System.out.println(this.resettableList.size()); //why is it empty?
        for (Resettable item: resettableList) {
            item.resetInstance();

        }
        for (int i=0; i< resettableList.size(); i++) {
            cleanUp(resettableList.get(i));
        }
    }

    /**
     * Add the Resettable instance to the list
     * FIXME: it does nothing, you need to implement it :)
     */
    public void appendResetInstance(Resettable item){
        this.resettableList.add(item);
    }

    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     * FIXME: it does nothing, you need to implement it :)
     */
    public void cleanUp(Resettable resettable){
        resettableList.remove(resettable);
    }

}
