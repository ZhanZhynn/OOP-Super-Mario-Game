//package game.action;
//
//import edu.monash.fit2099.engine.actions.Action;
//import edu.monash.fit2099.engine.actors.Actor;
//import edu.monash.fit2099.engine.items.Item;
//import edu.monash.fit2099.engine.positions.GameMap;
//import game.item.Status;
//import game.item.Wrench;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class SpeakAction extends Action{
//
//    ArrayList<String> dialogB = new ArrayList<>();
//    ArrayList<String> dialogP = new ArrayList<>();
//    ArrayList<String> dialogF = new ArrayList<>();
//    ArrayList<String> dialogK = new ArrayList<>();
//    ArrayList<String> dialogPR = new ArrayList<>();
//    ArrayList<String> dialogG = new ArrayList<>();
//
//
//
//    @Override
//    public String menuDescription(Actor actor) {
//        return actor + " TRIES TO SPEAK";
//    }
//
//
//    public String dialogBowser() {
//        dialogB.clear();
//        dialogB.add("What was that sound? Oh, just a fire.");
//        dialogB.add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
//        dialogB.add("Never gonna let you down!");
//        dialogB.add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
//        int index = new Random().nextInt(dialogB.size());
//        String string = dialogB.get(index);
//        return string;
//    }
//
//    public String dialogPiranha() {
//        dialogP.clear();
//        dialogP.add("Slsstssthshs~! (Never gonna say goodbye~)");
//        dialogP.add("Ohmnom nom nom nom.");
//        int index = new Random().nextInt(dialogP.size());
//        String string = dialogP.get(index);
//        return string;
//    }
//
//    public String dialogFlyingKoopa() {
//        dialogF.clear();
//        dialogF.add("Pam pam pam!");
//        int index = new Random().nextInt(dialogF.size());
//        String string = dialogF.get(index);
//        return string;
//    }
//
//    public String dialogKoopa() {
//        dialogK.clear();
//        dialogK.add("Never gonna make you cry!");
//        dialogK.add("Koopi koopi koopii~!");
//        int index = new Random().nextInt(dialogK.size());
//        String string = dialogK.get(index);
//        return string;
//    }
//
//    public String dialogPrincess() {
//        dialogPR.clear();
//        dialogPR.add("Dear Mario, I'll be waiting for you...");
//        dialogPR.add("Never gonna give you up!");
//        dialogPR.add("Release me, or I will kick you!");
//        int index = new Random().nextInt(dialogPR.size());
//        String string = dialogPR.get(index);
//        return string;
//    }
//
//    public String dialogGoomba() {
//        dialogG.clear();
//        dialogG.add("Mugga mugga!");
//        dialogG.add("Ugha ugha... (Never gonna run around and desert you...)");
//        dialogG.add("Ooga-Chaka Ooga-Ooga!");
//        int index = new Random().nextInt(dialogG.size());
//        String string = dialogG.get(index);
//        return string;
//    }
//
//    @Override
//    public String execute(Actor actor, GameMap map) {
//        String talkBack = "";
//
//        if (actor.getDisplayChar() == 'g') {
//            talkBack += dialogGoomba();
//        }
//        else if (actor.getDisplayChar() == 'P') {
//            talkBack += dialogPrincess();
//        }
//        else if (actor.getDisplayChar() == 'K') {
//            talkBack += dialogKoopa();
//        }
//        else if (actor.getDisplayChar() == 'F') {
//            talkBack += dialogFlyingKoopa();
//        }
//        else if (actor.getDisplayChar() == 'B') {
//            talkBack += dialogBowser();
//        }
//        else if (actor.getDisplayChar() == 'Y') {
//            talkBack += dialogPiranha();
//        }
//
//        return actor + "" + talkBack;
//    }
//}
