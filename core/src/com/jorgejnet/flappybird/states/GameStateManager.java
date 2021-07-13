package com.jorgejnet.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;
/*
Administra los estados del juego a traves de una pila
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    //Agrega estado a la pila
    public void push(State state) {
        states.push(state);
    }

    //Elimina el ultimo stado de la pila
    public void pop() {
        states.pop();
    }

    //Reemplaza un estado previo
    public void set(State state) {
        states.pop();
        states.push(state);
    }

    //Actualiza el juego segun el delta time
    public void update(float dt) {
        states.peek().update(dt);
    }

    //Renderiza los sprits
    public void render(SpriteBatch spriteBatch) {
        states.peek().render(spriteBatch);
    }
}
