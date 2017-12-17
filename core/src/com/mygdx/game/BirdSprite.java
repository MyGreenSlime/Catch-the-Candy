package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by GreenSlime on 15/11/2560.
 */
public class BirdSprite {
    private boolean check = FALSE;
    private static final int garvity = 3;
    private float time = 0;
    private int flip = 0;
    private int speedX = 3;
    private int speedY = 0;
    private int status = 0;
    private Vector2 position;

    public BirdSprite(int x, int y) {
        position = new Vector2(x, y);

    }

    public Vector2 getPosition() {
        return position;
    }

    public int getStatus() {
        return status;
    }

    public int getFlip() {
        return flip;
    }

    private void checkWall() {
        if (360 - position.x < 0) {
            speedX *= -1;
            flip = 1;
            status = 1;
        } else if (position.x < 0) {
            speedX *= -1;
            flip = 0;
            status = 0;
        }
    }

    public void move(float delta) {
        time += delta;
        position.x += speedX;
        position.y += speedY;
        checkWall();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && check == FALSE) {
            if (flip == 0) {
                status = 2;
            } else {
                status = 3;
            }

            speedY = 4;
            position.y += 20;
            check = TRUE;
            time = 0;
        }
        if (time > 0.2) {
            speedY -= garvity;
            if (check) {
                if (flip == 0) {
                    status = 0;
                } else {
                    status = 1;
                }

                check = FALSE;
            }
            time = 0;
        }
    }

}
