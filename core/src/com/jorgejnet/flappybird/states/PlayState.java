package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jorgejnet.flappybird.FlappyBird;

public class PlayState extends State {

    private Texture bird;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Texture("bird.png");
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bird, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
