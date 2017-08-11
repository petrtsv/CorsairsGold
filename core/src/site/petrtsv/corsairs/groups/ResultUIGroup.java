package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.actors.ui.buttons.ReplayButton;
import site.petrtsv.corsairs.actors.ui.labels.RecordLabel;
import site.petrtsv.corsairs.actors.ui.labels.ResultLabel;
import site.petrtsv.corsairs.actors.ui.labels.ScreenHeader;
import site.petrtsv.corsairs.managers.SaveManager;
import site.petrtsv.corsairs.models.ResultWorld;

/**
 * Created by Петр on 29.07.2017.
 */

public class ResultUIGroup extends UIGroup
{
	ResultWorld world;
	Array<GameButton> buttons;
	ReplayButton replayButton;
	ResultLabel resultLabel;
	ScreenHeader header;
	RecordLabel recordLabel;

	public ResultUIGroup(ResultWorld world)
	{
		this.world = world;
		setX(0);
		setY(0);
		buttons = new Array<GameButton>();
		replayButton = new ReplayButton(this, 0, -200);
		int result = world.getGameResult().getScore();
		int record = SaveManager.getInstance().getRecord();
		resultLabel = new ResultLabel(this, result, 25);
		Color special = UIGroup.SPECIAL_UI;
		header = new ScreenHeader(this, 300, "GAME OVER", special);
		recordLabel = new RecordLabel(this, Color.WHITE, special, -20, result, record);
		buttons.add(replayButton);
		addActor(replayButton);
		addActor(resultLabel);
		addActor(recordLabel);
		addActor(header);
		setTouchable(Touchable.enabled);
	}

	@Override
	public ResultWorld getWorld()
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
