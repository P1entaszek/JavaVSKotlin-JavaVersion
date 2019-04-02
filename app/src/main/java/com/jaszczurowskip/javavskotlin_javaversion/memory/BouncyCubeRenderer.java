package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/**
 * Created by jaszczurowskip on 29.03.2019
 */
class BouncyCubeRenderer implements GLSurfaceView.Renderer {

    public static final float OBJ_SCALE = 0.3f;
    private float rowSize = 10;
    private Cube mCube;
    private float mAngle;
    private int cubesCount;
    private long lastFrameTime;
    float fps;
    private int width;
    private int height;

    public void setCubesCount(int cubesCount) {
        this.cubesCount = cubesCount;

    }

    public BouncyCubeRenderer() {
        mCube = new Cube();
        lastFrameTime = System.currentTimeMillis();
    }


    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL11.GL_COLOR_ARRAY);
        int cubesCounter = (int)(rowSize*rowSize);
        gl.glLoadIdentity();
        float scaleW = width / rowSize;
        float scaleH = height / rowSize;
//        gl.glTranslatef(-rowSizeW/2, -rowSizeH/2, -rowSizeW);
        gl.glTranslatef(scaleW/2, scaleH/2, -10);
        for (int x = 0; x < rowSize; x++) {
            for (int y = 0; y < rowSize; y++) {

                gl.glPushMatrix();
                gl.glTranslatef(x*scaleW, (float) y*scaleH, 0.1f);
//            gl.glScalef(0.9f/rowSizeW, 0.9f/rowSizeH, 0.9f/rowSizeW);
                gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);
                gl.glScalef(scaleW* OBJ_SCALE, scaleW*OBJ_SCALE, scaleW*OBJ_SCALE);
                mCube.draw(gl);
                gl.glPopMatrix();
                cubesCounter--;
            }
        }
        long currentTime = System.currentTimeMillis();
        long frameTime = currentTime - lastFrameTime;
        lastFrameTime = currentTime;
        mAngle += 200.8f *(((frameTime /1000.f)));
        fps = 1000.0f / ((float) frameTime);
        Log.e("fps", "" + fps);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.width = width;
        this.height = height;
        gl.glViewport(0, 0, width, height);
        float aspectRatio;
        float zNear = .1f;
        float zFar = 1000;
        float fieldOfView = 60.0f / 57.3f;
        float size;

        gl.glEnable(GL10.GL_NORMALIZE);
        aspectRatio = (float) width / (float) height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));
        gl.glOrthof(0.0f, width, height, 0.0f, -1.0f, 1000.0f);
//        gl.glFrustumf(-size, size, -size / aspectRatio,
//                size / aspectRatio, zNear, zFar);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL11.GL_DITHER);
        gl.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
        gl.glClearColor(1, 1, 1, 1);
        gl.glEnable(GL11.GL_CULL_FACE);
        gl.glShadeModel(GL11.GL_SMOOTH);
        gl.glEnable(GL11.GL_DEPTH_TEST);
    }
}
