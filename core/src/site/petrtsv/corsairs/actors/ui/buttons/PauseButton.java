package site.petrtsv.corsairs.actors.ui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import site.petrtsv.corsairs.groups.GameUIGroup;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 09.07.2017.
 *
 * Pause button.
 */
public class PauseButton extends GameButton
{
	private static final float BUTTON_RADIUS = 20;
	@SuppressWarnings("CanBeFinal")
	private GameWorld world;
	private GameUIGroup group;
	@SuppressWarnings({"unused", "FieldCanBeLocal"})
	private int pointer;

	@SuppressWarnings("unused")
	public PauseButton(GameUIGroup group, int x, int y)
	{
		this.group = group;
		this.world = group.getWorld();
		setX(x - BUTTON_RADIUS);
		setY(y - BUTTON_RADIUS);
		setSize(BUTTON_RADIUS * 2, BUTTON_RADIUS * 2);
		pointer = -1;

		setTouchable(Touchable.enabled);
	}

	@SuppressWarnings("unused")
	public PauseButton(GameWorld world, int x, int y)
	{
		this.world = world;
		setX(x - BUTTON_RADIUS);
		setY(y - BUTTON_RADIUS);
		setSize(BUTTON_RADIUS * 2, BUTTON_RADIUS * 2);

		setTouchable(Touchable.enabled);
	}


	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if (batch.isDrawing())
		{
			batch.end();
		}

		ShapeRenderer renderer = group.getWorld().getShapeRenderer();
		renderer.begin();
		renderer.set(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.DARK_GRAY);
		renderer.circle(getX() + BUTTON_RADIUS, getY() + BUTTON_RADIUS, BUTTON_RADIUS);
		renderer.end();

		if (!batch.isDrawing())
		{
			batch.begin();
		}
	}

	@Override
	public void onTouchDown(int pointer)
	{
		world.setState(GameWorld.WorldState.PAUSED);
	}

	@Override
	public void onTouchUp(int pointer)
	{

	}

	@Override
	public void destruct()
	{
		if (group != null)
		{
			group.deleteButton(this);
		}
	}
}
