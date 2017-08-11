package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.interfaces.Destructible;
import site.petrtsv.corsairs.groups.CoinsGroup;
import site.petrtsv.corsairs.managers.AssetManager;
import site.petrtsv.corsairs.models.GameWorld;
import site.petrtsv.corsairs.pools.CoinsPool;

/**
 * Created by Петр on 06.07.2017.
 */
public class Coin extends Actor implements Destructible
{
	public static final int COIN_SCORE = 1;
	private static final int COIN_WIDTH = 15;
	private static final int COIN_HEIGHT = 15;
	private static final int IMAGE_WIDTH = 15;
	private static final int IMAGE_HEIGHT = 15;
	private static final int MAX_WIDTH = 18;
	private static final int MAX_HEIGHT = 18;
	private static final float DESTRUCTION_TIME = 0.3f;

	private CoinsGroup group;
	private GameWorld world;
	private Vector2 position;
	private TextureRegion region;
	private Sprite sprite;

	private Rectangle hitBox;

	private CoinState state;
	private float destructionTime;
	private float alpha;
	private float widthDelta;
	private float heightDelta;

	public Coin(CoinsGroup group, Vector2 position)
	{
		this.position = new Vector2();
		hitBox = new Rectangle();
		set(group, position);

	}

	public void set(CoinsGroup group, Vector2 position)
	{
		this.group = group;
		this.world = group.getWorld();

		this.position.set(position);
		setPosition(position.x, position.y);
		region = AssetManager.getInstance().getTextureRegion("coin");
		try
		{
			sprite = new Sprite(region);
			sprite.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		hitBox.set(position.x - COIN_WIDTH / 2, position.y - COIN_HEIGHT / 2,
				COIN_WIDTH, COIN_HEIGHT);
		setState(CoinState.STATIC);
		destructionTime = 0;
		alpha = 1;
		widthDelta = 0;
		heightDelta = 0;
	}

	@Override
	public void setPosition(float x, float y)
	{
		super.setPosition(x, y);
		position.set(x, y);
	}

	@Override
	public void setX(float x)
	{
		super.setX(x);
		position.x = x;
	}

	@Override
	public void setY(float y)
	{
		super.setY(y);
		position.y = y;
	}

	@Override
	public void act(float delta)
	{
		if (state == CoinState.STATIC)
		{
			if (isCollide())
			{
				group.onCoinCollision(this);
				setState(CoinState.DISAPPEARING);
			}
		} else if (state == CoinState.DISAPPEARING)
		{
			destructionTime += delta;

			if (destructionTime >= DESTRUCTION_TIME)
			{
				destruct();
			} else
			{
				setPosition(position.x, position.y);
				alpha = Math.max(alpha - (1f / 0.25f * delta), 0);
				widthDelta += (MAX_WIDTH - IMAGE_WIDTH) / destructionTime * delta;
				heightDelta += (MAX_HEIGHT - IMAGE_HEIGHT) / destructionTime * delta;
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if (!batch.isDrawing())
		{
			batch.begin();
		}
		sprite.setSize(IMAGE_WIDTH + widthDelta, IMAGE_HEIGHT + heightDelta);
		sprite.setCenter(position.x, position.y);
		sprite.setAlpha(alpha);
		sprite.draw(batch, parentAlpha);

		//drawHitBox(batch);
	}

	public Rectangle getHitBox()
	{
		hitBox.setPosition(position.x - COIN_WIDTH / 2, position.y - COIN_HEIGHT / 2);
		return hitBox;
	}

	public void drawHitBox(Batch batch)
	{
		if (batch.isDrawing())
		{
			batch.end();
		}
		ShapeRenderer renderer = world.getShapeRenderer();
		renderer.begin();
		renderer.set(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.BLACK);
		Rectangle rectangle = getHitBox();
		renderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		renderer.end();
		if (!batch.isDrawing())
		{
			batch.begin();
		}
	}

	public boolean isCollide()
	{
		if (getHitBox().overlaps(group.getWorld().getPlayer().getHitBox()))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public void destruct()
	{
		group.deleteCoin(this);
		CoinsPool.getInstance().putCoin(this);
	}

	public CoinState getState()
	{

		return state;
	}

	public void setState(CoinState state)
	{
		this.state = state;
	}

	public enum CoinState
	{
		STATIC, DISAPPEARING
	}


}
