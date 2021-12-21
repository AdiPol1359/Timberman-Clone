package me.adipol.timberman.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    void update();
    void render(SpriteBatch batch);
}