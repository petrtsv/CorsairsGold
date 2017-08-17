package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import site.petrtsv.corsairs.Corsairs;
import site.petrtsv.corsairs.controllers.MenuController;
import site.petrtsv.corsairs.models.InfoWorld;

/**
 * Created by Петр on 17.08.2017.
 * <p>
 * Screen with information about game.
 */

public class InfoScreen extends AppScreen
{
	private static final float MAX_FPS = 60;
	@SuppressWarnings("CanBeFinal")
	private int width;
	@SuppressWarnings("CanBeFinal")
	private int height;
	@SuppressWarnings("CanBeFinal")
	private Corsairs game;
	private float time;
	@SuppressWarnings("CanBeFinal")
	private MenuController controller;
	@SuppressWarnings("CanBeFinal")
	private InfoWorld model;

	public InfoScreen(Corsairs game, int width, int height)
	{
		this.game = game;
		this.width = width;
		this.height = height;
		this.model = new InfoWorld(this);
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
