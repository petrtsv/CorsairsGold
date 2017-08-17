package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.AudioManager;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.screens.AppScreen;
import site.petrtsv.corsairs.screens.GameScreen;

/**
 * Created by Петр on 29.07.2017.
 * <p>
 * Replay button on the result screen.
 */

public class ReplayButton extends site.petrtsv.corsairs.actors.ui.buttons.GameButton
{
	@SuppressWarnings("CanBeFinal")
	private TextureRegion usualRegion;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion pressedRegion;
	private ButtonState state;
	private int pointer;


	public ReplayButton(UIGroup group, @SuppressWarnings("SameParameterValue") int x, int y)
	{
		super(group, x, y);
		usualRegion = TextureManager.getInstance().getTextureRegion("replay_button");
		pressedRegion = TextureManager.getInstance().getTextureRegion("replay_button_pressed");
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
		AudioManager.getInstance().playSound("click");
		setState(ButtonState.PRESSED);
	}

	@Override
	public void onTouchUp(int pointer)
	{
		if (this.pointer == pointer)
		{
			setState(ButtonState.NOTPRESSED);
			AppScreen screen = world.getScreen();
			Screen newScreen = new GameScreen(screen.getGame(),
					screen.getWidth(), screen.getHeight());
			screen.newScreen(newScreen);
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
