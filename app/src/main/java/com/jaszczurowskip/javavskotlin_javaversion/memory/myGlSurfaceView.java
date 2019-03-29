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
        // Set the Renderer for drawing on the GLSurfaceView
        this.myRender = new BouncyCubeRenderer(true);
        setRenderer(myRender);
        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


}
