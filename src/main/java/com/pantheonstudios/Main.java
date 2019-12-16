package com.pantheonstudios;

import com.pantheonstudios.engine.GameEngine;
import com.pantheonstudios.engine.GameLogic;
import com.pantheonstudios.sandbox.DummyGame;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            GameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}