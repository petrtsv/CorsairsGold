package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Петр on 10.08.2017.
 *
 * Abstract label.
 */

abstract class GameLabel extends Actor
{
	private static final int SHADOW_OFFSET = 3;
	private static final Color SHADOW_COLOR = Color.LIGHT_GRAY;

	private boolean isCenteredX = false;

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		BitmapFont font = getFont();

		String[] lines = getText().split("\n");
		float currentY = getPosition().y;

		int shadowOffset = getShadowOffset();
		Color shadowColor = getShadowColor();

		for (String line : lines)
		{
			int offsetX = 0;
			if (isCenteredX)
			{
				offsetX = -(getLabelWidth(line) / 2);
			}
			font.setColor(shadowColor);
			font.draw(batch, line,
					getPosition().x + offsetX + shadowOffset, currentY - shadowOffset);

			font.setColor(getColor());
			font.draw(batch, line, getPosition().x + offsetX, currentY);

			currentY -= font.getLineHeight();
		}
	}

	void centerX(@SuppressWarnings("SameParameterValue") int x)
	{

		isCenteredX = true;
		getPosition().x = x;
	}

	private int getLabelWidth(String text)
	{
		int labelWidth = 0;
		if (text.contains("\n"))
		{
			String[] lines = text.split("\n");
			int maxLength = -1;
			for (String line : lines)
			{
				if (line.length() > maxLength)
				{
					maxLength = line.length();
					text = line;
				}
			}
		}
		int length = text.length();


		BitmapFont font = getFont();
		for (int i = 0; i < length; i++)
		{
			labelWidth += font.getData().getGlyph(text.charAt(i)).xadvance;
		}
		for (int i = 0; i < length - 1; i++)
		{
			BitmapFont.Glyph current = font.getData().getGlyph(text.charAt(i));
			labelWidth += current.getKerning(text.charAt(i + 1));
		}
		labelWidth += getShadowOffset();

		return labelWidth;
	}

	@SuppressWarnings("SameReturnValue")
	private int getShadowOffset()
	{
		return SHADOW_OFFSET;
	}

	@SuppressWarnings("SameReturnValue")
	private Color getShadowColor()
	{
		return SHADOW_COLOR;
	}

	protected abstract String getText();

	@SuppressWarnings("unused")
	public abstract void setText(String text);

	protected abstract Vector2 getPosition();

	protected abstract BitmapFont getFont();

	public abstract Color getColor();
}
