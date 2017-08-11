package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;

/**
 * Created by Петр on 29.07.2017.
 */

public abstract class MenuWorld extends Stage
{
	public abstract UIGroup getUIGroup();

	public abstract ShapeRenderer getShapeRenderer();
}
