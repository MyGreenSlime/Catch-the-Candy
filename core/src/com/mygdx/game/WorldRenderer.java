package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GreenSlime on 15/11/2560.
 */
public class WorldRenderer {
    private SpriteBatch batch;
    private MyGame myGame;
    private World world;

    private Texture spriteImg;
    private Texture wallHeadImg;
    private Texture wallBottomImg;
    private Texture circle;
    private Texture home;
    private Texture gameOver;
    private Texture scoreBoard;
    private Texture candy;
    private List<Texture> num1;
    private List<Texture> num2;

    private Texture spikeleftImg;
    private List<Texture> arrspikeleft;

    private Texture spikerightImg;
    private List<Texture> arrspikeright;


    public WorldRenderer(MyGame myGame,World world){
        this.myGame = myGame;
        batch = myGame.batch;
        this.world = world;
        spriteImg = new Texture("bird1.png");
        wallHeadImg = new Texture("spikewallhead.png");
        wallBottomImg = new Texture("spikewallbottom.png");
        circle = new Texture("circle.png");
        home = new Texture("start.png");
        gameOver =new Texture("gameover.png");
        scoreBoard = new Texture("scoreboard.png");
        candy = new Texture("candy.png");
        num1 = new ArrayList<Texture>();
        num2 = new ArrayList<Texture>();
        num1 = Arrays.asList(new Texture("0.png"),new Texture("1.png"),new Texture("2.png"),new Texture("3.png"),new Texture("4.png"),new Texture("5.png"),new Texture("6.png"),new Texture("7.png"),new Texture("8.png"),new Texture("9.png"));
        num2 = Arrays.asList(new Texture(
                "0.png"),new Texture("1.png"),new Texture("2.png"),new Texture("3.png"),new Texture("4.png"),new Texture("5.png"),new Texture("6.png"),new Texture("7.png"),new Texture("8.png"),new Texture("9.png"));

        arrspikeleft = new ArrayList<Texture>();
        for(int i = 0;i<world.getArrWallLeft().size();i++){
            spikeleftImg = new Texture("spikeleft.png");
            arrspikeleft.add(spikeleftImg);
        }

        arrspikeright = new ArrayList<Texture>();
        for(int i = 0;i<world.getArrWallRight().size();i++){
            spikerightImg = new Texture("spikeright.png");
            arrspikeright.add(spikerightImg);
        }


    }
    public Texture getSpriteImg() {
        return spriteImg;
    }
    public Texture getSpikeleftImg(){
        return  spikeleftImg;
    }
    public Texture getSpikerightImg(){
        return  spikeleftImg;
    }
    public Texture getCandyImg(){
        return candy;
    }

    private void imgUpdate(int status){
        if(status == 0) {
            spriteImg = new Texture("bird1.png");
        }
        else if(status == 1) {
            spriteImg = new Texture("bird1flip.png");
        }
        else if(status == 2) {
            spriteImg = new Texture("bird2.png");
        }
        else if(status == 3) {
            spriteImg = new Texture("bird2flip.png");
        }
    }
    private int calScore(char i){
        if(i == '/'){
            int x = world.score/10;
            return x;
        }
        else if(i == '%'){
            int x = world.score%10;
            return x;
        }
        return 0;
    }
    private void renderPlay(){
        SpriteBatch batch = myGame.batch;
        imgUpdate(world.getBirdSprite().getStatus());
        batch.begin();
        batch.draw(circle, 50, 200);
        batch.draw(num1.get(calScore('/')), 80, 275);
        batch.draw(num1.get(calScore('%')),200,275);
        batch.draw(candy, world.getCandy().getPosition().x, world.getCandy().getPosition().y);
        batch.draw(spriteImg, world.getBirdSprite().getPosition().x, world.getBirdSprite().getPosition().y);
        batch.draw(wallBottomImg, 0, -30);
        batch.draw(wallHeadImg, 0, 670);
        for(int i =0;i<arrspikeleft.size();i++){
            batch.draw(arrspikeleft.get(i),world.getArrWallLeft().get(i).getPosition().x,world.getArrWallLeft().get(i).getPosition().y);
        }
        for(int i =0;i<arrspikeright.size();i++){
            batch.draw(arrspikeright.get(i),world.getArrWallRight().get(i).getPosition().x,world.getArrWallRight().get(i).getPosition().y);
        }
        batch.end();
    }
    private void renderGameOver(){
        SpriteBatch batch = myGame.batch;
        batch.begin();
        batch.draw(gameOver,50,200);
        batch.draw(scoreBoard, 100, 125);
        batch.draw(num1.get(calScore('/')), 210, 125,50,60);
        batch.draw(num1.get(calScore('%')),250,125,50,60);
        batch.end();
    }
    public void render(float delta){

        if(world.gameStatus == -1){
            SpriteBatch batch = myGame.batch;
            batch.begin();
            batch.draw(home,50,150);
            batch.end();
        }
        else if(world.gameStatus == 0&&world.getBirdSprite().getPosition().y<0)
        {
            renderGameOver();
        }
        else if(world.gameStatus == 1||world.gameStatus == 0){
            renderPlay();
        }

    }
}
