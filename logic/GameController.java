package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.Poolable;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private ProjectilesController projectilesController;
    private Hero hero;
    private Map map;
    private MonstersController monstersController;
    private Vector2 tmp, tmp2;

    public Hero getHero() {
        return hero;
    }

    public Map getMap() {
        return map;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public MonstersController getMonstersController(){ return monstersController; }

    public GameController() {
        this.projectilesController = new ProjectilesController();
        this.hero = new Hero(this);
        this.map = new Map();
        this.monstersController = new MonstersController(this, 5);
        this.tmp = new Vector2(0, 0);
        this.tmp2 = new Vector2(0, 0);
    }

    public void update(float dt) {
        hero.update(dt);
        monstersController.update(dt);
        checkCollisions();
        projectilesController.update(dt);
    }

    public void collideUnits(GameCharacter u1, GameCharacter u2) {
        if (u1.getArea().overlaps(u2.getArea())) {
            tmp.set(u1.getArea().x, u1.getArea().y);
            tmp.sub(u2.getArea().x, u2.getArea().y);
            float halfInterLen = ((u1.getArea().radius + u2.getArea().radius) - tmp.len()) / 2.0f;
            tmp.nor();

            tmp2.set(u1.getPosition()).mulAdd(tmp, halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u1.changePosition(tmp2);
            }

            tmp2.set(u2.getPosition()).mulAdd(tmp, -halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u2.changePosition(tmp2);
            }
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < monstersController.getActiveList().size(); i++) {
            collideUnits(monstersController.getActiveList().get(i), hero);

        }
        for (int i = 0; i < monstersController.getActiveList().size()  - 1; i++) {
            for (int j = i+1; j < monstersController.getActiveList().size(); j++) {
                collideUnits(monstersController.getActiveList().get(i), monstersController.getActiveList().get(j));
            }
        }
        for (int i = 0; i < projectilesController.getActiveList().size(); i++) {
            Projectile p = projectilesController.getActiveList().get(i);
            if (!map.isAirPassable(p.getCellX(), p.getCellY())) {
                p.deactivate();
                continue;
            }
            for (int j = 0; j < monstersController.getActiveList().size(); j ++) {
                if (p.getPosition().dst(monstersController.getActiveList().get(j).getPosition()) < 24) {
                    p.deactivate();
                    if (monstersController.getActiveList().get(j).takeDamage(1)) {
                        hero.addCoins(MathUtils.random(1, 10));
                    }
                }
            }
        }
    }

    public abstract static class ObjectPool<T extends Poolable> {
        protected List<T> activeList;
        protected List<T> freeList;

        public List<T> getActiveList() {
            return activeList;
        }

        protected abstract T newObject();

        public void free(int index) {
            freeList.add(activeList.remove(index));
        }

        public ObjectPool() {
            this.activeList = new ArrayList<T>();
            this.freeList = new ArrayList<T>();
        }

        public T getActiveElement() {
            if (freeList.size() == 0) {
                freeList.add(newObject());
            }
            T temp = freeList.remove(freeList.size() - 1);
            activeList.add(temp);
            return temp;
        }

        public void checkPool() {
            for (int i = activeList.size() - 1; i >= 0; i--) {
                if (!activeList.get(i).isActive()) {
                    free(i);
                }
            }
        }
    }
}
