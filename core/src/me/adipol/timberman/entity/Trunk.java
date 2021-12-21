package me.adipol.timberman.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lombok.Getter;
import me.adipol.timberman.Timberman;
import me.adipol.timberman.manager.ResourceManager;
import me.adipol.timberman.util.PositionUtil;

@Getter
public class Trunk implements Entity {

    private final EntityPosition position;
    private final ResourceManager resourceManager;
    private final TextureRegion textureRegion;
    private final Image image;

    public Trunk(EntityPosition position) {
        this.position = position;

        resourceManager = Timberman.getInstance().getResourceManager();
        textureRegion = getTexture();

        image = new Image(textureRegion);
        image.setHeight(87);
    }

    @Override
    public void update() {}

    @Override
    public void render(SpriteBatch batch) {}

    public void render(SpriteBatch batch, int y) {
        batch.draw(textureRegion, getTextureX(), y, getTextureWidth() ,87);
    }

    private TextureRegion getTexture() {
        switch(position) {
            case LEFT:
                return resourceManager.getLeftBranch();
            case RIGHT:
                return resourceManager.getRightBranch();
            case CENTER:
                return resourceManager.getTrunk();
        }

        return null;
    }

    private float getTextureX() {
        image.setWidth(getTextureWidth());

        switch(position) {
            case CENTER:
                return PositionUtil.centerX(image);
            case LEFT:
                return PositionUtil.centerX(image) - 60;
            case RIGHT:
                return PositionUtil.centerX(image) + 60;
        }

        return -1;
    }

    private float getTextureWidth() {
        return position == EntityPosition.CENTER ? 120 : 240;
    }
}