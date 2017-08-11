package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import site.petrtsv.corsairs.groups.ResultUIGroup;
import site.petrtsv.corsairs.managers.AssetManager;
import site.petrtsv.corsairs.models.ResultWorld;

/**
 * Created by Петр on 29.07.2017.
 */

public class ReplayButton extends site.petrtsv.corsairs.actors.ui.buttons.GameButton
{
	ResultWorld world;
	ResultUIGroup group;
	TextureRegion usualRegion;
	TextureRegion pressedRegion;
	ButtonState state;
	int pointer;


	public ReplayButton(ResultUIGroup group, int x, int y)
	{
		this.group = group;
		this.world = group.getWorld();
		setX(x);
		setY(y);
		usualRegion = AssetManager.getInstance().getTextureRegion("replay_button");
		pressedRegion = AssetManager.getInstance().getTextureRegion("replay_button_pressed");
		setSize(usualRegion.getRegionWidth(), usualRegion.getRegionHeight());
		pointer = -1;

		setTouchable(Touchable.enabled);
		setState(ButtonState.NOTPRESSED);
	}


	@Override
	public void draw(Batch batch, float parentAlpha)
	{

		if (!batch.isDrawing())
		{
			batch.begin();
		}
		TextureRegion current = null;
		if (state == ButtonState.NOTPRESSED)
		{
			current = usualRegion;

		} else if (state == ButtonState.PRESSED)
		{
			current = pressedRegion;
		}
		batch.draw(current, getX() - current.getRegionWidth() / 2,
				getY() - current.getRegionHeight() / 2);
	}

	@Override
	public void onTouchDown(int pointer)
	{
		this.pointer = pointer;
		setState(ButtonState.PRESSED);
	}

	@Override
	public void onTouchUp(int pointer)
	{
		if (this.pointer == pointer)
		{
			setState(ButtonState.NOTPRESSED);
			world.onReplayButtonPressed();
			this.pointer = -1;
		}

	}

	@Override
	public Actor hit(float x, float y, boolean touchable)
	{

		return super.hit(x + getWidth() / 2, y + getHeight() / 2, touchable);
	}

	@Override
	public void destruct()
	{
		if (group != null)
		{
			group.deleteButton(this);
		}
	}

	public ButtonState getState()
	{
		return state;
	}

	public void setState(ButtonState state)
	{
		this.state = state;
	}
}
