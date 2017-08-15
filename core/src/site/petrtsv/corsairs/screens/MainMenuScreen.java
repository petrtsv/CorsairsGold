package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import site.petrtsv.corsairs.Corsairs;
import site.petrtsv.corsairs.controllers.MenuController;
import site.petrtsv.corsairs.models.MainMenuWorld;

/**
 * Created by Петр on 26.07.2017.
 *
 * Screen with the main menu.
 */

public class MainMenuScreen extends AppScreen
{

	private static final float MAX_FPS = 60;
	@SuppressWarnings("CanBeFinal")
	public int width;
	@SuppressWarnings("CanBeFinal")
	public int height;
	private float time;

	@SuppressWarnings("CanBeFinal")
	private Corsairs game;
	@SuppressWarnings("CanBeFinal")
	private MenuController controller;
	@SuppressWarnings("CanBeFinal")
	private MainMenuWorld model;

	public MainMenuScreen(Corsairs game, int width, @SuppressWarnings("SameParameterValue") int height)
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

	@Override
	public Corsairs getGame()
	{
		return game;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}
}
