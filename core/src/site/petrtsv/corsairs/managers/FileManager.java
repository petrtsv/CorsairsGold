package site.petrtsv.corsairs.managers;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.IOException;

/**
 * Created by Петр on 10.07.2017.
 */
public class FileManager
{
	private static FileManager ourInstance = new FileManager();

	private FileManager()
	{
	}

	public static FileManager getInstance()
	{
		return ourInstance;
	}

	public static File getFile(String path)
	{
		File file = Gdx.files.local(path).file();
		if (!file.exists())
		{
			try
			{
				System.out.println(file.createNewFile());
			} catch (IOException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
}
