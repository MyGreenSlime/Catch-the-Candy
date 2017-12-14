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
    private BirdSprite birdSprite;
    private Candy candy;
    private MyGame myGame;
    private WorldRenderer worldRenderer;

    private WallSpriteLeft tmpWallLeft;
    private WallSpriteRight tmpWallRight;
    private List<WallSpriteLeft> arrWallLeft;
    private List<WallSpriteRight> arrWallRight;
    public Rectangle birdRec;
    public Rectangle spikeRec;
    public Rectangle candyRec;
    private Random rand;
    private int tempFlip;
    private List<Integer> temp;
    private List<Integer> tempReal;

    public int score;

    public int gameStatus;
    public World(MyGame myGame){
        gameStatus = -1;
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);
        candy = new Candy();
        score = 0;

        arrWallLeft = new ArrayList<WallSpriteLeft>();
        arrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        temp = new ArrayList<Integer>();
        tempReal = new ArrayList<Integer>();


        for(int i = 0;i<8;i++){
            tmpWallLeft = new WallSpriteLeft(-65,40 +80*i);
            tmpWallRight = new WallSpriteRight(405,40+80*i);
            arrWallRight.add(tmpWallRight);
            arrWallLeft.add(tmpWallLeft);
            temp.add(i);
        }
        tempFlip = birdSprite.getFlip();
        worldRenderer = new WorldRenderer(myGame,this);
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x,(int)getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth(), worldRenderer.getSpriteImg().getHeight());
        spikeRec = new Rectangle((int)arrWallLeft.get(0).getPosition().x,(int)arrWallLeft.get(0).getPosition().y,worldRenderer.getSpikeleftImg().getWidth(),worldRenderer.getSpikeleftImg().getHeight());
        candyRec = new Rectangle((int)candy.getPosition().x,(int)candy.getPosition().y,worldRenderer.getCandyImg().getWidth(),worldRenderer.getCandyImg().getHeight());

    }
    private void setupWorld(MyGame myGame){
        gameStatus = 1;
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);

        candy = new Candy();
        score = 0;

        arrWallLeft = new ArrayList<WallSpriteLeft>();
        arrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        temp = new ArrayList<Integer>();
        tempReal = new ArrayList<Integer>();

        for(int i = 0;i<8;i++){
            tmpWallLeft = new WallSpriteLeft(-60,40 +80*i);
            tmpWallRight = new WallSpriteRight(400,40+80*i);
            arrWallRight.add(tmpWallRight);
            arrWallLeft.add(tmpWallLeft);
            temp.add(i);
        }
        tempFlip = birdSprite.getFlip();
        worldRenderer = new WorldRenderer(myGame,this);
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x,(int)getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth(), worldRenderer.getSpriteImg().getHeight());
        spikeRec = new Rectangle((int)arrWallLeft.get(0).getPosition().x,(int)arrWallLeft.get(0).getPosition().y,worldRenderer.getSpikeleftImg().getWidth(),worldRenderer.getSpikeleftImg().getHeight());
        candyRec = new Rectangle((int)candy.getPosition().x,(int)candy.getPosition().y,worldRenderer.getCandyImg().getWidth(),worldRenderer.getCandyImg().getHeight());
    }
    public BirdSprite getBirdSprite()
    {
        return birdSprite;
    }
    public List<WallSpriteLeft> getArrWallLeft(){
        return arrWallLeft;
    }
    public List<WallSpriteRight> getArrWallRight(){
        return arrWallRight;
    }
    public Candy getCandy(){
        return candy;
    }
    private void wallMove(){
        if(tempFlip != birdSprite.getFlip()){
            temp.clear();
            tempReal.clear();
            for (int i = 0;i<8;i++){
                temp.add(i);
            }
            int numSpike = 1+score/5;
            if(numSpike>6){
                numSpike = 6;
            }
            for(int i = 0;i<numSpike;i++){
                int tmp = rand.nextInt(temp.size());
                tempReal.add(temp.get(tmp));
                temp.remove(tmp);
                //System.out.println("time"+i+"  "+temp);
            }
        }
        else{
            for(int i =0;i<tempReal.size();i++){
                if(birdSprite.getFlip()%2==1)
                    arrWallLeft.get(tempReal.get(i)).inScreen();
                else if(birdSprite.getFlip()%2==0){
                    arrWallRight.get(tempReal.get(i)).inScreen();
                }
                //System.out.println("wall"+tempReal.get(i));
            }
        }
        for(int i = 0;i<arrWallLeft.size();i++){
            if(birdSprite.getFlip()%2==0){
                arrWallLeft.get(i).outScreen();
            }
            else if(birdSprite.getFlip()%2==1){
                arrWallRight.get(i).outScreen();
            }
        }
        tempFlip = birdSprite.getFlip();
    }
    private void checkHit(){
        birdRec = new Rectangle((int) getBirdSprite().getPosition().x,(int)getBirdSprite().getPosition().y, worldRenderer.getSpriteImg().getWidth(), worldRenderer.getSpriteImg().getHeight());
        //birdRec.setCenter(birdRec.x+(birdRec.width/2),birdRec.y+(birdRec.height/2));
        if(score%2==1) {
            birdRec.setX(birdRec.x+birdRec.width/5);
            for (int i = 0; i < arrWallLeft.size(); i++) {
                /*if (abs(arrWallLeft.get(i).getPosition().x+60-(birdSprite.getPosition().x+20)) <= 17 && abs(arrWallLeft.get(i).getPosition().y +30 -birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit left" + i);
                }*/
                spikeRec = new Rectangle((int)arrWallLeft.get(i).getPosition().x,(int)arrWallLeft.get(i).getPosition().y,worldRenderer.getSpikeleftImg().getWidth(),worldRenderer.getSpikeleftImg().getHeight());
                if(birdRec.overlaps(spikeRec)){
                    gameStatus =0;
                }

            }
        }
        else if(score%2==0) {
            birdRec.setX(birdRec.x-birdRec.width/5);
            for (int i = 0; i < arrWallRight.size(); i++) {
                /*if (abs(arrWallRight.get(i).getPosition().x-birdSprite.getPosition().x-20) <= 17 && abs(arrWallRight.get(i).getPosition().y +30-birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit right" + i);
                }*/
                spikeRec = new Rectangle((int)arrWallRight.get(i).getPosition().x,(int)arrWallRight.get(i).getPosition().y,worldRenderer.getSpikerightImg().getWidth(),worldRenderer.getSpikerightImg().getHeight());
                if(birdRec.overlaps(spikeRec)){
                    gameStatus =0;
                }

            }
        }
        if(birdSprite.getPosition().y-40<0 || birdSprite.getPosition().y>630){
            gameStatus =0;
        }
        if(abs(candy.getPosition().x-birdSprite.getPosition().x)<=20 && abs(candy.getPosition().y-birdSprite.getPosition().y)<=20){
            candy.setRand();
            score +=1;
        }
    }
    public void update(float delta){
        if(gameStatus == 1) {
            birdSprite.move(delta);
            wallMove();
            checkHit();
        }
        else if(gameStatus == -1){

            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStatus = 1;
            }

        }
        else if(gameStatus == 0&&birdSprite.getPosition().y<0){

            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                setupWorld(myGame);
            }
        }
        else if(gameStatus == 0){
            birdSprite.position.y -=10;
        }

    }
}
