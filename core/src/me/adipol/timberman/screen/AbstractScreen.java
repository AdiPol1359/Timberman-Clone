package me.adipol.timberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import me.adipol.timberman.Timberman;
import me.adipol.timberman.manager.ResourceManager;
import me.adipol.timberman.util.Settings;

public abstract class AbstractScreen implements Screen {

    protected final SpriteBatch batch;
    protected final Stage stage;
    protected final Timberman game;
    protected final ResourceManager rm;

    protected Color clearColor = Color.BLACK;

    public AbstractScreen() {
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(Settings.WIDTH, Settings.HEIGHT));
        game = Timberman.getInstance();
        rm = game.getResourceManager();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(clearColor);

        preRender();

        stage.act(delta);
        stage.draw();

        postRender();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void fadeScreen(final AbstractScreen screen) {
        stage.addAction(Actions.sequence(
                Actions.alpha(0, 0.2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(screen);
                    }
                })
        ));
    }

    protected void update() {}
    protected void preRender() {}
    protected void postRender() {}
}