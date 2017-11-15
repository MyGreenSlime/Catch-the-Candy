package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.font.TrueTypeFont;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.StrictMath.abs;

/**
 * Created by GreenSlime on 15/11/2560.
 */
public class GameScreen extends ScreenAdapter {
    private MyGame myGame;
    private Texture spriteImg;
    private int x;
    private int y;
    private boolean check = FALSE;
    private int speedy = 0;
    private int Garvity;
    private float time = 0;
    private int Flip = 0;
    private int speedx = 1;
    public GameScreen(MyGame myGame){
        this.myGame = myGame;
        spriteImg = new Texture("bird1.png");
        x =20;
        y =300;
        Garvity = 2;
    }
    private void update(float delta){
        time+=delta;
        x+=speedx;
        y+=speedy;
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)&&check == FALSE) {
            if(Flip==0)
                spriteImg = new Texture("bird2.png");
            else
                spriteImg = new Texture("bird2flip.png");
            speedy = 3;
            y+=30;
            check = TRUE;

        }
        if(time>0.2){
            speedy-=Garvity;
            if(check){
                if(Flip==0)
                    spriteImg = new Texture("bird1.png");
                else
                    spriteImg = new Texture("bird1flip.png");
                check =FALSE;
            }
            time = 0;
        }
        if(340-x<0){
            speedx*=-1;
            Flip = 1;
            spriteImg = new Texture("bird1flip.png");
        }
        if(x<0){
            speedx*=-1;
            Flip = 0;
            spriteImg = new Texture("bird1.png");
        }


    }
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = myGame.batch;
        update(delta);
        batch.begin();
        batch.draw(spriteImg, x, y);
        batch.end();
    }
}

