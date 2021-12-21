package me.adipol.timberman.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import me.adipol.timberman.util.PositionUtil;
import me.adipol.timberman.util.Settings;

public class MenuScreen extends AbstractGameScreen {

    private final Image playButton;
    private final Image timbermanLogo;
    private final Sound clickSound;

    public MenuScreen() {
        playButton = new Image(rm.getPlayButton());
        timbermanLogo = new Image(rm.getTimbermanLogo());
        clickSound = rm.getAssetManager().get("menu.ogg");

        timbermanLogo.setX(PositionUtil.centerX(timbermanLogo));
        timbermanLogo.setY(Settings.HEIGHT);

        playButton.setWidth(170);
        playButton.setHeight(82);
        playButton.setX(PositionUtil.centerX(playButton));
        playButton.setY(-playButton.getHeight());

        stage.addActor(playButton);
        stage.addActor(timbermanLogo);

        handlePlayButton();
    }

    @Override
    public void show() {
        super.show();

        timbermanLogo.addAction(Actions.moveBy(0, -280, 0.2f));
        playButton.addAction(Actions.moveBy(0, 150, 0.2f));
    }

    private void handlePlayButton() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                fadeScreen(new GameScreen());
            }
        });
    }
}
