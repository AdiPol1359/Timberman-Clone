package me.adipol.timberman.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lombok.Getter;
import lombok.Setter;
import me.adipol.timberman.Timberman;
import me.adipol.timberman.manager.ResourceManager;
import me.adipol.timberman.util.PositionUtil;

import javax.swing.text.Position;

@Getter
@Setter
public class Player implements Entity {

    private Animation playerAnimation;
    private final ResourceManager resourceManager;
    private final float y = 135;

    private EntityPosition position = EntityPosition.LEFT;
    private float animationCounter = 0;
    private boolean animation = false;

    private final float ANIMATION_SPEED = 0.05f;

    public Player() {
        resourceManager = Timberman.getInstance().getResourceManager();

        TextureRegion[] animationTextures = new TextureRegion[3];

        animationTextures[0] = resourceManager.getMan();
        animationTextures[1] = resourceManager.getAnimatedMan2();
        animationTextures[2] = resourceManager.getAnimatedMan1();

        playerAnimation = new Animation(ANIMATION_SPEED, animationTextures);
    }

    @Override
    public void update() {}

    @Override
    public void render(SpriteBatch batch) {
        if(animation) {
            animationCounter += Gdx.graphics.getDeltaTime();
        }
        else {
            animationCounter = 0;
        }

        TextureRegion textureRegion = getTextureRegion();

        if(position == EntityPosition.LEFT && textureRegion.isFlipX() || position == EntityPosition.RIGHT && !textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        }

        if(animation && animationCounter >= ANIMATION_SPEED + ANIMATION_SPEED + ANIMATION_SPEED) {
            animation = false;
            animationCounter = 0;
        }

        batch.draw(getTextureRegion(), getX(), y, getTextureWidth(), getTextureHeight());
    }

    public float getX() {
        Image image = new Image(getTextureRegion());
        image.setWidth(getTextureWidth());
        image.setHeight(getTextureHeight());

        float pos = PositionUtil.centerX(image);

        if(animation && animationCounter >= ANIMATION_SPEED && animationCounter <= ANIMATION_SPEED + ANIMATION_SPEED) {
            pos = position == EntityPosition.LEFT ? pos - 8 : pos + 8;
        }

        if(animation && animationCounter >= ANIMATION_SPEED + ANIMATION_SPEED) {
            pos = position == EntityPosition.LEFT ? pos + 40 : pos - 40;
        }

        return position == EntityPosition.LEFT ? pos - 120 : pos + 120;
    }

    private TextureRegion getTextureRegion() {
        return (TextureRegion) playerAnimation.getKeyFrame(animationCounter, true);
    }

    private float getTextureHeight() {
        if(animationCounter <= ANIMATION_SPEED) {
            return 151;
        }

        if(animation && animationCounter >= ANIMATION_SPEED && animationCounter <= ANIMATION_SPEED + ANIMATION_SPEED) {
            return 125;
        }


        if(animation && animationCounter >= ANIMATION_SPEED + ANIMATION_SPEED) {
            return 124;
        }

        return -1;
    }

    private float getTextureWidth() {
        if(animationCounter <= ANIMATION_SPEED) {
            return 110;
        }

        if(animation && animationCounter >= ANIMATION_SPEED && animationCounter <= ANIMATION_SPEED + ANIMATION_SPEED) {
            return 123;
        }


        if(animation && animationCounter >= ANIMATION_SPEED + ANIMATION_SPEED) {
            return 167;
        }

        return -1;
    }
}