package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jorgejnet.flappybird.FlappyBird;
import com.jorgejnet.flappybird.sprites.Bird;
import com.jorgejnet.flappybird.sprites.Tube;

public class PlayState extends State {

    private Bird bird;
    private Texture bg;
    private Tube tube;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 320);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        tube = new Tube(100);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
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
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2));
        //spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth / 2),camera.position.y - (camera.viewportHeight / 2), FlappyBird.WIDTH / 2, FlappyBird.HEIGHT /2);
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
