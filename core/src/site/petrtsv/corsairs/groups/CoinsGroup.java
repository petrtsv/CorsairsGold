package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.Coin;
import site.petrtsv.corsairs.models.GameWorld;
import site.petrtsv.corsairs.pools.CoinsPool;

/**
 * Created by Петр on 08.07.2017.
 * <p>
 * Group, that control coins during the game.
 */
public class CoinsGroup extends Group implements Disposable
{
	private static final int COINS_CNT = 38;

	@SuppressWarnings("CanBeFinal")
	private GameWorld world;

	@SuppressWarnings("CanBeFinal")
	private Array<Coin> coins;

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
		return coins.size;
	}

	public GameWorld getWorld()
	{
		return world;
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

	public void onCoinCollision(@SuppressWarnings("UnusedParameters") Coin coin)
	{
		world.onCoinCollected();

	}

}
