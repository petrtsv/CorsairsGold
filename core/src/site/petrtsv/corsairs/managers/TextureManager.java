package site.petrtsv.corsairs.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Петр on 15.07.2017.
 * <p>
 * Class, that load texture atlases (.atls/.png) from catalog (.ctlg).
 */
public class TextureManager
{
	@SuppressWarnings("unused")
	public static final String ATLASES_CATALOG_PATH = "catalogs" +
			File.separator + "atlases_catalog.ctlg";
	@SuppressWarnings("CanBeFinal")
	private static TextureManager ourInstance = new TextureManager();
	@SuppressWarnings({"CanBeFinal", "MismatchedQueryAndUpdateOfCollection"})
	private Map<String, Texture> atlases;
	@SuppressWarnings("CanBeFinal")
	private Map<String, TextureRegion> textureRegions;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private GsonBuilder gsonBuilder;
	@SuppressWarnings("CanBeFinal")
	private Gson gson;

	private TextureManager()
	{
		gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		atlases = new HashMap<String, Texture>();
		textureRegions = new HashMap<String, TextureRegion>();
	}

	public static TextureManager getInstance()
	{
		return ourInstance;
	}

	public void loadAtlases()
	{
		try
		{
			FileHandle catalogFileHandle =
					Gdx.files.internal("").child("catalogs").child("atlases_catalog.ctlg");

			Type type = new TypeToken<Map<String, Map<String, String>[]>>()
			{
			}.getType();
			InputStreamReader reader =
					new InputStreamReader(catalogFileHandle.read());
			Map<String, Map<String, String>[]> catalogs =
					gson.fromJson(new JsonReader(reader), type);
			for (String key : catalogs.keySet())
			{
				Map<String, String>[] objects = catalogs.get(key);
				for (Map<String, String> atlasDescription : objects)
				{
					String pathToAtlas = atlasDescription.get("path").replace(" ", File.separator);
					loadAtlas(pathToAtlas);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void loadAtlas(String path)
	{
		System.out.println("Loading \"" + path + ".atls\"...");
		String pathToDescription = path + ".atls";
		String pathToTexture = path + ".png";
		FileHandle textureFileHandle = Gdx.files.internal(pathToTexture);
		Texture texture = new Texture(textureFileHandle);

		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
		atlases.put(textureFileHandle.name(), texture);
		FileHandle descriptionFileHandle = Gdx.files.internal(pathToDescription);
		loadRegions(descriptionFileHandle, texture);
		System.out.println("Complete!");
	}

	private void loadRegions(FileHandle fileHandle, Texture texture)
	{
		Type type = new TypeToken<Map<String, Object>[]>()
		{
		}.getType();
		InputStreamReader reader = new InputStreamReader(fileHandle.read());
		Map<String, Object>[] regions =
				gson.fromJson(new JsonReader(reader), type);
		for (Map<String, Object> object : regions)
		{
			String name = (String) object.get("name");
			double x = (Double) object.get("x");
			double y = (Double) object.get("y");
			double width = (Double) object.get("width");
			double height = (Double) object.get("height");
			TextureRegion region =
					new TextureRegion(texture, (int) x, (int) y, (int) width, (int) height);
			textureRegions.put(name, region);
		}
	}

	public TextureRegion getTextureRegion(String name)
	{
		return textureRegions.get(name);
	}


}
