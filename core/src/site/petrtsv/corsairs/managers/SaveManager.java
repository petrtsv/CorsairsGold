package site.petrtsv.corsairs.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Петр on 10.07.2017.
 * <p>
 * Class, that save and load game data.
 */
public class SaveManager
{
	@SuppressWarnings("CanBeFinal")
	private static SaveManager ourInstance = new SaveManager();
	private String SHIP_SPRITE_NAME_PREFIX = "pirate_ship_0";
	private int MAX_SHIP_NUM = 5;

	private SaveManager()
	{
	}

	public static SaveManager getInstance()
	{
		return ourInstance;
	}

	public int getRecord()
	{
		File recordFile = FileManager.getFile("record.val");
		try
		{
			Scanner scanner;
			if (recordFile != null)
			{
				scanner = new Scanner(recordFile);
			} else
			{
				return -1;
			}
			int record = scanner.nextInt();
			scanner.close();
			return record;
		} catch (FileNotFoundException e)
		{
			return -1;
		} catch (NoSuchElementException e)
		{
			return 0;
		}
	}

	public void saveRecord(int record)
	{
		File recordFile = FileManager.getFile("record.val");
		if (recordFile != null)
		{
			try
			{
				PrintWriter writer = new PrintWriter(recordFile);
				writer.print(record);
				writer.close();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	public String getShipSpriteName()
	{
		int record = getRecord();
		return SHIP_SPRITE_NAME_PREFIX + Math.min(MAX_SHIP_NUM, record / (36 * 2));
	}
}
