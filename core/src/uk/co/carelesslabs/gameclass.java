package uk.co.carelesslabs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gameclass extends ApplicationAdapter {
    OrthographicCamera camera;
    Control control;
    SpriteBatch batch;
    Texture img;

    // Display Size
    private int displayW;
    private int displayH;

    // Temp x and y co-ords
    int x, y;

    // For Movement
    int direction_x, direction_y;
    int speed = 3;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        
        // CAMERA
        displayW = Gdx.graphics.getWidth();
        displayH = Gdx.graphics.getHeight();
        
        // For 800x600 we will get 266*200
        int h = (int) (displayH/Math.floor(displayH/160));
        int w = (int) (displayW/(displayH/ (displayH/Math.floor(displayH/160))));
        
        camera = new OrthographicCamera(w,h);
        camera.zoom = .4f;
        
        // Used to capture Keyboard Input
        control = new Control(displayW, displayH, camera);
        Gdx.input.setInputProcessor(control);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // GAME LOGIC
        // Reset the direction values
        direction_x=0;
        direction_y=0;
            
        if(control.down)  direction_y = -1 ;
        if(control.up)    direction_y = 1 ;
        if(control.left)  direction_x = -1;
        if(control.right) direction_x = 1;
            
        camera.position.x += direction_x * speed;
        camera.position.y += direction_y * speed;
        camera.update();
        
        // GAME DRAW
        batch.setProjectionMatrix(camera.combined);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }
	
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}