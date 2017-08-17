package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 22.07.2017.
 * <p>
 * Background of the game screen: isle and circle.
 */

public class GameBackground extends Actor
{
	private static final int ISLE_WIDTH = 100;
	private static final int ISLE_HEIGHT = 100;
	@SuppressWarnings("CanBeFinal")
	private GameWorld world;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private TextureRegion isleRegion;
	private Sprite isleSprite;


	public GameBackground(GameWorld world)
	{
		this.world = world;

		isleRegion = TextureManager.getInstance().getTextureRegion("isle");
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
