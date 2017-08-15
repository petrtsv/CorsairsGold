package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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
	private ResultScreen screen;
	private OrthographicCamera camera;

	@SuppressWarnings("CanBeFinal")
	private ShapeRenderer shapeRenderer;

	@SuppressWarnings("CanBeFinal")
	private UIGroup uiGroup;
	@SuppressWarnings("CanBeFinal")
	private BigSail sail;

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

	private void initializeCamera()
	{
		camera = new OrthographicCamera(screen.getWidth(), screen.getHeight());
		camera.position.set(new Vector2(0, 0), 0);
		camera.update();
	}

	@Override
	public UIGroup getUIGroup()
	{
		return uiGroup;
	}

	private void setZIndices()
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

	@Override
	public AppScreen getScreen()
	{
		return screen;
	}
}
