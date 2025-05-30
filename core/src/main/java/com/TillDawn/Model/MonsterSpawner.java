package com.TillDawn.Model;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MonsterSpawner {

    private final ArrayList<Monster> monsters;
    private final HashMap<Monster, Timer> shooterTimers;
    private final ArrayList<Projectile> monsterShots;

    private static final float tentacleSpawnInterval = 3.0f;
    private static final float eyeBatSpawnInterval = 10.0f;

    private final Timer tentacleTimer;
    private final Timer eyeBatTimer;
    private float timeSpent = 0f;
    private final float totalTime;
    private boolean bossSpawned = false;

    public MonsterSpawner(ArrayList<Monster> monsters, ArrayList<Projectile> monsterShots, float totalTime) {
        this.monsters = monsters;
        this.monsterShots = monsterShots;
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

        updateEyeBatShots(deltaTime);

        if (timeSpent * 2 >= totalTime && !bossSpawned) {
            spawnBoss();
        }
    }

    private void spawnEyeBat() {
        if (timeSpent < totalTime / 4) {
            return;
        }
        int amount = (int) ((timeSpent * 4 - totalTime + 30) / 30);
        for (int i = 0; i < amount; i++) {
            Monster monster = newMonster(2);
            monsters.add(monster);
            shooterTimers.put(monster, new Timer(6));
        }
    }

    private void spawnTentacle() {
        int amount = (int) (timeSpent / 30);
        for (int i = 0; i < amount; i++) {
            Monster monster = newMonster(1);
            monsters.add(monster);
        }
    }

    public void spawnBoss() {
        bossSpawned = true;
        Monster boss = newMonster(3);
        monsters.add(boss);
    }

    public Monster newMonster(int type) {
        Random rand = new Random();
        float width = App.getCurrentGame().getMapWidth() - 100;
        float height = App.getCurrentGame().getMapHeight() - 100;
        float x, y;
        if (rand.nextBoolean()) {
            x = width * rand.nextFloat();
            y = height * rand.nextInt(2);
        } else {
            x = width * rand.nextInt(2);
            y = height * rand.nextFloat();
        }
        return new Monster(type, x, y);
    }

    public void updateEyeBatShots(float deltaTime) {
        Iterator<Monster> iterator = shooterTimers.keySet().iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (monster.isDying()) {
                iterator.remove();
                continue;
            }
            shooterTimers.get(monster).update(deltaTime);
            if (shooterTimers.get(monster).isFinished()) {
                shoot(monster);
                shooterTimers.get(monster).reset();
            }
        }
    }

    public void shoot(Monster monster) {
        float xStart = monster.getPosX();
        float yStart = monster.getPosY();
        Vector2 direction = new Vector2(
            App.getCurrentGame().getPlayer().getPosX() - xStart,
            App.getCurrentGame().getPlayer().getPosY() - yStart).nor();

        Projectile shot = new Projectile(xStart, yStart, direction, GameAssetManager.getGameAssetManager().getMonsterShotTex());
        monsterShots.add(shot);
    }
}
