package site.petrtsv.corsairs.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Петр on 14.08.2017.
 * <p>
 * Class, that load audio from catalog (.ctlg).
 */

public class AudioManager
{
	private static final String AUDIO_CATALOG_PATH = "catalogs" +
			File.separator + "audio_catalog.ctlg";
	private static final AudioManager ourInstance = new AudioManager();
	@SuppressWarnings("CanBeFinal")
	private Map<String, Sound> sounds;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private GsonBuilder gsonBuilder;
	@SuppressWarnings("CanBeFinal")
	private Gson gson;
	private float volume;

	private AudioManager()
	{
		gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		sounds = new HashMap<String, Sound>();
		volume = 1;
	}

	public static AudioManager getInstance()
	{
		return ourInstance;
	}

	public void loadSounds()
	{
		try
		{
			FileHandle catalogFileHandle = Gdx.files.internal(AUDIO_CATALOG_PATH);
			Type type = new TypeToken<Map<String, JsonArray>>()
			{
			}.getType();

			InputStreamReader reader = new InputStreamReader(catalogFileHandle.read());

			Map<String, JsonArray> object = gson.fromJson(new JsonReader(reader), type);

			type = new TypeToken<Map<String, String>[]>()
			{
			}.getType();

			Map<String, String>[] soundList = gson.fromJson(object.get("sounds"), type);

			for (Map<String, String> soundDescription : soundList)
			{
				String name = soundDescription.get("name");
				String path = soundDescription.get("path").replace(" ", File.separator);
				Sound sound = loadSound(path);
				sounds.put(name, sound);
			}


		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private Sound loadSound(String path)
	{
		System.out.println("Loading \"" + path + "...");
		FileHandle soundFileHandle = Gdx.files.internal(path);
		Sound sound = Gdx.audio.newSound(soundFileHandle);
		System.out.println("Complete!");
		return sound;
	}

	@SuppressWarnings("UnusedReturnValue")
	public long playSound(String name)
	{
		Sound sound = sounds.get(name);
		return sound.play(volume);
	}

	public float getVolume()
	{
		return volume;
	}

	public void setVolume(float volume)
	{
		this.volume = volume;
	}
}
