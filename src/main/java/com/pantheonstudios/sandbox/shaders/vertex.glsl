#version 410

layout (location=0) in vec3 position;

void main()
{
    // gl_position expects 4d vector (x, y, z, w)
    // where w is any fourth dimension
    // https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/chapter04/chapter4.html
    gl_Position = vec4(position, 1.0);
}