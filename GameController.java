package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameController {
    private ProjectilesController projectilesController;
    private MonsterController monsterController;
    private Map map;
    private Hero hero;
    private Vector2 tmp, tmp2;

    public Hero getHero() {
        return hero;
    }


    public Map getMap() {
        return map;
    }

    //   public Monster getMonster() {
    //   return monster;
    // }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public GameController() {
        this.projectilesController = new ProjectilesController();
        this.hero = new Hero(this);
        this.map = new Map();
        this.monsterController = new MonsterController(this, 5);
        this.tmp = new Vector2(0, 0);
        this.tmp2 = new Vector2(0, 0);
    }

    public void update(float dt) {
        hero.update(dt);

        checkCollisions();
        collideUnits(hero, monsterController);
        projectilesController.update(dt);
        monsterController.update(dt);
    }

    public void collideUnits(GameCharacter u1, MonsterController mc) {
        for (int i = 0; i < mc.getActiveList().size(); i++) {
            Monster u2 = monsterController.getActiveList().get(i);
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
    }

    public void checkCollisions() {
        for (int i = 0; i < projectilesController.getActiveList().size(); i++) {
            Projectile p = projectilesController.getActiveList().get(i);
            if (!map.isAirPassable(p.getCellX(), p.getCellY())) {
                p.deactivate();
                continue;
            }
            for (int j = 0; j < projectilesController.getActiveList().size(); j++) {
                Monster m = monsterController.getActiveList().get(i);
                if (m.getPosition().dst(m.getPosition()) < 24) {
                    p.deactivate();
                    if (m.takeDamage(1)) {
                        hero.addCoins(MathUtils.random(1, 10));
                    }
                }
            }
        }
    }
}
