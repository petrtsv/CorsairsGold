package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import site.petrtsv.corsairs.groups.UIGroup;
import site.petrtsv.corsairs.managers.FontManager;

/**
 * Created by Петр on 17.08.2017.
 * <p>
 * Small header.
 */

public class SmallHeader extends GameLabel
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

	@SuppressWarnings("CanBeFinal")
	private Color color;


	public SmallHeader(UIGroup group, @SuppressWarnings("SameParameterValue") int y, @SuppressWarnings("SameParameterValue") String text, @SuppressWarnings("SameParameterValue") Color color)
	{
		this.text = text;
		this.group = group;
		this.world = group.getWorld();
		this.position = new Vector2();
		this.color = color;
		this.position.y = y;
		font = FontManager.getInstance().getSmallHeaderFont();
		centerX(0);
	}


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
