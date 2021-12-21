package me.adipol.timberman.generator;

import lombok.Getter;
import me.adipol.timberman.entity.EntityPosition;
import me.adipol.timberman.entity.Trunk;
import me.adipol.timberman.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TreeGenerator {

    private final List<Trunk> trunks = new ArrayList<>();

    private EntityPosition lastPosition = EntityPosition.CENTER;

    public void generateTrunks(int count) {
        for(int i = 0; i<count; i++) {
            EntityPosition position = EntityPosition.values()[trunks.size() == 0 ? 1 : MathUtil.getRandomInRange(0, 2)];

            while(checkPosition(position) && trunks.size() > 0) {
                position = EntityPosition.values()[MathUtil.getRandomInRange(0, 2)];
            }

            Trunk trunk = new Trunk(position);
            lastPosition = position;

            trunks.add(trunk);
        }
    }

    public void removeTrunk() {
        trunks.remove(0);
    }

    public void clear() {
        trunks.clear();
    }

    private boolean checkPosition(EntityPosition position) {
        return (position == EntityPosition.LEFT && lastPosition == EntityPosition.RIGHT) || (position == EntityPosition.RIGHT && lastPosition == EntityPosition.LEFT);
    }
}