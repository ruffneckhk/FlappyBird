package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jorgejnet.flappybird.FlappyBird;
import com.jorgejnet.flappybird.sprites.Bird;
import com.jorgejnet.flappybird.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 320);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, 0);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), 0);

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
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

        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                gsm.set(new MenuState(gsm));
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth / 2), camera.position.y - (camera.viewportHeight / 2));
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PLAY STATE DISPOSE");
    }
}
