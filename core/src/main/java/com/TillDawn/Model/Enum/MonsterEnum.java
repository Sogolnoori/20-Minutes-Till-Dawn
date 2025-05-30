package com.TillDawn.Model.Enum;

public enum MonsterEnum {
    Tree (1, 0),
    Tentacle (25, 0.5f),
    EyeBat (50, 0.5f),
    Boss (400, 0.05f);

    private final int hp;
    private final float speed;

    MonsterEnum(int hp, float speed) {
        this.hp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public float getSpeed() {
        return speed;
    }
}
