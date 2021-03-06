package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import site.petrtsv.corsairs.actors.BigSail;
import site.petrtsv.corsairs.groups.ResultUIGroup;
import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.screens.AppScreen;
import site.petrtsv.corsairs.screens.ResultScreen;

/**
 * Created by Петр on 29.07.2017.
 * <p>
 * Model, that represents result screen.
 */

public class ResultWorld extends MenuWorld
{

	@SuppressWarnings("CanBeFinal")
	private BigSail sail;

	@SuppressWarnings("CanBeFinal")
	private int result;

	public ResultWorld(ResultScreen screen)
	{
		this.screen = screen;
		this.result = screen.getGameResult();

		initializeCamera();
		setViewport(new ScalingViewport(Scaling.stretch, camera.viewportWidth,
				camera.viewportHeight, camera));

		uiGroup = new ResultUIGroup(this, getGameResult());
		addActor(uiGroup);
		sail = new BigSail(this, 0, 125);
		addActor(sail);

		setZIndices();
	}

	@Override
	public UIGroup getUIGroup()
	{
		return uiGroup;
	}

	@Override
	public ShapeRenderer getShapeRenderer()
	{
		return null;
	}

	private void setZIndices()
	{
		sail.setZIndex(1);
		uiGroup.setZIndex(5);
	}

	private int getGameResult()
	{
		return result;
	}

	@Override
	public void dispose()
	{
		super.dispose();
		uiGroup.dispose();
	}

	@Override
	public AppScreen getScreen()
	{
		return screen;
	}
}
