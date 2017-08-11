package site.petrtsv.corsairs.pools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.Shell;
import site.petrtsv.corsairs.groups.ShellsGroup;
import site.petrtsv.corsairs.models.GameWorld;


/**
 * Created by Петр on 21.06.2017.
 */
public class ShellsPool
{
	public static final int MAX_SIZE = 20;
	private static ShellsPool ourInstance = new ShellsPool();
	Array<Shell> shellsArray;

	private ShellsPool()
	{
		shellsArray = new Array<Shell>();
	}

	public static ShellsPool getInstance()
	{
		return ourInstance;
	}

	public Shell getShell(ShellsGroup group, float x, float y, Vector2 direction)
	{
		if (shellsArray.size < 1)
		{
			return new Shell(group, x, y, direction);
		} else
		{
			Shell shell = shellsArray.pop();
			shell.set(group, x, y, direction);
			return shell;
		}
	}

	public void putShell(Shell shell)
	{
		shellsArray.add(shell);
	}

}
