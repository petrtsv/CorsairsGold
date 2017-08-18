package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import site.petrtsv.corsairs.Corsairs;
import site.petrtsv.corsairs.controllers.MenuController;
import site.petrtsv.corsairs.models.ResultWorld;

/**
 * Created by Петр on 29.07.2017.
 * <p>
 * Screen with the results of a game.
 */

public class ResultScreen extends AppScreen
{
	private static final float MAX_FPS = 60;

	private float time;
	@SuppressWarnings("CanBeFinal")
	private int gameResult;
	@SuppressWarnings("CanBeFinal")
	private MenuController controller;
	@SuppressWarnings("CanBeFinal")
	private ResultWorld model;

	public ResultScreen(Corsairs game, int width, int height, int gameResult)
	{
		this.game = game;
		this.width = width;
		this.height = height;
		this.gameResult = gameResult;
		this.model = new ResultWorld(this);
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

	public int getGameResult()
	{
		return gameResult;
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
