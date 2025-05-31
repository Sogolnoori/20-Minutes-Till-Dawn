package com.TillDawn.Model.Enum;

public enum Hero {
    SHANA (4, 4),
    DIAMOND (7, 1),
    SCARLET (3, 5),
    LILITH (5, 3),
    DASHER (2, 10);

    private final int hp;
    private final int speed;

    Hero(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }
}
