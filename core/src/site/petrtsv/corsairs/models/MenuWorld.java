package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import site.petrtsv.corsairs.groups.UIGroup;

/**
 * Created by Петр on 29.07.2017.
 * <p>
 * Abstract model of menu.
 */

public abstract class MenuWorld extends Model
{
	UIGroup uiGroup;

	public abstract UIGroup getUIGroup();

	public abstract ShapeRenderer getShapeRenderer();
}
