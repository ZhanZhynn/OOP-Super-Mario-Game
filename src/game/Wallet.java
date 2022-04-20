package game;

import edu.monash.fit2099.engine.items.Item;

public class Wallet extends Item {

    private int balance = 0;

    public Wallet(String name, char displayChar, boolean portable, int balance) {
        super(name, displayChar, portable);
        this.balance = balance;
    }

    public Wallet(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void increaseBalance(int amount){
        balance += amount;
    }

    public void decreaseBalance(int amount){
        balance -= amount;
    }

    public int getBalance(){return balance;}
}
