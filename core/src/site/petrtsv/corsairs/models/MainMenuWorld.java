package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import site.petrtsv.corsairs.groups.MainMenuUIGroup;
import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.screens.AppScreen;
import site.petrtsv.corsairs.screens.MainMenuScreen;

/**
 * Created by Петр on 26.07.2017.
 *
 * Model of the main menu.
 */

public class MainMenuWorld extends MenuWorld
{
	@SuppressWarnings("CanBeFinal")
	private MainMenuScreen screen;
	private OrthographicCamera camera;

	@SuppressWarnings("CanBeFinal")
	private ShapeRenderer shapeRenderer;

	@SuppressWarnings("CanBeFinal")
	private UIGroup uiGroup;

	public MainMenuWorld(MainMenuScreen screen)
	{
		this.screen = screen;

		initializeCamera();
		setViewport(new ScalingViewport(Scaling.stretch, camera.viewportWidth, camera.viewportHeight, camera));

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setProjectionMatrix(getCamera().combined);

		uiGroup = new MainMenuUIGroup(this);
		addActor(uiGroup);
		setZIndices();
	}

	private void initializeCamera()
	{
		camera = new OrthographicCamera(screen.width, screen.height);
		camera.position.set(new Vector2(0, 0), 0);
		camera.update();
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
