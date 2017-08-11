package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.managers.AssetManager;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 22.07.2017.
 */

public class GameBackground extends Actor
{
	public static final int ISLE_WIDTH = 100;
	public static final int ISLE_HEIGHT = 100;
	GameWorld world;
	private TextureRegion isleRegion;
	private Sprite isleSprite;


	public GameBackground(GameWorld world)
	{
		this.world = world;

		isleRegion = AssetManager.getInstance().getTextureRegion("isle");
		try
		{
			isleSprite = new Sprite(isleRegion);
			isleSprite.setSize(ISLE_WIDTH, ISLE_HEIGHT);
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
		isleSprite.setCenter(0, 0);
		isleSprite.draw(batch, parentAlpha);
	}
}
