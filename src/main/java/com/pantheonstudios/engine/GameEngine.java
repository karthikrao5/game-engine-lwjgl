package com.pantheonstudios.engine;

import com.pantheonstudios.sandbox.Renderer;

public class GameEngine implements Runnable {

    private Window window;
    private final GameLogic gameLogic;

    private static int fps;
    private static float framerate = 200;
    private static float frameTime = 1.0f / framerate;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, GameLogic gameLogic) {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
            window.closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            gameLogic.cleanup();
        }
    }

    public void init() throws Exception {
        window.init();
        gameLogic.init();
    }

    public void gameLoop() {
        boolean isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;

            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) Constants.NANOSECOND;
            frameCounter += passedTime;

            input();

            while (unprocessedTime > frameTime) {
                render = true;
                unprocessedTime -= frameTime;

                if (window.windowShouldClose())
                    isRunning = false;

                if (frameCounter >= Constants.NANOSECOND) {
                    fps = frames;
                    frames = 0;
                    frameCounter = 0;
                }

                update(frames);
            }

            if (render) {
                render();

                frames++;

            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void input() {
        gameLogic.input(window);
    }

    public void update(float interval) {
        gameLogic.update(interval);
    }

    public void render() {
        gameLogic.render(window);
        window.update();
    }
}
