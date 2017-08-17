package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import site.petrtsv.corsairs.groups.InfoUIGroup;
import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.screens.AppScreen;

/**
 * Created by Петр on 17.08.2017.
 * <p>
 * Model of InfoScreen.
 */

public class InfoWorld extends MenuWorld
{

	public InfoWorld(AppScreen screen)
	{
		this.screen = screen;

		initializeCamera();
		setViewport(new ScalingViewport(Scaling.stretch, camera.viewportWidth, camera.viewportHeight, camera));

		uiGroup = new InfoUIGroup(this);
		addActor(uiGroup);

		setZIndices();
	}

	public UIGroup getUIGroup()
	{
		return uiGroup;
	}

	private void setZIndices()
	{
		uiGroup.setZIndex(5);
	}

	@Override
	public ShapeRenderer getShapeRenderer()
	{
		return null;
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
