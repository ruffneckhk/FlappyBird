package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jorgejnet.flappybird.FlappyBird;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        spriteBatch.draw(playButton, (FlappyBird.WIDTH / 2) - (playButton.getWidth() / 2), (FlappyBird.HEIGHT / 2) - (playButton.getHeight() / 2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("MENU STATE DISPOSE");
    }
}
