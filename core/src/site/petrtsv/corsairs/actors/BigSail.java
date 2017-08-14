package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.MenuWorld;

/**
 * Created by Петр on 02.08.2017.
 *
 * Sail, that appears in the center of result screen.
 */

public class BigSail extends Actor
{
	private static final int IMAGE_HEIGHT = 94;
	private static final int IMAGE_WIDTH = 132;
	private MenuWorld world;
	private Vector2 position;
	private TextureRegion region;
	private Sprite sprite;


	public BigSail(MenuWorld world, float x, float y)
	{
		this.world = world;
		this.position = new Vector2(x, y);

		region = TextureManager.getInstance().getTextureRegion("big_sail");
		try
		{
			sprite = new Sprite(region);
			sprite.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if (world.getShapeRenderer().isDrawing())
		{
			world.getShapeRenderer().end();
		}
		if (!batch.isDrawing())
		{
			batch.begin();
		}
		sprite.setCenter(position.x, position.y);
		sprite.draw(batch, parentAlpha);
	}
}
