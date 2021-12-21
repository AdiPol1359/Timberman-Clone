package me.adipol.timberman.manager;

import com.badlogic.gdx.audio.Sound;
import lombok.Getter;
import lombok.Setter;
import me.adipol.timberman.Timberman;
import me.adipol.timberman.entity.EntityPosition;
import me.adipol.timberman.entity.Player;
import me.adipol.timberman.generator.TreeGenerator;

@Getter
public class GameManager {

    private final Sound themeSound;
    private final Player player;
    private final TreeGenerator treeGenerator;

    private boolean playSound = false;
    @Setter private boolean death = false;
    @Setter private int points = 0;

    public GameManager() {
        ResourceManager rm = Timberman.getInstance().getResourceManager();

        themeSound = rm.getAssetManager().get("theme.ogg");
        player = new Player();
        treeGenerator = new TreeGenerator();
    }

    public void playThemeSound() {
        if(!playSound) {
            themeSound.loop(0.2f);
            playSound = true;
        }
    }

    public void stopThemeSound() {
        themeSound.stop();
        playSound = false;
    }

    public void restartGame() {
        points = 0;
        death = false;

        player.setPosition(EntityPosition.LEFT);
        player.setAnimation(false);
        player.update();

        treeGenerator.clear();
        //aatreeGenerator.generateTrunks(10);

        playThemeSound();
    }

    public void addPoint() {
        points++;
    }
}
