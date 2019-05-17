package com.jaszczurowskip.javavskotlin_javaversion.fps;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by jaszczurowskip on 29.03.2019
 */
public class MyGlSurfaceView extends GLSurfaceView {

    BouncyCubeRenderer myRender;

    public MyGlSurfaceView(Context context) {
        super(context);

    }

    public MyGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupView(int cubesCount, int rotateSpeed) {
        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.myRender = new BouncyCubeRenderer();
        myRender.setCubesCount(cubesCount, rotateSpeed / 2);
        setRenderer(myRender);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


    public void changeAmountOfCubes(int value, int rotateSpeed) {
        myRender.setCubesCount(value, rotateSpeed);
    }

    public String getFramerate() {
        return myRender.getLastFramerate();
    }
}
