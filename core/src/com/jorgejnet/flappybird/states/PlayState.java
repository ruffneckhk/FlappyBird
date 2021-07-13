package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jorgejnet.flappybird.FlappyBird;
import com.jorgejnet.flappybird.sprites.Bird;

public class PlayState extends State {

    private Bird bird;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 320);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
