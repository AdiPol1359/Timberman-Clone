package me.adipol.timberman;

import com.badlogic.gdx.Game;
import lombok.Getter;
import me.adipol.timberman.manager.GameManager;
import me.adipol.timberman.manager.ResourceManager;
import me.adipol.timberman.screen.GameScreen;
import me.adipol.timberman.screen.MenuScreen;
import me.adipol.timberman.screen.SplashScreen;

@Getter
public class Timberman extends Game {

	@Getter private static Timberman instance;

	private ResourceManager resourceManager;
	private GameManager gameManager;

	private SplashScreen splashScreen;
	private MenuScreen menuScreen;

	public Timberman() {
		instance = this;
	}

	@Override
	public void create () {
		resourceManager = new ResourceManager();
		gameManager = new GameManager();

		splashScreen = new SplashScreen();
		menuScreen = new MenuScreen();

		this.setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		resourceManager.dispose();
		splashScreen.dispose();
		menuScreen.dispose();
	}
}
