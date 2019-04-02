package com.jaszczurowskip.javavskotlin_javaversion.memory;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by jaszczurowskip on 29.03.2019
 */
public class myGlSurfaceView extends GLSurfaceView {

    BouncyCubeRenderer myRender;

    public myGlSurfaceView(Context context) {
        super(context);

    }

    public myGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupView() {
        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.myRender = new BouncyCubeRenderer();
        myRender.setCubesCount(100);
        setRenderer(myRender);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public float getFramerate(){
        return myRender.fps;
    }
}
