package site.petrtsv.corsairs.actors.ui.labels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Петр on 10.08.2017.
 */

public abstract class GameLabel extends Actor
{
	public static final int SHADOW_OFFSET = 3;
	public static final Color SHADOW_COLOR = Color.LIGHT_GRAY;

	boolean isCenteredX = false;

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
			if (isCenteredX) {
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

	public void centerX(int x)
	{

		isCenteredX = true;
		getPosition().x = x;
	}

	public int getLabelWidth(String text)
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

	public int getShadowOffset() {
		return SHADOW_OFFSET;
	}

	public Color getShadowColor() {
		return SHADOW_COLOR;
	}

	public abstract String getText();

	public abstract void setText(String text);

	public abstract Vector2 getPosition();

	public abstract BitmapFont getFont();

	public abstract Color getColor();
}
