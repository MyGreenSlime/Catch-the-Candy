package com.mygdx.game;

/**
 * Created by GreenSlime on 15/11/2560.
 */
public class World {
    private BirdSprite birdSprite;
    private MyGame myGame;
    World(MyGame myGame){
        this.myGame = myGame;
        birdSprite = new BirdSprite(200,350);
    }
    BirdSprite getBirdSprite()
    {
        return birdSprite;
    }
}
