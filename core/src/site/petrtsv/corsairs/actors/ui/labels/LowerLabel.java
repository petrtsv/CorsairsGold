package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 23.07.2017.
 * <p>
 * Label in the bottom of screen.
 */

public class LowerLabel extends GameLabel
{
	@SuppressWarnings("unused")
	public static final int CHAR_LENGTH = 14;
	private static final int Y_COORDINATE = -250;
	@SuppressWarnings({"CanBeFinal", "unused", "FieldCanBeLocal"})
	private Stage world;
	@SuppressWarnings({"CanBeFinal", "unused", "FieldCanBeLocal"})
	private UIGroup group;

	@SuppressWarnings("CanBeFinal")
	private Vector2 position;
	@SuppressWarnings("CanBeFinal")
	private BitmapFont font;
	private String text;

	@SuppressWarnings("CanBeFinal")
	private Color color;


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
