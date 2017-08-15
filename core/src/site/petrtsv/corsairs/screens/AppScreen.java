package site.petrtsv.corsairs.screens;

import com.badlogic.gdx.Screen;

import site.petrtsv.corsairs.Corsairs;

/**
 * Created by Петр on 15.08.2017.
 * <p>
 * Abstract app screen.
 */

public abstract class AppScreen implements Screen
{
	public abstract Corsairs getGame();

	public void newScreen(Screen screen)
	{
		dispose();
		getGame().setScreen(screen);
	}

	public abstract int getWidth();

	public abstract int getHeight();
}
