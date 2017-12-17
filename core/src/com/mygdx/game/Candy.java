package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by GreenSlime on 18/11/2560.
 */
public class Candy {
    private int x;
    private int y;
    private List<Integer> posx;
    private List<Integer> posy;
    private Random rand;
    private Vector2 position;

    public Candy() {
        rand = new Random();
        posx = new ArrayList<Integer>();
        posx.add(50);
        posx.add(320);
        posy = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            posy.add(120 + 80 * i);
        }
        x = posx.get(rand.nextInt(posx.size()));
        y = posy.get(rand.nextInt(posy.size()));
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setRand() {
        x = posx.get(rand.nextInt(posx.size()));
        y = posy.get(rand.nextInt(posy.size()));
        position = new Vector2(x, y);
    }
}
