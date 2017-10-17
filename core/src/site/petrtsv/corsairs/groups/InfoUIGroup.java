package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.actors.ui.buttons.MenuButton;
import site.petrtsv.corsairs.actors.ui.labels.LowerLabel;
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
	private LowerLabel lowerLabel;

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


		String rules = "" +
				"- Touch the screen to change \n" +
				"   ship's direction.\n\n" +
				"- Avoid fireballs.\n\n" +
				"- Collect coins to increase \n" +
				"   your score.\n\n" +
				"- As the record increases, \n" +
				"   your ship changes.";
		rulesLabel = new RulesLabel(this, 225, rules, UIGroup.MAIN_UI);
		addActor(rulesLabel);

		lowerLabel = new LowerLabel(this);
		lowerLabel.setText("Good luck! ");
		lowerLabel.getPosition().y = -150;
		lowerLabel.setColor(UIGroup.SPECIAL_UI);
		addActor(lowerLabel);

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
