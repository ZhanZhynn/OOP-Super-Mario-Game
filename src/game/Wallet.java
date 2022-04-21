package game;

import edu.monash.fit2099.engine.items.Item;

/**
 * Wallet system
 * @author Ng Zu Shen
 * @version 1.0
 */
public class Wallet extends Item {

    /**
     * wallet balance as integer
     */
    private int balance = 0;

    /**
     * constructor of wallet, need only wallet balance(int) as parameter
     * @param balance
     */
    public Wallet(int balance) {
        super("wallet", 'w', true);
        this.balance = balance;
    }

    /**
     * increase balance
     * @param amount the amount as integer
     */
    public void increaseBalance(int amount){
        balance += amount;
    }

    /**
     * decrease balance
     * @param amount the amount as integer
     */
    public void decreaseBalance(int amount){
        balance -= amount;
    }

    /**
     * getter of balance
     * @return the balance as integer
     */
    public int getBalance(){return balance;}
}
