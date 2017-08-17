package site.petrtsv.corsairs.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import site.petrtsv.corsairs.groups.UIGroup;

/**
 * Created by Петр on 07.08.2017.
 * <p>
 * Class, that load fonts.
 */

public class FontManager
{
	private static final FontManager ourInstance = new FontManager();
	@SuppressWarnings("FieldCanBeLocal")
	private final String ROBOTO_BOLD_PATH = "fonts/roboto_bold.ttf";

	//private final String KENPIXEL_BLOCKS_PATH = "fonts/kenpixel_blocks.ttf";

	@SuppressWarnings("FieldCanBeLocal")
	private final String RALEWAY_BOLD_PATH = "fonts/raleway_bold.ttf";
	private BitmapFont mainUI;
	private BitmapFont largeHeader;
	private BitmapFont smallHeader;


	private FontManager()
	{
	}

	public static FontManager getInstance()
	{
		return ourInstance;
	}

	public void loadFonts()
	{
		System.out.println("Loading fonts...");
		FreeTypeFontGenerator generator =
				new FreeTypeFontGenerator(Gdx.files.internal(ROBOTO_BOLD_PATH));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 28;
		mainUI = generator.generateFont(parameter);
		mainUI.setColor(UIGroup.MAIN_UI);
		mainUI.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		generator.dispose();

		generator = new FreeTypeFontGenerator(Gdx.files.internal(RALEWAY_BOLD_PATH));

		parameter.size = 56;
		largeHeader = generator.generateFont(parameter);
		largeHeader.setColor(UIGroup.MAIN_UI);
		largeHeader.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);

		parameter.size = 44;
		smallHeader = generator.generateFont(parameter);
		smallHeader.setColor(UIGroup.MAIN_UI);
		smallHeader.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		generator.dispose();

		System.out.println("Complete!");
	}

	public BitmapFont getMainUIFont()
	{
		return mainUI;
	}

	public BitmapFont getLargeHeaderFont()
	{
		return largeHeader;
	}

	public BitmapFont getSmallHeaderFont()
	{
		return smallHeader;
	}
}
