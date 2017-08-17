package site.petrtsv.corsairs.pools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import site.petrtsv.corsairs.actors.Shell;
import site.petrtsv.corsairs.groups.ShellsGroup;


/**
 * Created by Петр on 21.06.2017.
 * <p>
 * Class, that stores unused shells.
 */
public class ShellsPool
{
	@SuppressWarnings("unused")
	public static final int MAX_SIZE = 20;
	@SuppressWarnings("CanBeFinal")
	private static ShellsPool ourInstance = new ShellsPool();
	@SuppressWarnings("CanBeFinal")
	private Array<Shell> shellsArray;

	private ShellsPool()
	{
		shellsArray = new Array<Shell>();
	}

	public static ShellsPool getInstance()
	{
		return ourInstance;
	}

	public Shell getShell(ShellsGroup group, @SuppressWarnings("SameParameterValue") float x, @SuppressWarnings("SameParameterValue") float y, Vector2 direction)
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
