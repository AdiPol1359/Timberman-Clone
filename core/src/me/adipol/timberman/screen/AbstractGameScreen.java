package me.adipol.timberman.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class AbstractGameScreen extends AbstractScreen {

    public AbstractGameScreen() {
        Image background = new Image(rm.getAssetManager().get("background.png", Texture.class));

        stage.addActor(background);
    }

    @Override
    public void show() {
        super.show();

        game.getGameManager().playThemeSound();
    }
}