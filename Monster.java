package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Monster extends GameCharacter implements Poolable {
    private float attackTime;
    private Vector2 lastSeen;
    private TextureRegion monsterTextureRegion;
    private boolean active;
    private Vector2 velocity;

    public Monster(GameController gc) {
        super(gc, 20, 100.0f);
        this.monsterTextureRegion = Assets.getInstance().getAtlas().findRegion("monster");
        this.changePosition(800.0f, 300.0f);
        this.lastSeen = new Vector2(0.0f, 0.0f);
        this.active = true;
        this.velocity = new Vector2(0, 0);
    }



    public void generateMonster() {
            do {
                changePosition(MathUtils.random(40, 1240), MathUtils.random(40, 680));
            } while (!gc.getMap().isGroundPassable(position));
            hpMax = 20;
            hp = hpMax;
        }

    @Override
    public void onDeath() {
        this.position.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.hp = this.hpMax;
    }


    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void setup(TextureRegion textureRegion, float x, float y, float targetX, float targetY) {
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.position.set(x, y);
        this.velocity.set(targetX, targetY).sub(x, y).nor().scl(800.0f);
        this.active = true;
    }

    public void update(float dt) {
        super.update(dt);
        if(gc.getHero().getPosition().dst(position)<=300) {
            lastSeen.set(gc.getHero().getPosition());
            dst.set(gc.getHero().getPosition());
            if (this.position.dst(gc.getHero().getPosition()) < 40) {
                attackTime += dt;
                if (attackTime > 0.3f) {
                    attackTime = 0.0f;
                    gc.getHero().takeDamage(1);
                }
            }
        }
        else if (position.dst(dst) <= 5){
            dst.set(MathUtils.random(40, 1240), MathUtils.random(40, 680));
            }
        }

    @Override
    public boolean isActive() {
        return hp > 0;
    }
}

