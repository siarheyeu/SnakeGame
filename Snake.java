package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final static String HEAD_SIGN = "\uD83D\uDC7E";
    private final static String BODY_SIGN = "\u26AB";


    private List<com.javarush.games.snake.GameObject> snakeParts = new ArrayList<>();

    public boolean isAlive = true;

    private Direction direction = Direction.LEFT;



    public Snake(int x, int y) {
        snakeParts.add(new com.javarush.games.snake.GameObject(x, y));
        snakeParts.add(new com.javarush.games.snake.GameObject(x+1, y));
        snakeParts.add(new com.javarush.games.snake.GameObject(x+2, y));
    }

    public void draw (Game game){
        Color color = isAlive ? Color.BLACK : Color.RED;

        for (int i = 0; i < snakeParts.size(); i++) {
            com.javarush.games.snake.GameObject part = snakeParts.get(i);
            if (i==0){
                game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, color, 75);
            } else {
                game.setCellValueEx(part.x, part.y, Color.NONE, sign, BODY_SIGN, 75);
            }
        }
    }

    public void move(Apple apple) {
        com.javarush.games.snake.GameObject newHead = createNewHead();
        if (newHead.x >= com.javarush.games.snake.SnakeGame.WIDTH
                || newHead.x < 0
                || newHead.y >= com.javarush.games.snake.SnakeGame.HEIGHT
                || newHead.y < 0) {
            isAlive = false;
            return;
        }

        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

        
        public com.javarush.games.snake.GameObject createNewHead() {
            com.javarush.games.snake.GameObject oldHead = snakeParts.get(0);
            if (direction == com.javarush.games.snake.Direction.LEFT){
                return new com.javarush.games.snake.GameObject(oldHead.x - 1, oldHead.y);
            } else if (direction == com.javarush.games.snake.Direction.RIGHT) {
                return new com.javarush.games.snake.GameObject(oldHead.x + 1, oldHead.y);
            } else if (direction == com.javarush.games.snake.Direction.UP) {
                return new com.javarush.games.snake.GameObject(oldHead.x, oldHead.y - 1);
            } else {
                return new com.javarush.games.snake.GameObject(oldHead.x, oldHead.y + 1);
            }
        }

        public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
        }
    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }

        this.direction = direction;
    }

    public boolean checkCollision(com.javarush.games.snake.GameObject gameObject) {
        for (com.javarush.games.snake.GameObject : snakeParts) {
            if (part.x == gameObject.x && part.y == gameObject.y){
                return true;
            }
        }
        return false;
    }

    public int getLength(){
        return snakeParts.size();
    }
}
