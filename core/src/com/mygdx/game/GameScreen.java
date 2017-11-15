package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private Texture wallheadImg;
    private Texture wallbottomImg;
    private BirdSprite birdSprite;
    private Vector2 pos;
    public GameScreen(MyGame myGame){
        this.myGame = myGame;
        spriteImg = new Texture("bird1.png");
        wallheadImg = new Texture("spikewallhead.png");
        wallbottomImg = new Texture("spikewallbottom.png");
        birdSprite = new BirdSprite(20,350);

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
    private void update(float delta){
        birdSprite.move(delta);
        imgupdate(birdSprite.getStatus());
        //System.out.println(birdSprite.getPosition().x);

    }
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = myGame.batch;
        batch.begin();
        update(delta);
        batch.draw(spriteImg, birdSprite.getPosition().x, birdSprite.getPosition().y);
        batch.draw(wallbottomImg, 0, 0);
        batch.draw(wallheadImg, 0, 640);
        batch.end();
    }
}

