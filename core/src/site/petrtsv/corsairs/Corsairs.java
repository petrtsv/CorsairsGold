package site.petrtsv.corsairs;

import com.badlogic.gdx.Game;

import site.petrtsv.corsairs.managers.AudioManager;
import site.petrtsv.corsairs.managers.FontManager;
import site.petrtsv.corsairs.managers.TextureManager;
import site.petrtsv.corsairs.screens.MainMenuScreen;


public class Corsairs extends Game
{

	private static final int SCREEN_HEIGHT = 800;
	private int width = 0;

	public Corsairs(float width, float height)
	{
		super();
		this.width = (int) (width / height * SCREEN_HEIGHT);
	}

	@Override
	public void create()
	{
		TextureManager.getInstance().loadAtlasesCatalog();
		AudioManager.getInstance().loadSounds();
		FontManager.getInstance().loadFonts();
		setScreen(new MainMenuScreen(this, width, SCREEN_HEIGHT));
	}
}