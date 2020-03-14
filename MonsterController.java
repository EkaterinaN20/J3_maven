package com.geekbrains.rpg.game.logic;

import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class MonsterController extends ObjectPool<Monster> {
    private GameController gc;
    private float innerTime;
    private int initialCount;

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }

    public MonsterController(GameController gc, float initialCount) {
        this.gc = gc;
        this.innerTime = 0;
        for (int i = 0; i < initialCount; i ++) {
            getActiveElement().generateMonster();
        }
    }

    protected Monster newObject(GameController gc) {
        return new Monster(gc);
    }

        public void update(float dt) {
        innerTime += dt;
            if (innerTime%30==0) {
                getActiveElement().generateMonster();
            }
            for (int i = 0; i < getActiveList().size(); i++) {
                getActiveList().get(i).update(dt);
            }
            checkPool();
        }



/*    public void setup(float x, float y, float targetX, float targetY, GameController gc) {
        for (int i = 0; i < 5; i++) {
            Monster m = new Monster(gc);
            getActiveElement(gc).setup(monsterTextureRegion, x, y, targetX, targetY);
        }
    }*/
}
