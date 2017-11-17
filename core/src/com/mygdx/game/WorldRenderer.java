package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
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
    public void render(float delta){
        SpriteBatch batch = myGame.batch;
        imgupdate(world.getBirdSprite().getStatus());
        batch.begin();
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
}
