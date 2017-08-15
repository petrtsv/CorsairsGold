package site.petrtsv.corsairs.models;

import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.screens.AppScreen;

/**
 * Created by Петр on 15.08.2017.
 * <p>
 * Abstract model.
 */

public abstract class Model extends Stage
{
	public abstract AppScreen getScreen();
}
