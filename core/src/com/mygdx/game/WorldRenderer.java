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
    private Texture wallheadImg;
    private Texture wallbottomImg;
    private Texture circle;
    private Texture gameOver;
    private Texture scoreboard;
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
        wallheadImg = new Texture("spikewallhead.png");
        wallbottomImg = new Texture("spikewallbottom.png");
        circle = new Texture("circle.png");
        gameOver =new Texture("gameover.png");
        scoreboard = new Texture("scoreboard.png");
        num1 = new ArrayList<Texture>();
        num2 = new ArrayList<Texture>();
        num1 = Arrays.asList(new Texture("0.png"),new Texture("1.png"),new Texture("2.png"),new Texture("3.png"),new Texture("4.png"),new Texture("5.png"),new Texture("6.png"),new Texture("7.png"),new Texture("8.png"),new Texture("9.png"));
        num2 = Arrays.asList(new Texture("0.png"),new Texture("1.png"),new Texture("2.png"),new Texture("3.png"),new Texture("4.png"),new Texture("5.png"),new Texture("6.png"),new Texture("7.png"),new Texture("8.png"),new Texture("9.png"));

        arrspikeleft = new ArrayList<Texture>();
        for(int i = 0;i<world.getArrwallleft().size();i++){
            spikeleftImg = new Texture("spikeleft.png");
            arrspikeleft.add(spikeleftImg);
        }

        arrspikeright = new ArrayList<Texture>();
        for(int i = 0;i<world.getArrwallright().size();i++){
            spikerightImg = new Texture("spikeright.png");
            arrspikeright.add(spikerightImg);
        }

    }
    private void imgupdate(int status){
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
    private int calscore(char i){
        if(i == '/'){
            int x = world.getBirdSprite().getScore()/10;
            return x;
        }
        else if(i == '%'){
            int x = world.getBirdSprite().getScore()%10;
            return x;
        }
        return 0;
    }
    private void renderplay(){
        SpriteBatch batch = myGame.batch;
        imgupdate(world.getBirdSprite().getStatus());
        batch.begin();
        batch.draw(circle, 50, 200);
        batch.draw(num1.get(calscore('/')), 80, 275);
        batch.draw(num1.get(calscore('%')),200,275);

        batch.draw(spriteImg, world.getBirdSprite().getPosition().x, world.getBirdSprite().getPosition().y);
        batch.draw(wallbottomImg, 0, -30);
        batch.draw(wallheadImg, 0, 670);
        for(int i =0;i<arrspikeleft.size();i++){
            batch.draw(arrspikeleft.get(i),world.getArrwallleft().get(i).getPosition().x,world.getArrwallleft().get(i).getPosition().y);
        }
        for(int i =0;i<arrspikeright.size();i++){
            batch.draw(arrspikeright.get(i),world.getArrwallright().get(i).getPosition().x,world.getArrwallright().get(i).getPosition().y);
        }
        batch.end();
    }
    private void rendergameover(){
        SpriteBatch batch = myGame.batch;
        batch.begin();
        batch.draw(gameOver,50,200);
        batch.draw(scoreboard, 100, 125);
        batch.draw(num1.get(calscore('/')), 210, 125,50,60);
        batch.draw(num1.get(calscore('%')),250,125,50,60);
        batch.end();
    }
    public void render(float delta){

        if(world.gameStatus == 1){
            renderplay();
        }
        else if(world.gameStatus == 0)
        {
            rendergameover();
        }

    }
}
