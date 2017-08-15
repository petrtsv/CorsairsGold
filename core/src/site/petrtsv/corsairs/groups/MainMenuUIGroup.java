package site.petrtsv.corsairs.groups;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import site.petrtsv.corsairs.actors.ui.buttons.GameButton;
import site.petrtsv.corsairs.actors.ui.buttons.MusicSelector;
import site.petrtsv.corsairs.actors.ui.buttons.PlayButton;
import site.petrtsv.corsairs.actors.ui.labels.ScreenHeader;
import site.petrtsv.corsairs.models.MainMenuWorld;
import site.petrtsv.corsairs.models.Model;

/**
 * Created by Петр on 26.07.2017.
 * <p>
 * Group, that contain UI elements of main menu.
 */

public class MainMenuUIGroup extends UIGroup implements Disposable
{
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private PlayButton playButton;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private MusicSelector musicSelector;
	@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal"})
	private ScreenHeader header;

	public MainMenuUIGroup(MainMenuWorld world)
	{
		this.world = world;
		setX(0);
		setY(0);
		buttons = new Array<GameButton>();

		playButton = new PlayButton(this, 0, 0);
		buttons.add(playButton);

		musicSelector = new MusicSelector(this, -200, 350);
		buttons.add(musicSelector);

		header = new ScreenHeader(this, 300, "CORSAIRS\nGOLD", UIGroup.SPECIAL_UI);

		addActor(header);
		addActor(playButton);
		addActor(musicSelector);

		setTouchable(Touchable.enabled);
	}

	@Override
	public Model getWorld()
	{
		return world;
	}

	public void deleteButton(GameButton button)
	{
		removeActor(button);
		buttons.removeValue(button, true);
	}

	@Override
	public void onTouchUp(int screenX, int screenY, int pointer, int button)
	{
		Array.ArrayIterator<GameButton> iterator = new Array.ArrayIterator<GameButton>(buttons);
		while (iterator.hasNext())
		{
			GameButton current = iterator.next();
			current.onTouchUp(pointer);
		}
	}


	@Override
	public void dispose()
	{
		for (GameButton button : buttons)
		{
			button.destruct();
		}
	}
}
