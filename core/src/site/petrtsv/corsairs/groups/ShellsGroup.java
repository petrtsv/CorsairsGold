package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

import site.petrtsv.corsairs.actors.Shell;
import site.petrtsv.corsairs.models.GameWorld;
import site.petrtsv.corsairs.pools.ShellsPool;

/**
 * Created by Петр on 10.07.2017.
 * <p>
 * Group, that control shell during the game.
 */
public class ShellsGroup extends Group implements Disposable
{
	private static final int STATES_NUM = 3;
	@SuppressWarnings("CanBeFinal")
	private GameWorld world;
	@SuppressWarnings("CanBeFinal")
	private Array<Shell> shells;
	private int state;

	public ShellsGroup(GameWorld world)
	{
		this.world = world;
		setX(0);
		setY(0);
		setZIndex(100);
		state = 0;
		shells = new Array<Shell>();
	}

	public GameWorld getWorld()
	{
		return world;
	}

	@Override
	public void dispose()
	{
		for (Shell shell : shells)
		{
			deleteShell(shell);
		}
	}

	private void deleteShell(Shell shell)
	{
		removeActor(shell);
		shells.removeValue(shell, true);
		shell.destruct();
	}

	public void spawnShell(float shellVelocity)
	{
		Vector2 direction = null;
		if (state == 0)
		{
			direction = getFirstTypeShellDirection(shellVelocity);
		} else if (state == 1)
		{
			direction = getThirdTypeShellDirection(shellVelocity);
		} else if (state == 2)
		{
			direction = getSecondTypeShellDirection(shellVelocity);
		}
		Random random = new Random();

		state = (state + (random.nextInt(100) / 50) + 1) % STATES_NUM;

		Shell shell = ShellsPool.getInstance().getShell(this, 0, 0, direction);
		addActor(shell);
	}

	private Vector2 getFirstTypeShellDirection(float shellVelocity)
	{
		float time = GameWorld.RADIUS / shellVelocity;
		Vector2 direction = new Vector2(1, 1).setLength(shellVelocity);
		float playersDelta = world.getPlayer().getRadius() * time;
		float playersAngle = world.getPlayer().getAngle();
		Random random = new Random();
		float mult = random.nextFloat() / 10.0f + 0.95f;
		direction.setAngle(playersAngle + playersDelta * mult);
		return direction;
	}

	private Vector2 getSecondTypeShellDirection(float shellVelocity)
	{
		float time = GameWorld.RADIUS / shellVelocity;
		Vector2 direction = new Vector2(1, 1).setLength(shellVelocity);
		float playersDelta = world.getPlayer().getRadius() * time;
		float playersAngle = world.getPlayer().getAngle();
		Random random = new Random();
		float mult = random.nextFloat() / 6.0f;
		direction.setAngle(playersAngle + playersDelta * mult);
		return direction;
	}

	private Vector2 getThirdTypeShellDirection(float shellVelocity)
	{
		float time = GameWorld.RADIUS / shellVelocity;
		Vector2 direction = new Vector2(1, 1).setLength(shellVelocity);
		float playersDelta = world.getPlayer().getRadius() * time;
		float playersAngle = world.getPlayer().getAngle();
		Random random = new Random();
		float mult = random.nextFloat() / 4.0f - 0.1f;
		direction.setAngle(playersAngle + playersDelta * mult);
		return direction;
	}


}
