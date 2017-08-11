package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 10.08.2017.
 */

public class ScreenHeader extends GameLabel
{
	Stage world;
	UIGroup group;

	Vector2 position;
	BitmapFont font;
	String text;

	Color color;


	public ScreenHeader(UIGroup group, int y, String text, Color color)
	{
		this.text = text;
		this.group = group;
		this.world = group.getWorld();
		this.position = new Vector2();
		this.color = color;
		this.position.y = y;
		font = FontManager.getInstance().getLargeHeaderFont();
		centerX(0);
	}

	/*public void centerX(int x)
	{
		float label_width = text.length() * CHAR_LENGTH * SCALE;
		position.set(x - label_width / 2, y);
	}*/

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		centerX(0);
	}

	@Override
	public Vector2 getPosition()
	{
		return position;
	}

	@Override
	public BitmapFont getFont()
	{
		return font;
	}

	@Override
	public Color getColor()
	{
		return color;
	}
}
