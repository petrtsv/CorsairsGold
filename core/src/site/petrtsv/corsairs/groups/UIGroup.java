package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.models.MainMenuWorld;
import site.petrtsv.corsairs.models.Model;

/**
 * Created by Петр on 29.07.2017.
 * <p>
 * Abstract group, that contains UI elements.
 */

public abstract class UIGroup extends Group implements Disposable
{
	public static final Color MAIN_UI = Color.WHITE;
	static final Color SPECIAL_UI = new Color(115f / 255f, 205f / 255f, 75f / 255f, 1f);

	MainMenuWorld world;
	Array<GameButton> buttons;

	public abstract void onTouchUp(@SuppressWarnings("UnusedParameters") int screenX, @SuppressWarnings("UnusedParameters") int screenY, int pointer, @SuppressWarnings("UnusedParameters") int button);

	public abstract Model getWorld();

	public abstract void deleteButton(GameButton button);
}
