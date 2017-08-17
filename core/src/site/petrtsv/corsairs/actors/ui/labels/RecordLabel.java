package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 07.08.2017.
 * <p>
 * Record label.
 */

public class RecordLabel extends GameLabel
{
	@SuppressWarnings({"CanBeFinal", "unused", "FieldCanBeLocal"})
	private Stage world;
	@SuppressWarnings({"CanBeFinal", "unused", "FieldCanBeLocal"})
	private UIGroup group;

	@SuppressWarnings("CanBeFinal")
	private Vector2 position;
	@SuppressWarnings("CanBeFinal")
	private BitmapFont font;
	private String text;

	private Color color;


	public RecordLabel(UIGroup group, @SuppressWarnings("SameParameterValue") Color color, Color recordColor,
					   int y, int result, int record)
	{
		this.group = group;
		this.world = group.getWorld();
		this.position = new Vector2();
		this.position.y = y;
		this.color = color;
		font = FontManager.getInstance().getMainUIFont();
		if (result >= record)
		{
			this.color = recordColor;
			setText("New record!");
		} else
		{

			this.color = color;
			setText("Best: " + record);

		}
		centerX(0);
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

	@Override
	public void setColor(Color color)
	{
		this.color = color;
	}
}
