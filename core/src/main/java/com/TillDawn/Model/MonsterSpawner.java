package com.TillDawn.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MonsterSpawner {

    private final ArrayList<Monster> monsters;
    private final HashMap<Monster, Timer> shooterTimers;

    private static final float tentacleSpawnInterval = 3.0f;
    private static final float eyeBatSpawnInterval = 10.0f;

    private final Timer tentacleTimer;
    private final Timer eyeBatTimer;
    private float timeSpent = 0f;
    private final float totalTime;

    public MonsterSpawner(ArrayList<Monster> monsters, float totalTime) {
        this.monsters = monsters;
        this.totalTime = totalTime;
        this.tentacleTimer = new Timer(tentacleSpawnInterval);
        this.eyeBatTimer = new Timer(eyeBatSpawnInterval);
        this.shooterTimers = new HashMap<>();
    }

    public void update(float deltaTime) {
        timeSpent += deltaTime;
        tentacleTimer.update(deltaTime);
        eyeBatTimer.update(deltaTime);


        if (eyeBatTimer.isFinished()) {
            spawnEyeBat();
            eyeBatTimer.reset();
        }

        if (tentacleTimer.isFinished()) {
            spawnTentacle();
            tentacleTimer.reset();
        }

        Iterator<Monster> iterator = shooterTimers.keySet().iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (monster.isDying()) {
                iterator.remove();
                continue;
            }
            if(shooterTimers.get(monster).isFinished()){
                //monster.shoot();
                shooterTimers.get(monster).reset();
            }
        }

        if (timeSpent * 2 >= totalTime) {}
    }

    private void spawnEyeBat() {
        if(timeSpent < totalTime / 4){
            return;
        }
        int amount = (int)((timeSpent * 4 - totalTime + 30) / 30);
        for (int i = 0; i < amount; i++) {
            newMonster(2);
            shooterTimers.put(monsters.get(monsters.size() - 1), new Timer(3));
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
