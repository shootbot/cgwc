package com.codingame.game;

import java.util.ArrayList;
import java.util.List;

import com.codingame.game.grid.Coord;
import com.codingame.game.grid.Direction;

public class Organ {
    static int ENTITY_COUNT = 0;

    int id;
    OrganType type;
    Player owner;
    Organ parent;
    List<Organ> children;
    Direction direction;
    Coord pos;
    int rootId;
    int parentId;

    public Organ(Player owner, OrganType type, Direction direction) {
        this(ENTITY_COUNT+1, type, owner, null, direction, 0, ENTITY_COUNT+1);
        ENTITY_COUNT++;
//        this.owner = owner;
//        this.type = type;
//        this.direction = direction;
//        this.parent = null;
//        this.id = ++ENTITY_COUNT;
//        this.children = new ArrayList<>();
//        this.rootId = this.id;
    }

    public Organ(int id, OrganType type, Player owner, Organ parent, Direction direction, int parentId, int rootId) {
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.parent = parent;
        this.direction = direction;
        this.parentId = parentId;
        this.rootId = rootId;
        this.children = new ArrayList<>();
    }

    public Player getOwner() {
        return owner;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public boolean isHarvester() {
        return getType() == OrganType.HARVESTER;
    }

    public Coord getFacedCoord() {
        return pos.add(direction.coord);
    }

    public boolean isTentacle() {
        return getType() == OrganType.TENTACLE;
    }

    public void setParent(Organ parent) {
        this.parent = parent;
        if (parent.getType() == OrganType.ROOT) {
            this.rootId = parent.id;
        } else {
            this.rootId = parent.getRootId();
        }
    }

    public boolean isNucleus() {
        return getType() == OrganType.ROOT;
    }

    public OrganType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Coord getPos() {
        return pos;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getParentId() {
        if (parent == null) {
            return 0;
        }
        return parent.id;

    }

    public boolean isSporer() {
        return type == OrganType.SPORER;
    }

    public boolean isRoot() {
        return type == OrganType.ROOT;
    }

    public int getRootId() {
        return rootId;
    }

    @Override
    public String toString() {
        return "Organ{" +
                "type=" + type +
                ", owner=" + owner +
                ", id=" + id +
                '}';
    }
}