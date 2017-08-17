package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.screens.AppScreen;

/**
 * Created by Петр on 15.08.2017.
 * <p>
 * Abstract model.
 */

public abstract class Model extends Stage
{
	AppScreen screen;
	OrthographicCamera camera;

	void initializeCamera()
	{
		camera = new OrthographicCamera(screen.getWidth(), screen.getHeight());
		camera.position.set(new Vector2(0, 0), 0);
		camera.update();
	}

	public abstract AppScreen getScreen();
}
