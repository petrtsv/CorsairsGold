package site.petrtsv.corsairs;

import com.badlogic.gdx.Game;

import site.petrtsv.corsairs.managers.AssetManager;
import site.petrtsv.corsairs.managers.FontManager;
import site.petrtsv.corsairs.screens.MainMenuScreen;


public class Corsairs extends Game
{

	public static final int SCREEN_HEIGHT = 800;
	public int width = 0;

	public Corsairs(float width, float height)
	{
		super();
		this.width = (int) (width / height * SCREEN_HEIGHT);
	}

	@Override
	public void create()
	{
		AssetManager.getInstance().loadAtlases();
		FontManager.getInstance().loadFonts();
		setScreen(new MainMenuScreen(this, width, SCREEN_HEIGHT));
	}
}