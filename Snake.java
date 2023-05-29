package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final static String HEAD_SIGN = "\uD83D\uDC7E";
    private final static String BODY_SIGN = "\u26AB";

    private List<com.javarush.games.snake.GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        snakeParts.add(new com.javarush.games.snake.GameObject(x, y));
        snakeParts.add(new com.javarush.games.snake.GameObject(x+1, y));
        snakeParts.add(new com.javarush.games.snake.GameObject(x+2, y));
    }

    public void draw (Game game){
        for (int i = 0; i < snakeParts.size(); i++) {
            com.javarush.games.snake.GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValue(part.x, part.y, sign);

        }

    }
}
