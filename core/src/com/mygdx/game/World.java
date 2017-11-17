package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
    private MyGame myGame;

    private WallSpriteLeft tmpwallleft;
    private WallSpriteRight tmpwallright;
    private List<WallSpriteLeft> ArrWallLeft;
    private List<WallSpriteRight> ArrWallRight;

    private Random rand;
    private int tempscore;
    private List<Integer> temp;
    private List<Integer> tempReal;

    public int gameStatus;
    World(MyGame myGame){
        gameStatus = 1;
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);

        ArrWallLeft = new ArrayList<WallSpriteLeft>();
        ArrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        temp = new ArrayList<Integer>();
        tempReal = new ArrayList<Integer>();

        for(int i = 0;i<8;i++){
            tmpwallleft = new WallSpriteLeft(-60,40 +80*i);
            tmpwallright = new WallSpriteRight(400,40+80*i);
            ArrWallRight.add(tmpwallright);
            ArrWallLeft.add(tmpwallleft);
            temp.add(i);
        }
        tempscore = birdSprite.getScore();


    }
    private void setupWorld(MyGame myGame){
        gameStatus = 1;
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);

        ArrWallLeft = new ArrayList<WallSpriteLeft>();
        ArrWallRight = new ArrayList<WallSpriteRight>();

        rand = new Random();
        temp = new ArrayList<Integer>();
        tempReal = new ArrayList<Integer>();

        for(int i = 0;i<8;i++){
            tmpwallleft = new WallSpriteLeft(-60,40 +80*i);
            tmpwallright = new WallSpriteRight(400,40+80*i);
            ArrWallRight.add(tmpwallright);
            ArrWallLeft.add(tmpwallleft);
            temp.add(i);
        }
        tempscore = birdSprite.getScore();
    }
    BirdSprite getBirdSprite()
    {
        return birdSprite;
    }
    List<WallSpriteLeft> getArrwallleft(){
        return ArrWallLeft;
    }
    List<WallSpriteRight> getArrwallright(){
        return ArrWallRight;
    }
    private void wallmove(){
        if(tempscore != birdSprite.getScore()){
            temp.clear();
            tempReal.clear();
            for (int i = 0;i<8;i++){
                temp.add(i);
            }
            int numofspike = 1+birdSprite.getScore()/7;
            if(numofspike>6){
                numofspike = 6;
            }
            for(int i = 0;i<numofspike;i++){
                int tmp = rand.nextInt(temp.size());
                tempReal.add(temp.get(tmp));
                temp.remove(tmp);
                //System.out.println("time"+i+"  "+temp);
            }
        }
        else{
            for(int i =0;i<tempReal.size();i++){
                if(birdSprite.getScore()%2==1)
                    ArrWallLeft.get(tempReal.get(i)).inScreen();
                else if(birdSprite.getScore()%2==0){
                    ArrWallRight.get(tempReal.get(i)).inScreen();
                }
                //System.out.println("wall"+tempReal.get(i));
            }
        }
        for(int i = 0;i<ArrWallLeft.size();i++){
            if(birdSprite.getScore()%2==0){
                ArrWallLeft.get(i).outScreen();
            }
            else if(birdSprite.getScore()%2==1){
                ArrWallRight.get(i).outScreen();
            }
        }
        tempscore = birdSprite.getScore();
    }
    private void checkhit(){
        if(birdSprite.getScore()%2==1) {
            for (int i = 0; i < ArrWallLeft.size(); i++) {
                if (abs(ArrWallLeft.get(i).getPosition().x+60-(birdSprite.getPosition().x+20)) <= 15 && abs(ArrWallLeft.get(i).getPosition().y +30 -birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit left" + i);
                }
            }
        }
        else if(birdSprite.getScore()%2==0) {
            for (int i = 0; i < ArrWallRight.size(); i++) {
                if (abs(ArrWallRight.get(i).getPosition().x-birdSprite.getPosition().x-20) <= 15 && abs(ArrWallRight.get(i).getPosition().y +30-birdSprite.getPosition().y-20) <= 20) {
                    gameStatus = 0;
                    //System.out.println("hit right" + i);
                }
            }
        }
        if(birdSprite.getPosition().y-40<0 || birdSprite.getPosition().y>630){
            gameStatus =0;
        }
    }
    public void update(float delta){
        if(gameStatus == 1) {
            birdSprite.move(delta);
            wallmove();
            checkhit();
        }
        else if(gameStatus == 0){

            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                setupWorld(myGame);
            }
        }

    }
}
