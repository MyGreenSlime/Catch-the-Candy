package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by GreenSlime on 15/11/2560.
 */
public class World {
    private BirdSprite birdSprite;
    private MyGame myGame;
    private WallSpriteLeft tmpwallleft;
    private List<WallSpriteLeft> ArrWallLeft;
    private Random rand;
    private int tempscore;
    World(MyGame myGame){
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);
        ArrWallLeft = new ArrayList<WallSpriteLeft>();
        rand = new Random();
        for(int i = 0;i<10;i++){
            tmpwallleft = new WallSpriteLeft(-60,60 +60*i);
            ArrWallLeft.add(tmpwallleft);
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
    public void update(float delta){
        birdSprite.move(delta);
        List<Integer> temp = new ArrayList<Integer>();
        for(int i = 0;i<10;i++){
            temp.add(i);
        }
        List<Integer> temp2 = new ArrayList<Integer>();
        if(tempscore!=birdSprite.getScore()){
            for(int i =0;i<1+birdSprite.getScore()/5;i++){
                int tmp = rand.nextInt(temp.size());
                ArrWallLeft.get(tmp).inScreen();
                System.out.println("pos" + tmp +" "+ArrWallLeft.get(tmp).getPosition().x+" "+ArrWallLeft.get(tmp).getPosition().y);
                temp2.add(tmp);
                temp.remove(tmp);
            }
        }
        for(int i = 0;i<ArrWallLeft.size();i++){
            if(birdSprite.getScore()%2==0){
                ArrWallLeft.get(i).outScreen();
            }
        }
        tempscore = birdSprite.getScore();

    }
}
