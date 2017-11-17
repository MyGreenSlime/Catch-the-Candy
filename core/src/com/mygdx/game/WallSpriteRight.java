package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by GreenSlime on 17/11/2560.
 */
public class WallSpriteRight {
    private int wallStatus;
    private Vector2 position;
    private int move;
    public WallSpriteRight(int x,int y){
        wallStatus = 1;
        position = new Vector2(x,y);
        move = 1;
    }

    public void inScreen(){
        //position.x = -30;
        move = -1;
        System.out.println("inright");
        if(position.x == 370){
            move = 0;
        }
        position.x+=move;

    }
    public void outScreen(){
        position.x = 400;
        /*move = 1;
        if(position.x == -60){
            move = 0;
        }
        else{
            position.x-=move;
        }*/
    }
    public Vector2 getPosition(){
        return position;
    }
}
