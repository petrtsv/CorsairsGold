package site.petrtsv.corsairs.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import site.petrtsv.corsairs.managers.SaveManager;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.models.GameWorld;

/**
 * Created by Петр on 04.07.2017.
 * <p>
 * Player on the game screen.
 */
public class Player extends Actor
{
	private static final int ANGLE_VELOCITY = 75;
	private static final int PLAYERS_HEIGHT = 20;
	private static final int PLAYERS_WIDTH = 20;
	private static final int IMAGE_WIDTH = 33;
	private static final int IMAGE_HEIGHT = 40;

	@SuppressWarnings("CanBeFinal")
	private GameWorld world;
	private Vector2 position;
	@SuppressWarnings("CanBeFinal")
	private Vector2 rotationPosition;
	@SuppressWarnings("CanBeFinal")
	private Vector2 radius;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private TextureRegion region;
	private Sprite sprite;
	private float angleVelocity;
	@SuppressWarnings("FieldCanBeLocal")
	private float rotation;
	private PlayerState state;

	@SuppressWarnings("CanBeFinal")
	private Rectangle hitBox;

	public Player(GameWorld world, @SuppressWarnings("SameParameterValue") float radius, @SuppressWarnings("SameParameterValue") float angle, Vector2 rotationPosition)
	{
		this.world = world;

		angleVelocity = -ANGLE_VELOCITY;
		this.rotationPosition = rotationPosition.cpy();
		this.radius = new Vector2(1, 1).setAngle(angle).setLength(radius);
		position = new Vector2(rotationPosition.cpy().add(this.radius));
		setPosition(position.x, position.y);
		SaveManager saveManager = SaveManager.getInstance();
		region = TextureManager.getInstance().getTextureRegion(saveManager.getShipSpriteName());
		try
		{
			sprite = new Sprite(region);
			sprite.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		hitBox = new Rectangle(position.x - PLAYERS_WIDTH / 2,
				position.y - PLAYERS_HEIGHT / 2, PLAYERS_WIDTH, PLAYERS_HEIGHT);
		setState(PlayerState.ACTIVE);
	}

	@Override
	public void setPosition(float x, float y)
	{
		super.setPosition(x, y);
		position.set(x, y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if (!batch.isDrawing())
		{
			batch.begin();
		}
		rotation = (radius.angle() + 90 + (90 * angleVelocity / Math.abs(angleVelocity))) % 360;
		sprite.setOriginCenter();
		sprite.setRotation(rotation);
		sprite.setCenter(position.x, position.y);
		sprite.draw(batch, parentAlpha);
		//drawHitBox(batch);

	}

	@Override
	public void act(float deltaTime)
	{
		if (state == PlayerState.ACTIVE)
		{
			radius.setAngle((radius.angle() + angleVelocity * deltaTime) % 360);
			position = rotationPosition.cpy().add(radius);
			setPosition(position.x, position.y);
		}
	}

	public void changeDirection()
	{
		if (state == PlayerState.ACTIVE)
		{
			angleVelocity = -angleVelocity;
		}
	}

	Rectangle getHitBox()
	{
		hitBox.setCenter(position);
		hitBox.setSize(PLAYERS_WIDTH, PLAYERS_HEIGHT);
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

	public float getAngle()
	{
		return radius.angle();
	}

	@SuppressWarnings("unused")
	public int getVelocityDir()
	{
		return Float.compare(angleVelocity, 0);
	}

	public float getRadius()
	{
		return angleVelocity;
	}

	@Override
	public float getX()
	{
		return position.x;
	}

	@Override
	public void setX(float x)
	{
		super.setX(x);
		position.x = x;
	}

	@Override
	public float getY()
	{
		return position.y;
	}

	@Override
	public void setY(float y)
	{
		super.setY(y);
		position.y = y;
	}

	@SuppressWarnings("unused")
	public PlayerState getState()
	{
		return state;
	}

	public void setState(PlayerState state)
	{
		this.state = state;
	}

	@SuppressWarnings("unused")
	public enum PlayerState
	{
		ACTIVE, STATIC
	}
}
