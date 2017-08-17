package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.interfaces.Destructible;
import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.models.Model;

/**
 * Created by Петр on 20.06.2017.
 * <p>
 * Abstract button on the screen.
 */
public abstract class GameButton extends Actor implements Destructible
{
	@SuppressWarnings("CanBeFinal")
	Model world;
	@SuppressWarnings("CanBeFinal")
	UIGroup group;

	GameButton(UIGroup group, int x, int y)
	{
		this.group = group;
		this.world = group.getWorld();
		setX(x);
		setY(y);
	}

	public abstract void onTouchDown(int pointer);

	public abstract void onTouchUp(int pointer);

	@SuppressWarnings("unused")
	enum ButtonState
	{
		PRESSED, NOTPRESSED
	}

}
