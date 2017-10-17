package site.petrtsv.corsairs;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

@SuppressWarnings("unused")
public class AndroidLauncher extends AndroidApplication
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i("GAMEINFO", "---1");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		RelativeLayout layout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		View gameView = initializeForView(new Corsairs(size.x, size.y), config);
		layout.addView(gameView);
		try
		{
			Log.i("GAMEINFO", "Init ads");
			MobileAds.initialize(this, "ca-app-pub-1082660632042086~4520593310");

			AdView adView = new AdView(this);
			adView.setAdSize(AdSize.BANNER);
			adView.setAdUnitId("ca-app-pub-1082660632042086/7971741409");
//			AdRequest adRequest = new AdRequest.Builder()
//					.addTestDevice("44EA965F4537E5973C615BEA03642AEB").build();
			AdRequest adRequest = new AdRequest.Builder().build();
			adView.loadAd(adRequest);

			RelativeLayout.LayoutParams adParams =
					new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT);
			adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

			layout.addView(adView, adParams);
			adView.setVisibility(View.VISIBLE);
			Log.i("GAMEINFO/Ads", "" + adView.getAdUnitId());
		} catch (Exception e)
		{
			Log.i("GAMEINFO", "ads error");
			Log.i("GAMEINFO", Log.getStackTraceString(e));
		}

		setContentView(layout);
	}
}
