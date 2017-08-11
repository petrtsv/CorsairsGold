package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 23.07.2017.
 */

public class LowerLabel extends GameLabel
{
	public static final int Y_COORDINATE = -250;
	public static final int CHAR_LENGTH = 14;

	Stage world;
	UIGroup group;

	Vector2 position;
	BitmapFont font;
	String text;

	Color color;


	public LowerLabel(UIGroup group)
	{
		this.text = "";
		this.group = group;
		this.world = group.getWorld();
		this.position = new Vector2();
		position.y = Y_COORDINATE;
		this.color = Color.WHITE;
		font = FontManager.getInstance().getMainUIFont();
		centerX(0);
	}

	/*public void centerX(int x)
	{
		float label_width = text.length() * CHAR_LENGTH;

	}*/

	@Override
	public String getText()
	{
		return text;
	}

	@Override
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
