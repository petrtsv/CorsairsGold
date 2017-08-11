package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import site.petrtsv.corsairs.managers.FontManager;
import site.petrtsv.corsairs.managers.SaveManager;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 06.07.2017.
 */
public class ScoresLabel extends GameLabel
{
	GameWorld world;

	int value;
	String text;
	int record;
	Vector2 position;
	BitmapFont font;
	Color color;

	public ScoresLabel(GameWorld world, Vector2 position)
	{
		this.world = world;
		this.position = new Vector2(position);
		try
		{
			this.record = SaveManager.getInstance().getRecord();
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		color = Color.WHITE;
		font = FontManager.getInstance().getMainUIFont();

		setValue(0);
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
		this.text = "Score: " + value;
		centerX(0);
	}

	@Override
	public String getText()
	{
		return text;
	}

	@Deprecated
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
