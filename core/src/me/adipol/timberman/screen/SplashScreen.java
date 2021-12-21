package me.adipol.timberman.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.adipol.timberman.util.PositionUtil;

public class SplashScreen extends AbstractScreen {

    public SplashScreen() {
        Image logo = new Image(rm.getLogo());
        Image splashText = new Image(rm.getSplashText());

        logo.setWidth(220);
        logo.setHeight(220);
        logo.setX(PositionUtil.centerX(logo));
        logo.setY(PositionUtil.centerY(logo) + 50);

        splashText.setWidth(510);
        splashText.setHeight(36);
        splashText.setX(PositionUtil.centerX(splashText));
        splashText.setY(PositionUtil.centerY(splashText) - 105);

        stage.addActor(logo);
        stage.addActor(splashText);

        clearColor = Color.WHITE;
    }

    @Override
    public void show() {
        super.show();

        stage.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.delay(0.2f),
                Actions.alpha(1, 1.3f),
                Actions.delay(1),
                Actions.alpha(0, 1.3f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(game.getMenuScreen());
                    }
                })
        ));
    }
}
