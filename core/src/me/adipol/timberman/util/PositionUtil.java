package me.adipol.timberman.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PositionUtil {

    public static float centerX(Actor actor) {
        return ((Settings.WIDTH - actor.getWidth()) / 2);
    }

    public static float centerY(Actor actor) {
        return ((Settings.HEIGHT - actor.getHeight()) / 2);
    }
}