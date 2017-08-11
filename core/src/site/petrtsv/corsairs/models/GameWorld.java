package site.petrtsv.corsairs.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import site.petrtsv.corsairs.managers.SaveManager;
import site.petrtsv.corsairs.screens.GameScreen;

/**
 * Created by Петр on 04.07.2017.
 */
public class GameWorld extends Stage
{
	public static final int RADIUS = 200;
	public static final float START_SHELL_VELOCITY = 325;
	public static final float SHELL_VELOCITY_MULT = 1.025f;
	public static final float START_SHELL_SPAWN_PERIOD = 0.325f;
	public static final float SHELL_SPAWN_PERIOD_MULT = 0.975f;

	GameScreen screen;
	OrthographicCamera camera;
	Player player;
	GameBackground background;
	Tower tower;

	ScoresLabel scoresLabel;
	ShapeRenderer shapeRenderer;

	CoinsGroup coinsGroup;
	GameUIGroup uiGroup;
	ShellsGroup shellsGroup;

	WorldState state;
	float shellSpawnTime;
	float shellVelocity;
	float shellSpawnPeriod;
	int coinsCount;
	int highscore;
	int score;

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

	public void setScore(int score)
	{
		this.score = score;
	}

	public void initializeCamera()
	{
		camera = new OrthographicCamera(screen.width, screen.height);
		camera.position.set(new Vector2(0, 0), 0);
		camera.update();
	}

	public void initializeGame()
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

	public void updateHighscore()
	{
		highscore = SaveManager.getInstance().getRecord();
	}

	public void saveHighscore(int score)
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

	public ScoresLabel getScoresLabel()
	{
		return scoresLabel;
	}

	public void onCoinCollected()
	{
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
		screen.onGameOver();
	}

	public void nextLevel()
	{
		resetField();
		shellVelocity *= SHELL_VELOCITY_MULT;
		shellSpawnPeriod *= SHELL_SPAWN_PERIOD_MULT;
	}

	public void resetField()
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

	public void setZIndices()
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

	public enum WorldState
	{
		RUNNING, PAUSED
	}


}
