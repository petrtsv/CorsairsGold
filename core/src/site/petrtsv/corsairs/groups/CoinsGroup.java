package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.Coin;
import site.petrtsv.corsairs.models.GameWorld;
import site.petrtsv.corsairs.pools.CoinsPool;

/**
 * Created by Петр on 08.07.2017.
 */
public class CoinsGroup extends Group implements Disposable
{
	public static final int COINS_CNT = 38;

	GameWorld world;

	Array<Coin> coins;

	public CoinsGroup(GameWorld world)
	{
		this.world = world;
		setX(0);
		setY(0);
		setZIndex(50);
		coins = new Array<Coin>();
	}

	public int placeCoins()
	{
		float delta = 360.0f / (COINS_CNT + 1);
		Vector2 polarCoord = new Vector2(1, 1).setAngle(90 + delta).setLength(GameWorld.RADIUS);
		for (int i = 0; i < COINS_CNT; i++)
		{
			Coin coin = CoinsPool.getInstance().getCoin(this, polarCoord.x, polarCoord.y);
			addActor(coin);
			coins.add(coin);
			polarCoord.setAngle(polarCoord.angle() + delta);
		}
		return COINS_CNT;
	}

	public GameWorld getWorld()
	{
		return world;
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
	}

	@Override
	public void dispose()
	{
		for (Coin coin : coins)
		{
			coin.destruct();
		}
	}

	public void deleteCoin(Coin coin)
	{
		removeActor(coin);
		coins.removeValue(coin, true);
	}

	public void onCoinCollision(Coin coin) {
		world.onCoinCollected();

	}

}
