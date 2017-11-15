package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public WorldRenderer(MyGame myGame,World world){
        this.myGame = myGame;
        batch = myGame.batch;
        this.world = world;
        spriteImg = new Texture("bird1.png");
        wallheadImg = new Texture("spikewallhead.png");
        wallbottomImg = new Texture("spikewallbottom.png");
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
    public void render(float delta){
        SpriteBatch batch = myGame.batch;
        imgupdate(world.getBirdSprite().getStatus());
        batch.begin();
        batch.draw(spriteImg, world.getBirdSprite().getPosition().x, world.getBirdSprite().getPosition().y);
        batch.draw(wallbottomImg, 0, 0);
        batch.draw(wallheadImg, 0, 640);
        batch.end();
    }
}
