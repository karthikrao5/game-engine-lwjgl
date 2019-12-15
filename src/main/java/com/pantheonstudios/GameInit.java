package com.pantheonstudios;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameInit {

    // The window handle
    private Window window;
    private Renderer renderer;
    private static int fps;
    private static float framerate = 200;
    private static float frameTime = 1.0f / framerate;


    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        window = new Window("Sandbox", 1280, 720, true);
        renderer = new Renderer();

        init();
        loop();

        window.closeWindow();
    }

    private void init() {
        window.init();
        renderer.init();
    }

    private void loop() {
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
            }
            if (render) {
                renderer.clear();
                window.update();

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

    public static void main(String[] args) {
        new GameInit().run();
    }
}