package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import site.petrtsv.corsairs.groups.MainMenuUIGroup;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.MainMenuWorld;

/**
 * Created by Петр on 25.07.2017.
 *
 * Play button.
 */

public class PlayButton extends GameButton
{
	@SuppressWarnings("CanBeFinal")
	private MainMenuWorld world;
	@SuppressWarnings("CanBeFinal")
	private MainMenuUIGroup group;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion usualRegion;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion pressedRegion;
	private ButtonState state;
	private int pointer;


	public PlayButton(MainMenuUIGroup group, @SuppressWarnings("SameParameterValue") int x, @SuppressWarnings("SameParameterValue") int y)
	{
		this.group = group;
		this.world = group.getWorld();
		setX(x);
		setY(y);
		usualRegion = TextureManager.getInstance().getTextureRegion("play_button");
		pressedRegion = TextureManager.getInstance().getTextureRegion("play_button_pressed");
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
		if (current != null)
		{
			batch.draw(current, getX() - current.getRegionWidth() / 2,
					getY() - current.getRegionHeight() / 2);
		}
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
			world.onPlayButtonPressed();
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

	@SuppressWarnings("unused")
	public ButtonState getState()
	{
		return state;
	}

	private void setState(ButtonState state)
	{
		this.state = state;
	}
}
