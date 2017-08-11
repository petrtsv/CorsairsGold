package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 02.08.2017.
 */

public class ResultLabel extends GameLabel
{
	Stage world;
	UIGroup group;

	Vector2 position;
	BitmapFont font;
	String text;

	Color color;


	public ResultLabel(UIGroup group, int result, int y)
	{
		this.text = "";
		this.group = group;
		this.world = group.getWorld();
		this.position = new Vector2();
		this.position.y = y;
		this.color = Color.WHITE;
		font = FontManager.getInstance().getMainUIFont();
		setText("Result: " + result);
	}

	/*public void centerX(int x)
	{
		float label_width = text.length() * CHAR_LENGTH;
		position.set(x - label_width / 2, y);
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
