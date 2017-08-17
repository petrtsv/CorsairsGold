package site.petrtsv.corsairs.models;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import site.petrtsv.corsairs.actors.Coin;
import site.petrtsv.corsairs.actors.GameBackground;
import site.petrtsv.corsairs.actors.Player;
import site.petrtsv.corsairs.actors.Tower;
import site.petrtsv.corsairs.actors.ui.labels.ScoresLabel;
import site.petrtsv.corsairs.groups.CoinsGroup;
import site.petrtsv.corsairs.groups.GameUIGroup;
import site.petrtsv.corsairs.groups.ShellsGroup;
import site.petrtsv.corsairs.managers.AudioManager;
import site.petrtsv.corsairs.managers.SaveManager;
import site.petrtsv.corsairs.screens.AppScreen;
import site.petrtsv.corsairs.screens.GameScreen;
import site.petrtsv.corsairs.screens.ResultScreen;

/**
 * Created by Петр on 04.07.2017.
 * <p>
 * Model, that contains logic of the game.
 */
public class GameWorld extends Model
{
	public static final int RADIUS = 200;
	private static final float START_SHELL_VELOCITY = 325;
	private static final float SHELL_VELOCITY_MULT = 1.025f;
	private static final float START_SHELL_SPAWN_PERIOD = 0.325f;
	private static final float SHELL_SPAWN_PERIOD_MULT = 0.975f;

	private Player player;
	private GameBackground background;
	private Tower tower;

	private ScoresLabel scoresLabel;
	@SuppressWarnings("CanBeFinal")
	private ShapeRenderer shapeRenderer;

	private CoinsGroup coinsGroup;
	private GameUIGroup uiGroup;
	private ShellsGroup shellsGroup;

	private WorldState state;
	private float shellSpawnTime;
	private float shellVelocity;
	private float shellSpawnPeriod;
	private int coinsCount;
	private int highscore;
	private int score;

	public GameWorld(GameScreen screen)
	{
		this.screen = screen;

		initializeCamera();
		setViewport(new ScalingViewport(Scaling.fillX, camera.viewportWidth, camera.viewportHeight, camera));

		initializeGame();

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setProjectionMatrix(getCamera().combined);
	}

	public int getScore()
	{
		return score;
	}

	@SuppressWarnings("unused")
	public void setScore(int score)
	{
		this.score = score;
	}


	private void initializeGame()
	{
		player = new Player(this, RADIUS, 90, new Vector2(0, 0));
		background = new GameBackground(this);
		addActor(background);
		shellSpawnTime = 0;
		shellSpawnPeriod = START_SHELL_SPAWN_PERIOD;
		shellVelocity = START_SHELL_VELOCITY;
		scoresLabel = new ScoresLabel(this, new Vector2(75, 350));
		addActor(player);
		coinsGroup = new CoinsGroup(this);
		coinsCount = coinsGroup.placeCoins();
		addActor(coinsGroup);
		uiGroup = new GameUIGroup(this);
		addActor(uiGroup);
		shellsGroup = new ShellsGroup(this);
		addActor(shellsGroup);
		tower = new Tower(this);
		addActor(tower);
		setState(WorldState.PAUSED);
		setZIndices();
		updateHighscore();
	}

	private void updateHighscore()
	{
		highscore = SaveManager.getInstance().getRecord();
	}

	private void saveHighscore(int score)
	{
		SaveManager.getInstance().saveRecord(score);
	}

	@Override
	public void act(float deltaTime)
	{
		//System.out.println("GAMEFPS: " + 1 / deltaTime + " fps");
		if (!(getState() == WorldState.PAUSED))
		{
			if (uiGroup.getLowerLabelText().equals("Tap to continue"))
			{
				uiGroup.setLowerLabelText("");
			}
			if (coinsCount == 0)
			{
				nextLevel();
				return;
			}
			shellSpawnTime += deltaTime;
			if (shellSpawnTime >= shellSpawnPeriod)
			{
				shellSpawnTime = 0;
				shellsGroup.spawnShell(shellVelocity);
			}
			super.act(deltaTime);
		} else if (getState() == WorldState.PAUSED)
		{
			uiGroup.setLowerLabelText("Tap to continue");
		}
	}

	@Override
	public void draw()
	{
		Batch batch = this.getBatch();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		scoresLabel.draw(batch, 1);
		batch.end();

		shapeRenderer.begin();
		shapeRenderer.set(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(0, 0, RADIUS);
		shapeRenderer.end();

		super.draw();
	}

	public Player getPlayer()
	{
		return player;
	}

	@SuppressWarnings("unused")
	public ScoresLabel getScoresLabel()
	{
		return scoresLabel;
	}

	public void onCoinCollected()
	{
		AudioManager.getInstance().playSound("coin_collected");
		score += Coin.COIN_SCORE;
		scoresLabel.setValue(score);
		if (score > highscore)
		{
			uiGroup.setLowerLabelText("New record!");
		}
		coinsCount--;
	}

	public void newGame()
	{
		if (score > highscore)
		{
			saveHighscore(score);
		}
		onGameOver();
	}

	private void onGameOver()
	{
		Screen newScreen = new ResultScreen(screen.getGame(), screen.getWidth(),
				screen.getHeight(), this);
		screen.newScreen(newScreen);
	}

	private void nextLevel()
	{
		AudioManager.getInstance().playSound("levelup");
		resetField();
		shellVelocity *= SHELL_VELOCITY_MULT;
		shellSpawnPeriod *= SHELL_SPAWN_PERIOD_MULT;
	}

	private void resetField()
	{
		getRoot().removeActor(player);
		getRoot().removeActor(coinsGroup);
		getRoot().removeActor(shellsGroup);
		coinsGroup.dispose();
		shellsGroup.dispose();
		coinsGroup = new CoinsGroup(this);
		coinsCount = coinsGroup.placeCoins();
		addActor(coinsGroup);
		shellsGroup = new ShellsGroup(this);
		addActor(shellsGroup);

		player = new Player(this, RADIUS, 90, new Vector2(0, 0));
		shellSpawnTime = 0;
		addActor(player);
		setState(WorldState.PAUSED);
		setZIndices();
		updateHighscore();
	}

	private void setZIndices()
	{
		background.setZIndex(0);
		coinsGroup.setZIndex(1);
		player.setZIndex(2);
		shellsGroup.setZIndex(3);
		tower.setZIndex(4);
		uiGroup.setZIndex(5);
	}

	public ShapeRenderer getShapeRenderer()
	{
		return shapeRenderer;
	}

	public WorldState getState()
	{
		return state;
	}

	public void setState(WorldState state)
	{
		this.state = state;
	}

	public void onShellCollision()
	{
		player.setState(Player.PlayerState.STATIC);
	}

	public GameUIGroup getUiGroup()
	{
		return uiGroup;
	}

	@Override
	public AppScreen getScreen()
	{
		return screen;
	}

	@SuppressWarnings("unused")
	public enum WorldState
	{
		RUNNING, PAUSED
	}


}
