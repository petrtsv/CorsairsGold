package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import site.petrtsv.corsairs.Corsairs;
import site.petrtsv.corsairs.controllers.GameController;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 04.07.2017.
 *
 * Screen with the game.
 */
public class GameScreen implements Screen
{
	private static final float MAX_FPS = 120;
	@SuppressWarnings("CanBeFinal")
	public int width;
	@SuppressWarnings("CanBeFinal")
	public int height;
	private float time;

	@SuppressWarnings("CanBeFinal")
	private Corsairs game;
	@SuppressWarnings("CanBeFinal")
	private GameController controller;
	@SuppressWarnings("CanBeFinal")
	private GameWorld model;


	GameScreen(Corsairs game, int width, int height)
	{
		this.game = game;
		this.width = width;
		this.height = height;

		model = new GameWorld(this);
		controller = new GameController(model);


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

	public void onGameOver()
	{
		dispose();
		Screen newScreen = new ResultScreen(game, width, height, model);
		game.setScreen(newScreen);
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{
		model.setState(GameWorld.WorldState.PAUSED);
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
























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































