package me.adipol.timberman.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import lombok.Getter;

@Getter
public class ResourceManager implements Disposable {

    private final AssetManager assetManager;

    private final TextureAtlas textureAtlas;

    private final TextureRegion logo;
    private final TextureRegion splashText;
    private final TextureRegion playButton;
    private final TextureRegion timbermanLogo;
    private final TextureRegion man;
    private final TextureRegion animatedMan1;
    private final TextureRegion animatedMan2;
    private final TextureRegion stump;
    private final TextureRegion trunk;
    private final TextureRegion leftBranch;
    private final TextureRegion rightBranch;
    private final TextureRegion rip;
    private final TextureRegion leftChop;
    private final TextureRegion rightChop;
    private final TextureRegion gameOver;

    public ResourceManager() {
        assetManager = new AssetManager();

        assetManager.load("textures.atlas", TextureAtlas.class);
        assetManager.load("background.png", Texture.class);
        assetManager.load("theme.ogg", Sound.class);
        assetManager.load("menu.ogg", Sound.class);
        assetManager.load("cut.ogg", Sound.class);
        assetManager.load("death.ogg", Sound.class);

        assetManager.finishLoading();

        textureAtlas = assetManager.get("textures.atlas", TextureAtlas.class);

        logo = textureAtlas.findRegion("logo");
        splashText = textureAtlas.findRegion("splash-text");
        playButton = textureAtlas.findRegion("play-button");
        timbermanLogo = textureAtlas.findRegion("timberman-logo");
        man = textureAtlas.findRegion("man");
        animatedMan1 = textureAtlas.findRegion("animated-man-1");
        animatedMan2 = textureAtlas.findRegion("animated-man-2");
        stump = textureAtlas.findRegion("stump");
        trunk = textureAtlas.findRegion("trunk");
        leftBranch = textureAtlas.findRegion("left-branch");
        rightBranch = textureAtlas.findRegion("right-branch");
        rip = textureAtlas.findRegion("rip");
        leftChop = textureAtlas.findRegion("chop-left");
        rightChop = textureAtlas.findRegion("chop-right");
        gameOver = textureAtlas.findRegion("game-over");
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        textureAtlas.dispose();
    }
}
