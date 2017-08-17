package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.actors.ui.buttons.MenuButton;
import site.petrtsv.corsairs.actors.ui.labels.RulesLabel;
import site.petrtsv.corsairs.actors.ui.labels.SmallHeader;
import site.petrtsv.corsairs.models.Model;

/**
 * Created by Петр on 17.08.2017.
 * <p>
 * Group, that contains UI elements on the InfoScreen.
 */

public class InfoUIGroup extends UIGroup
{
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private MenuButton menuButton;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private SmallHeader header;

	@SuppressWarnings({"FieldCanBeLocal", "CanBeFinal"})
	private RulesLabel rulesLabel;

	public InfoUIGroup(Model world)
	{
		this.world = world;
		setX(0);
		setY(0);
		buttons = new Array<GameButton>();

		menuButton = new MenuButton(this, 0, -250);
		buttons.add(menuButton);
		addActor(menuButton);

		header = new SmallHeader(this, 350, "CORSAIRS\nGOLD", UIGroup.SPECIAL_UI);
		addActor(header);


		String rules = "Rules of the game:\n\n" +
				"1) Touch the screen to change \n" +
				"   ship's direction.\n\n" +
				"2) Avoid fireballs.\n\n" +
				"3) Collect coins.";
		rulesLabel = new RulesLabel(this, 225, rules, UIGroup.MAIN_UI);
		addActor(rulesLabel);

		setTouchable(Touchable.enabled);
	}

	@Override
	public Model getWorld()
	{
		return world;
	}

	public void deleteButton(GameButton button)
	{
		removeActor(button);
		buttons.removeValue(button, true);
	}

	@Override
	public void onTouchUp(int screenX, int screenY, int pointer, int button)
	{
		Array.ArrayIterator<GameButton> iterator = new Array.ArrayIterator<GameButton>(buttons);
		while (iterator.hasNext())
		{
			GameButton current = iterator.next();
			current.onTouchUp(pointer);
		}
	}


	@Override
	public void dispose()
	{
		for (GameButton button : buttons)
		{
			button.destruct();
		}
	}
}
