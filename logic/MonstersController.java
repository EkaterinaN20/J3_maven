package com.geekbrains.rpg.game.logic;

public class MonstersController extends GameController.ObjectPool<Monster> {
    private GameController gc;
    private float time;
    private int monstersNumber;
    private float spawnPeriod;

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }

    public MonstersController(GameController gc, int monstersNumber) {

        this.gc = gc;
        this.monstersNumber = 5;
        this.spawnPeriod = 30.0f;
    for (int i = 0; i < monstersNumber; i++) {
        getActiveElement().generateMonster();
    }
    }

    public void update(float dt) {
        time += dt;
            if (time > spawnPeriod) {
                time = 0.0f;
            getActiveElement().generateMonster();
        }
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }
}
