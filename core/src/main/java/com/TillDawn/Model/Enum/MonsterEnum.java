package com.TillDawn.Model.Enum;

public enum MonsterEnum {
    Tree (1),
    Tentacle (25),
    EyeBat (50),
    Elder (400);

    private final int hp;

    MonsterEnum(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
