package site.petrtsv.corsairs.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 04.07.2017.
 */
public class GameController implements InputProcessor
{
	GameWorld model;

	Vector2 touch;

	public GameController(GameWorld model)
	{
		this.model = model;
		touch = new Vector2();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if (model.getState() == GameWorld.WorldState.RUNNING)
		{
			model.getPlayer().changeDirection();
		} else
		{
			model.setState(GameWorld.WorldState.RUNNING);
		}
		return true;
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
		} else
		{
			if (model.getState() == GameWorld.WorldState.RUNNING)
			{
				model.getPlayer().changeDirection();
			} else
			{
				model.setState(GameWorld.WorldState.RUNNING);
			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		model.getUiGroup().onTouchUp(screenX, screenY, pointer, button);
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
