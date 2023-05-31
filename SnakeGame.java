package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    private Snake snake;

    private int turnDelay;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame(){
        turnDelay = 300;
        setTurnTimer(turnDelay);
        snake = new com.javarush.games.snake.Snake(WIDTH/2, HEIGHT/2);
       drawScene();
       }

    private void drawScene(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.DARKSEAGREEN);
            }
        }
        snake.draw(this);
    }

    @Override
    public void onTurn(int step) {
        snake.move();
        drawScene();
    }

    public com.javarush.games.snake.GameObject createNewHead(){
        com.javarush.games.snake.GameObject oldHead = snakeParts.get(0);
        if(direction == Direction.LEFT){
            return new com.javarush.games.snake.GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction == com.javarush.games.snake.Direction.RIGHT){
            return new com.javarush.games.snake.GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction == com.javarush.games.snake.Direction.UP){
            return new com.javarush.games.snake.GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new com.javarush.games.snake.GameObject(oldHead.x, oldHead.y + 1);
        }
    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
