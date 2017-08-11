package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import site.petrtsv.corsairs.actors.BigSail;
import site.petrtsv.corsairs.groups.ResultUIGroup;
import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.screens.ResultScreen;

/**
 * Created by Петр on 29.07.2017.
 */

public class ResultWorld extends MenuWorld
{
	ResultScreen screen;
	OrthographicCamera camera;

	ShapeRenderer shapeRenderer;

	UIGroup uiGroup;
	BigSail sail;

	public ResultWorld(ResultScreen screen)
	{
		this.screen = screen;

		initializeCamera();
		setViewport(new ScalingViewport(Scaling.stretch, camera.viewportWidth,
				camera.viewportHeight, camera));
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setProjectionMatrix(getCamera().combined);

		uiGroup = new ResultUIGroup(this);
		addActor(uiGroup);
		sail = new BigSail(this, 0, 125);
		addActor(sail);

		setZIndices();
	}

	public void initializeCamera()
	{
		camera = new OrthographicCamera(screen.width, screen.height);
		camera.position.set(new Vector2(0, 0), 0);
		camera.update();
	}

	@Override
	public UIGroup getUIGroup()
	{
		return uiGroup;
	}

	public void onReplayButtonPressed()
	{
		screen.onReplayButtonPressed();
	}

	public void setZIndices()
	{
		sail.setZIndex(1);
		uiGroup.setZIndex(5);
	}

	public GameWorld getGameResult()
	{
		return screen.getGameResult();
	}

	@Override
	public ShapeRenderer getShapeRenderer()
	{
		return shapeRenderer;
	}

	@Override
	public void dispose()
	{
		super.dispose();
		uiGroup.dispose();
	}
}
