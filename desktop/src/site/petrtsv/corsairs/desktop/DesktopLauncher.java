package site.petrtsv.corsairs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import site.petrtsv.corsairs.Corsairs;

class DesktopLauncher
{
	@SuppressWarnings("unused")
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Corsairs";
		config.resizable = false;
		config.height = 600;
		config.width = 360;
		System.setProperty("user.name", "\\xD0\\x9F\\xD0\\xB5\\xD1\\x82\\xD1\\x80");
		new LwjglApplication(new Corsairs(config.width, config.height), config);
	}
}
