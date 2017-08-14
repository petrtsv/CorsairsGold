package site.petrtsv.corsairs.managers;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.IOException;

/**
 * Created by Петр on 10.07.2017.
 *
 * Class, that access file system.
 */
class FileManager
{
	@SuppressWarnings("CanBeFinal")
	private static FileManager ourInstance = new FileManager();

	private FileManager()
	{
	}

	@SuppressWarnings("unused")
	public static FileManager getInstance()
	{
		return ourInstance;
	}

	static File getFile(@SuppressWarnings("SameParameterValue") String path)
	{
		File file = Gdx.files.local(path).file();
		if (!file.exists())
		{
			try
			{
				boolean result = file.createNewFile();
				if (!result)
				{
					return null;
				}
			} catch (IOException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
}
