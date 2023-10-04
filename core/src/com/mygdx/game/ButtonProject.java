package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ButtonProject extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Texture lightBulbOff;
	Texture lightBulbOn;
	Rectangle lightBulb;
	ShapeRenderer shapeRenderer;
	Boolean lightOn;
	BitmapFont font;
	Boolean buttonPressed;
	CustomButton cb;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		lightBulbOff = new Texture("lightbulb_off.png");
		lightBulbOn = new Texture("lightbulb_on.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		lightBulb = new Rectangle();
		lightBulb.x = 800 / 2 - 128 / 2;
		lightBulb.y = 225;
		lightBulb.width = 128;
		lightBulb.height = 128;

		shapeRenderer = new ShapeRenderer();

		lightOn = false;
		cb = new CustomButton(800 / 2 - 200 / 2, 150, 200, 50, "",
				81f / 255, 197f / 255, 1f,
				0f, 46f / 255, 122f / 255);
		font = new BitmapFont();
	}

	@Override
	public void render () {
		ScreenUtils.clear(153f / 255, 153f / 255, 153f / 255, 1);
		camera.update();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(cb.getInsideRGB()[0], cb.getInsideRGB()[1], cb.getInsideRGB()[2], 1f);
		shapeRenderer.rect(cb.getX(), cb.getY(), cb.getWidth(), cb.getHeight());
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(cb.getFrameRGB()[0], cb.getFrameRGB()[1], cb.getFrameRGB()[2], 1f);
		shapeRenderer.rect(cb.getX(), cb.getY(), cb.getWidth(), cb.getHeight());
		shapeRenderer.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(lightOn ? lightBulbOn : lightBulbOff, lightBulb.x, lightBulb.y);
		font.setColor(Color.WHITE);
		cb.setText(lightOn ? "Turn light off" : "Turn light on");
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, cb.getText());
		float textX = cb.getX() + (cb.getWidth() - layout.width) / 2;
		float textY = cb.getY() + (cb.getHeight() + layout.height) / 2;
		font.draw(batch, cb.getText(),
				textX, textY);
		batch.end();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			float mouseX = touchPos.x;
			float mouseY = touchPos.y;
			Vector3 buttonPos = new Vector3(cb.getX(), cb.getY(), 0);
			//camera.unproject(buttonPos);
			if (mouseX >= buttonPos.x && mouseX <= buttonPos.x + cb.getWidth() &&
					mouseY >= buttonPos.y && mouseY <= buttonPos.y + cb.getHeight())
			{
				lightOn = !lightOn;
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		lightBulbOff.dispose();
		lightBulbOn.dispose();
	}
}
