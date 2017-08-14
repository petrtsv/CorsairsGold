package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by petrtsv on 22.07.2017.
 *
 * Tower in the center of the game field.
 */

public class Tower extends Actor
{
	private static final int IMAGE_WIDTH = 50;
	private static final int IMAGE_HEIGHT = 50;
	private GameWorld world;
	private TextureRegion region;
	private Sprite sprite;


	public Tower(GameWorld world)
	{
		this.world = world;

		region = TextureManager.getInstance().getTextureRegion("tower");
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
		sprite.setCenter(0, 0);
		sprite.draw(batch, parentAlpha);
	}
}
