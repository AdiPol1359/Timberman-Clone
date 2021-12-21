package me.adipol.timberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import me.adipol.timberman.entity.EntityPosition;
import me.adipol.timberman.entity.Player;
import me.adipol.timberman.entity.Trunk;
import me.adipol.timberman.generator.TreeGenerator;
import me.adipol.timberman.manager.GameManager;
import me.adipol.timberman.util.PositionUtil;
import me.adipol.timberman.util.Settings;

public class GameScreen extends AbstractGameScreen {

    private final GameManager gameManager;
    private final Player player;
    private final TreeGenerator treeGenerator;
    private final Sound cutSound;
    private final Sound deathSound;
    private final Image ripImage;
    private final Image leftChopImage;
    private final Image rightChopImage;
    private final Image playButton;
    private final Image gameOver;
    private final BitmapFont pointsCounter;
    private final Stage gameOverStage;

    private boolean started = false;
    private boolean shownDeath = false;

    public GameScreen() {
        gameManager = game.getGameManager();
        player = gameManager.getPlayer();
        treeGenerator = gameManager.getTreeGenerator();
        cutSound = rm.getAssetManager().get("cut.ogg");
        deathSound = rm.getAssetManager().get("death.ogg");

        ripImage = new Image(rm.getRip());

        leftChopImage = new Image(rm.getLeftChop());
        rightChopImage = new Image(rm.getRightChop());
        playButton = new Image(rm.getPlayButton());
        gameOver = new Image(rm.getGameOver());

        Image stump = new Image(rm.getStump());

        stump.setWidth(136);
        stump.setHeight(29);
        stump.setX(PositionUtil.centerX(stump));
        stump.setY(135);

        leftChopImage.setWidth(105);
        leftChopImage.setHeight(41);
        leftChopImage.setX(PositionUtil.centerX(leftChopImage) - 70);
        leftChopImage.setY(40);

        rightChopImage.setWidth(105);
        rightChopImage.setHeight(41);
        rightChopImage.setX(PositionUtil.centerX(leftChopImage) + 70);
        rightChopImage.setY(40);

        playButton.setWidth(170);
        playButton.setHeight(82);
        playButton.setX(PositionUtil.centerX(playButton));
        playButton.setY(-playButton.getHeight());

        gameOver.setX(PositionUtil.centerX(gameOver));
        gameOver.setY(Settings.HEIGHT);

        stage.addActor(stump);
        stage.addActor(leftChopImage);
        stage.addActor(rightChopImage);

        gameOverStage = new Stage(new StretchViewport(Settings.WIDTH, Settings.HEIGHT));

        gameOverStage.addActor(gameOver);
        gameOverStage.addActor(playButton);

        treeGenerator.generateTrunks(10);

        pointsCounter = new BitmapFont();

        pointsCounter.setColor(Color.WHITE);
        pointsCounter.getData().setScale(2.2f);

        handlePlayButton();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameOverStage);

        stage.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.alpha(1, 0.2f)
        ));

        leftChopImage.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(-40, 0, 0.5f),
                Actions.moveBy(40, 0, 0.5f)
        )));

        rightChopImage.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(40, 0, 0.5f),
                Actions.moveBy(-40, 0, 0.5f)
        )));
    }

    @Override
    protected void update() {
        if(gameManager.isDeath()) return;

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            player.setPosition(EntityPosition.LEFT);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            player.setPosition(EntityPosition.RIGHT);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if(!started) {
                leftChopImage.addAction(Actions.alpha(0));
                rightChopImage.addAction(Actions.alpha(0));

                started = true;
            }

            player.update();
            player.setAnimation(true);
            treeGenerator.generateTrunks(1);
            cutSound.play(1);
            gameManager.addPoint();

            Trunk trunk0 = treeGenerator.getTrunks().get(0);
            Trunk trunk1 = treeGenerator.getTrunks().get(1);

            if(trunk0.getPosition() == player.getPosition() || trunk1.getPosition() == player.getPosition()) {
                gameManager.stopThemeSound();
                gameManager.setDeath(true);
                deathSound.play(0.2f);

                if(!shownDeath) {
                    playButton.addAction(Actions.moveBy(0, 150, 0.15f));
                    gameOver.addAction(Actions.moveBy(0, -300, 0.15f));

                    shownDeath = true;
                }

                return;
            }

            treeGenerator.removeTrunk();
        }
    }


    @Override
    protected void postRender() {
        batch.begin();

        for(int i = 0; i<treeGenerator.getTrunks().size(); i++) {
            treeGenerator.getTrunks().get(i).render(batch, 164 + (87 * i));
        }

        if(gameManager.isDeath()) {
            ripImage.setX(Settings.WIDTH / 2 + (player.getPosition() == EntityPosition.LEFT ? -155 : 80));
            ripImage.setY(player.getY());
            ripImage.draw(batch, 1);
        }
        else {
            player.render(batch);
        }

        if(!shownDeath) {
            pointsCounter.draw(batch, String.valueOf(gameManager.getPoints()), 620, Settings.HEIGHT - 70);
        }

        batch.end();

        gameOverStage.act();
        gameOverStage.draw();
    }

    private void handlePlayButton() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameManager.restartGame();
                game.setScreen(new GameScreen());
            }
        });
    }
}
