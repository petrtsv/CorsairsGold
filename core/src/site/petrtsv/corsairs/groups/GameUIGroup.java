package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.actors.ui.labels.LowerLabel;
import site.petrtsv.corsairs.actors.ui.buttons.PauseButton;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 09.07.2017.
 */
public class GameUIGroup extends UIGroup implements Disposable
{
	GameWorld world;
	Array<GameButton> buttons;
	PauseButton pauseButton;
	LowerLabel lowerLabel;

	public GameUIGroup(GameWorld world)
	{
		this.world = world;
		setX(0);
		setY(0);
		buttons = new Array<GameButton>();
//		pauseButton = new PauseButton(this, -200, 350);
//		buttons.add(pauseButton);
//		addActor(pauseButton);
		lowerLabel = new LowerLabel(this);
		addActor(lowerLabel);
		setTouchable(Touchable.enabled);
	}

	@Override
	public GameWorld getWorld()
	{
		return world;
	}

	@Override
	public void dispose()
	{
		for (GameButton button : buttons)
		{
			button.destruct();
		}
	}

	public void deleteButton(GameButton button)
	{
		removeActor(button);
		buttons.removeValue(button, true);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable)
	{
		return super.hit(x, y, touchable);
	}

	public String getLowerLabelText()
	{
		return lowerLabel.getText();
	}

	public void setLowerLabelText(String text)
	{
		lowerLabel.setText(text);
	}

	@Override
	public void onTouchUp(int screenX, int screenY, int pointer, int button)
	{
		for (GameButton current : buttons)
		{
			current.onTouchUp(pointer);
		}
	}

}
