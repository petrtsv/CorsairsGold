package site.petrtsv.corsairs.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.models.MenuWorld;

/**
 * Created by Петр on 26.07.2017.
 * <p>
 * Class, that process input in the menu.
 */

public class MenuController implements InputProcessor
{
	@SuppressWarnings("CanBeFinal")
	private MenuWorld model;
	@SuppressWarnings("CanBeFinal")
	private Vector2 touch;

	public MenuController(MenuWorld model)
	{
		this.model = model;
		touch = new Vector2();
	}


	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		Vector3 touchPoint = model.getCamera().unproject(new Vector3(screenX, screenY, 0));
		touch.set(touchPoint.x, touchPoint.y);
		Actor actor = model.hit(touch.x, touch.y, true);
		if (actor != null)
		{
			if (actor instanceof GameButton)
			{
				((GameButton) actor).onTouchDown(pointer);
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		model.getUIGroup().onTouchUp(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}
