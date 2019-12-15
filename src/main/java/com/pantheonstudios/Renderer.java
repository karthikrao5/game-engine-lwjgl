package com.pantheonstudios;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public void init() {

    }

    public void update() {

    }

    public void clear() {
        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
