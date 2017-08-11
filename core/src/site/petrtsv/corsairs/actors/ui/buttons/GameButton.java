package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.interfaces.Destructible;

/**
 * Created by Петр on 20.06.2017.
 */
public abstract class GameButton extends Actor implements Destructible
{
	public abstract void onTouchDown(int pointer);

	public abstract void onTouchUp(int pointer);

	public enum ButtonState
	{
		PRESSED, NOTPRESSED
	}

}
