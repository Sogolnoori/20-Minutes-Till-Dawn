package com.TillDawn.Model;

import java.util.ArrayList;
import java.util.Random;

public class MonsterSpawner {

    private final ArrayList<Monster> monsters;

    private final float tentacleSpawnInterval = 3.0f;
    private final float eyeBatSpawnInterval = 10.0f;

    private float timeSinceLastTentacleSpawn = 0f;
    private float timeSinceLastEyeBatSpawn = 0f;
    private float timeSpent = 0f;
    private float totalTime;

    public MonsterSpawner(ArrayList<Monster> monsters, float totalTime) {
        this.monsters = monsters;
        this.totalTime = totalTime;
    }

    public void update(float deltaTime) {
        timeSpent += deltaTime;
        timeSinceLastEyeBatSpawn += deltaTime;
        timeSinceLastTentacleSpawn += deltaTime;

        if (timeSinceLastEyeBatSpawn >= eyeBatSpawnInterval) {
            spawnEyeBat();
            timeSinceLastTentacleSpawn = 0f;
        }

        if (timeSinceLastTentacleSpawn >= tentacleSpawnInterval) {
            spawnTentacle();
            timeSinceLastEyeBatSpawn = 0f;
        }
    }

    private void spawnEyeBat() {
        if(timeSpent < totalTime / 4){
            return;
        }
        int amount = (int)((timeSpent * 4 - totalTime + 30) / 30);
        for (int i = 0; i < amount; i++) {
            newMonster(2);
        }
    }

    private void spawnTentacle() {
        int amount = (int)(timeSpent / 30);
        for (int i = 0; i < amount; i++) {
            newMonster(1);
        }
    }

    public void newMonster(int type){
        Random rand = new Random();
        float x, y;
        if(rand.nextBoolean()){
            x = App.getCurrentGame().getMapWidth() * rand.nextFloat();
            y = App.getCurrentGame().getMapHeight() * rand.nextInt(2);
        }
        else{
            x = App.getCurrentGame().getMapWidth() * rand.nextInt(2);
            y = App.getCurrentGame().getMapHeight() * rand.nextFloat();
        }
        Monster monster = new Monster(type, x, y);
        monsters.add(monster);
    }
}
