package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.models.MainMenuWorld;

/**
 * Created by Петр on 29.07.2017.
 */

public abstract class UIGroup extends Group  implements Disposable
{
	public static final Color MAIN_UI = Color.WHITE;
	public static final Color SPECIAL_UI = new Color(115f / 255f, 205f / 255f, 75f / 255f, 1f);

	MainMenuWorld world;
	Array<GameButton> buttons;
	public abstract void onTouchUp(int screenX, int screenY, int pointer, int button);
	public abstract Stage getWorld();
}
