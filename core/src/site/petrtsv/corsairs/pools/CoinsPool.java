package site.petrtsv.corsairs.pools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.Coin;
import site.petrtsv.corsairs.groups.CoinsGroup;

/**
 * Created by Петр on 06.07.2017.
 *
 * Pool, that contain unused coins.
 */
public class CoinsPool
{
	@SuppressWarnings("CanBeFinal")
	private static CoinsPool ourInstance = new CoinsPool();
	@SuppressWarnings("CanBeFinal")
	private Array<Coin> coinsArray;

	private CoinsPool()
	{
		coinsArray = new Array<Coin>();
	}

	public static CoinsPool getInstance()
	{
		return ourInstance;
	}

	public Coin getCoin(CoinsGroup group, float x, float y)
	{
		if (coinsArray.size < 1)
		{
			return new Coin(group, new Vector2(x, y));
		} else
		{
			Coin coin = coinsArray.pop();
			coin.set(group, new Vector2(x, y));
			return coin;
		}
	}

	public void putCoin(Coin coin)
	{
		coinsArray.add(coin);
	}
}
