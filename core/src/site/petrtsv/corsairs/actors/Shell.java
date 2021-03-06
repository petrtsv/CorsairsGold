package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.actors.interfaces.Destructible;
import site.petrtsv.corsairs.groups.ShellsGroup;
import site.petrtsv.corsairs.managers.AudioManager;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.GameWorld;
import site.petrtsv.corsairs.pools.ShellsPool;

/**
 * Created by Петр on 04.07.2017.
 * <p>
 * Shell on the game screen.
 */
public class Shell extends Actor implements Destructible
{
	private static final int SHELL_WIDTH = 15;
	private static final int SHELL_HEIGHT = 15;
	private static final int IMAGE_WIDTH = 15;
	private static final int IMAGE_HEIGHT = 22;
	private static final float EXPLOSION_SCALE = 0.75f;
	private static final float DESTRUCTION_TIME = 1f / 8f;


	@SuppressWarnings("CanBeFinal")
	private Animation<TextureRegion> explosionAnimation;
	private GameWorld world;
	@SuppressWarnings("unused")
	private ShellsGroup group;
	private Vector2 velocity;
	@SuppressWarnings("CanBeFinal")
	private Vector2 position;
	@SuppressWarnings("CanBeFinal")
	private TextureRegion region;
	private Sprite sprite;
	private float rotation;
	private float destructionTime;

	@SuppressWarnings("CanBeFinal")
	private Rectangle hitBox;
	private ShellState state;

	public Shell(ShellsGroup group, float x, float y, Vector2 direction)
	{
		this.group = group;
		this.world = group.getWorld();
		position = new Vector2();
		region = TextureManager.getInstance().getTextureRegion("fire_shell");
		try
		{
			sprite = new Sprite(region);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		TextureRegion[] explosionRegions = new TextureRegion[3];
		for (int i = 0; i < 3; i++)
		{
			explosionRegions[i] = TextureManager.getInstance().getTextureRegion("explosion" + i);
		}
		explosionAnimation = new Animation<TextureRegion>(DESTRUCTION_TIME / 3f, explosionRegions);
		hitBox = new Rectangle();
		set(group, x, y, direction);
	}

	public void set(ShellsGroup group, float x, float y, Vector2 direction)
	{
		this.group = group;
		this.world = group.getWorld();
		position.set(x, y);
		velocity = direction;
		rotation = 0;
		setX(x);
		setY(y);
		setSize(SHELL_WIDTH, SHELL_HEIGHT);
		hitBox.setCenter(position);
		hitBox.setSize((SHELL_WIDTH + SHELL_HEIGHT) / 2);
		setState(ShellState.FLYING);
		sprite.setRegion(region);
		sprite.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		sprite.setScale(1);
		destructionTime = 0;
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
	public void draw(Batch batch, float parentAlpha)
	{
		if (!batch.isDrawing())
		{
			batch.begin();
		}
		if (state == ShellState.FLYING)
		{
			rotation = (velocity.angle() + 90) % 360;
			sprite.setOriginCenter();
			sprite.setRotation(rotation);
			sprite.setCenter(position.x, position.y);
			sprite.draw(batch, parentAlpha);
		} else if (state == ShellState.EXPLOSION)
		{
			TextureRegion current = explosionAnimation.getKeyFrame(destructionTime);
			sprite.setRegion(current);
			sprite.setSize(current.getRegionWidth() * EXPLOSION_SCALE,
					current.getRegionHeight() * EXPLOSION_SCALE);
			sprite.setOriginCenter();
			sprite.setRotation(0);
			sprite.setCenter(position.x, position.y);
			sprite.draw(batch, parentAlpha);
		}

		//drawHitBox(batch);
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);
		if (state == ShellState.FLYING)
		{
			position.add(velocity.cpy().scl(delta));
			setPosition(position.x, position.y);
			if (velocity.len() > Math.sqrt(240 * 240 + 400 * 400))
			{
				destruct();
			} else if (isCollide())
			{
				AudioManager.getInstance().playSound("explosion");
				setPosition(world.getPlayer().getX(), world.getPlayer().getY());
				world.onShellCollision();
				setState(ShellState.EXPLOSION);
			}
		} else if (state == ShellState.EXPLOSION)
		{
			destructionTime += delta;
			if (destructionTime > DESTRUCTION_TIME)
			{
				world.newGame();
				destruct();
			}
		}
	}

	@SuppressWarnings("unused")
	public Vector2 getVelocity()
	{
		return velocity;
	}

	@SuppressWarnings("unused")
	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}

	private Rectangle getHitBox()
	{
		hitBox.setCenter(position);
		return hitBox;
	}

	@SuppressWarnings("unused")
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

	private boolean isCollide()
	{
		return getHitBox().overlaps(world.getPlayer().getHitBox());
	}

	public void destruct()
	{
		ShellsPool.getInstance().putShell(this);
	}

	@SuppressWarnings("unused")
	public ShellState getState()
	{

		return state;
	}

	private void setState(ShellState state)
	{
		this.state = state;
	}

	@SuppressWarnings("unused")
	private enum ShellState
	{
		FLYING, EXPLOSION
	}


}
