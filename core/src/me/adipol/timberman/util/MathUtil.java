package me.adipol.timberman.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class MathUtil {

    public static int getRandomInRange(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
