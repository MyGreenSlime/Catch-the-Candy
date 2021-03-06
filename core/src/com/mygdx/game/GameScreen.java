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
    private WorldRenderer worldRenderer;
    public static final int HEIGHT = 700;
    public static final int WIDTH = 400;

    private World world;

    public GameScreen(MyGame myGame) {
        myGame = myGame;
        world = new World(myGame);
        worldRenderer = new WorldRenderer(myGame, world);

    }

    private void update(float delta) {
        world.update(delta);

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(.50f, .230f, .50f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        worldRenderer.render(delta);

    }
}

