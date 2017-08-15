package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.AudioManager;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.Model;

/**
 * Created by Петр on 15.08.2017.
 * <p>
 * Music control elements.
 */

public class MusicSelector extends GameButton
{
	@SuppressWarnings({"CanBeFinal", "unused", "FieldCanBeLocal"})
	private Model world;
	@SuppressWarnings("CanBeFinal")
	private UIGroup group;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion usualRegion;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion pressedRegion;
	private ButtonState state;


	public MusicSelector(UIGroup group, @SuppressWarnings("SameParameterValue") int x, @SuppressWarnings("SameParameterValue") int y)
	{
		this.group = group;
		this.world = group.getWorld();
		setX(x);
		setY(y);
		usualRegion = TextureManager.getInstance().getTextureRegion("audio_on");
		pressedRegion = TextureManager.getInstance().getTextureRegion("audio_off");
		setSize(usualRegion.getRegionWidth(), usualRegion.getRegionHeight());

		setTouchable(Touchable.enabled);
		if (AudioManager.getInstance().getVolume() == 0)
		{
			setState(ButtonState.PRESSED);
		} else
		{
			setState(ButtonState.NOTPRESSED);
		}
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
		AudioManager.getInstance().playSound("click");
		if (getState() == ButtonState.NOTPRESSED)
		{
			AudioManager.getInstance().setVolume(0);
			setState(ButtonState.PRESSED);
		} else if (getState() == ButtonState.PRESSED)
		{
			AudioManager.getInstance().setVolume(1);
			setState(ButtonState.NOTPRESSED);
		}
	}

	@Override
	public void onTouchUp(int pointer)
	{

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
	private ButtonState getState()
	{
		return state;
	}

	private void setState(ButtonState state)
	{
		this.state = state;
	}
}
