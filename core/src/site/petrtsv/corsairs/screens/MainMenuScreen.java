package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import site.petrtsv.corsairs.Corsairs;
import site.petrtsv.corsairs.controllers.MenuController;
import site.petrtsv.corsairs.models.MainMenuWorld;

/**
 * Created by Петр on 26.07.2017.
 */

public class MainMenuScreen implements Screen
{

	public static final float MAX_FPS = 60;
	public int width;
	public int height;
	public float time;

	Corsairs game;
	MenuController controller;
	MainMenuWorld model;

	public MainMenuScreen(Corsairs game, int width, int height)
	{
		this.game = game;
		this.width = width;
		this.height = height;
		this.model = new MainMenuWorld(this);
		this.controller = new MenuController(model);

	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void render(float delta)
	{
		time += delta;
		if (time >= 1 / MAX_FPS)
		{

			Gdx.gl.glClearColor(82.0f / 255.0f, 219.0f / 255.0f, 255.0f / 255.0f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			model.act(delta);
			model.draw();
		}

	}

	public void onPlayButtonPressed()
	{
		dispose();
		Screen newScreen = new GameScreen(game, width, height);
		game.setScreen(newScreen);
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{
		model.dispose();
	}
}
