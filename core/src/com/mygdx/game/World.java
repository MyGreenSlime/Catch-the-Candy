package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Math.abs;


/**
 * Created by GreenSlime on 15/11/2560.
 */
public class World {
    private MyGame myGame;
    private WorldRenderer worldRenderer;
    private BirdSprite birdSprite;
    private Candy candy;

    private WallSpriteLeft tmpWallLeft;
    private WallSpriteRight tmpWallRight;
    private Rectangle birdRec;
    private Rectangle spikeRec;
    private Random rand;

    private List<Integer> showWallAt;
    private List<WallSpriteLeft> arrWallLeft;
    private List<WallSpriteRight> arrWallRight;

    private int tempFlip;
    public int score;
    public int gameStatus;

    public World(MyGame myGame) {
        this.myGame = myGame;
        birdSprite = new BirdSprite(200, 350);
        candy = new Candy();

        score = 0;
        gameStatus = -1;

        arrWallLeft = new ArrayList<WallSpriteLeft>();
        arrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        showWallAt = new ArrayList<Integer>();


        for (int i = 0; i < 8; i++) {
            tmpWallLeft = new WallSpriteLeft(-30, 40 + 80 * i);
            tmpWallRight = new WallSpriteRight(400, 40 + 80 * i);
            arrWallRight.add(tmpWallRight);
            arrWallLeft.add(tmpWallLeft);
        }
        tempFlip = birdSprite.getFlip();
        worldRenderer = new WorldRenderer(myGame, this);
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x, (int) getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth(), worldRenderer.getSpriteImg().getHeight());
        spikeRec = new Rectangle((int) arrWallLeft.get(0).getPosition().x, (int) arrWallLeft.get(0).getPosition().y, worldRenderer.getSpikeleftImg().getWidth(), worldRenderer.getSpikeleftImg().getHeight());

    }

    private void setupWorld(MyGame myGame) {
        this.myGame = myGame;
        birdSprite = new BirdSprite(200, 350);
        candy = new Candy();

        score = 0;
        gameStatus = 1;

        arrWallLeft = new ArrayList<WallSpriteLeft>();
        arrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        showWallAt = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            tmpWallLeft = new WallSpriteLeft(-60, 40 + 80 * i);
            tmpWallRight = new WallSpriteRight(400, 40 + 80 * i);
            arrWallRight.add(tmpWallRight);
            arrWallLeft.add(tmpWallLeft);
        }
        tempFlip = birdSprite.getFlip();
        worldRenderer = new WorldRenderer(myGame, this);
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x, (int) getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth(), worldRenderer.getSpriteImg().getHeight());
        spikeRec = new Rectangle((int) arrWallLeft.get(0).getPosition().x, (int) arrWallLeft.get(0).getPosition().y, worldRenderer.getSpikeleftImg().getWidth(), worldRenderer.getSpikeleftImg().getHeight());
    }

    public BirdSprite getBirdSprite() {
        return birdSprite;
    }

    public List<WallSpriteLeft> getArrWallLeft() {
        return arrWallLeft;
    }

    public List<WallSpriteRight> getArrWallRight() {
        return arrWallRight;
    }

    public Candy getCandy() {
        return candy;
    }

    private void wallMove() {
        if (tempFlip != birdSprite.getFlip()) {
            showWallAt.clear();
            int numSpike = 1 + score / 5;
            if (numSpike > 6) {
                numSpike = 6;
            }
            for (int i = 0; i < numSpike; i++) {
                int tmp = rand.nextInt(8);
                showWallAt.add(tmp);
            }
        } else {
            for (int i = 0; i < showWallAt.size(); i++) {
                if (birdSprite.getFlip() % 2 == 1)
                    arrWallLeft.get(showWallAt.get(i)).inScreen();
                else if (birdSprite.getFlip() % 2 == 0) {
                    arrWallRight.get(showWallAt.get(i)).inScreen();
                }
            }
        }
        for (int i = 0; i < arrWallLeft.size(); i++) {
            if (birdSprite.getFlip() % 2 == 0) {
                arrWallLeft.get(i).outScreen();
            } else if (birdSprite.getFlip() % 2 == 1) {
                arrWallRight.get(i).outScreen();
            }
        }
        tempFlip = birdSprite.getFlip();
    }

    private void checkHit() {
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x, (int) getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth() + 5, worldRenderer.getSpriteImg().getHeight() + 5);
        //birdRec.setCenter(birdRec.x+(birdRec.width/2),birdRec.y+(birdRec.height/2));
        if (score % 2 == 1) {
            birdRec.setX(birdRec.x + 4);
            for (int i = 0; i < arrWallLeft.size(); i++) {
                /*if (abs(arrWallLeft.get(i).getPosition().x+60-(birdSprite.getPosition().x+20)) <= 17 && abs(arrWallLeft.get(i).getPosition().y +30 -birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit left" + i);
                }*/
                spikeRec = new Rectangle((int) arrWallLeft.get(i).getPosition().x, (int) arrWallLeft.get(i).getPosition().y, worldRenderer.getSpikeleftImg().getWidth(), worldRenderer.getSpikeleftImg().getHeight() + 5);
                if (birdRec.overlaps(spikeRec)) {
                    gameStatus = 0;
                }

            }
        } else if (score % 2 == 0) {
            birdRec.setX(birdRec.x - birdRec.getWidth() / 5);
            for (int i = 0; i < arrWallRight.size(); i++) {
                /*if (abs(arrWallRight.get(i).getPosition().x-birdSprite.getPosition().x-20) <= 17 && abs(arrWallRight.get(i).getPosition().y +30-birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit right" + i);
                }*/
                spikeRec = new Rectangle((int) arrWallRight.get(i).getPosition().x, (int) arrWallRight.get(i).getPosition().y, worldRenderer.getSpikerightImg().getWidth() + 5, worldRenderer.getSpikerightImg().getHeight() + 5);
                if (birdRec.overlaps(spikeRec)) {
                    gameStatus = 0;
                }

            }
        }
        if (birdSprite.getPosition().y - 40 < 0 || birdSprite.getPosition().y > 630) {
            gameStatus = 0;
        }
        if (abs(candy.getPosition().x - birdSprite.getPosition().x) <= 20 && abs(candy.getPosition().y - birdSprite.getPosition().y) <= 20) {
            candy.setRand();
            score += 1;
        }
    }

    public void update(float delta) {
        if (gameStatus == 1) {
            birdSprite.move(delta);
            wallMove();
            checkHit();
        } else if (gameStatus == -1) {

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStatus = 1;
            }

        } else if (gameStatus == 0 && birdSprite.getPosition().y < 0) {

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                setupWorld(myGame);
            }
        } else if (gameStatus == 0) {
            birdSprite.getPosition().y -= 10;
        }

    }
}
