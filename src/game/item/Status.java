package game.item;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, // wont take any damage
    INSTANT_KILL, // kll enemies instantly
    DESTROY_HIGH_GROUND, //can step into high ground and destroy it
    PATH_OF_GOLD, //when high ground is destroyed turn into coin
    GUARANTEED_JUMP, // jump success rate turn 100%
    DMG_UP, // increase base damage by 15
    RESCUE_PRINCESS,
    FIRE_ATTACK


}
