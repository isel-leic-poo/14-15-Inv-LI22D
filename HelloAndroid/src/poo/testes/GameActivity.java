package poo.testes;

import poo.demos.helloandroid.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity {

	private GameState model;
	private GameView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		view = (GameView) findViewById(R.id.gameView);
		model = new GameState();
		model.setOnGameStateListener(new GameState.OnGameStateListener() {
			@Override public void onDigitsChanged(int first, int second) {
				view.setDigits(first, second);
			}
		});
		
		// TODO 
		view.setDigits(model.getFirstDigit(), model.getSecondDigit());
		View.OnClickListener buttonHandler = new View.OnClickListener() {
			@Override public void onClick(View source) {
				int count = Integer.parseInt(((Button)source).getText().toString());
				model.evenCount(count);
			}
		};
		findViewById(R.id.none).setOnClickListener(buttonHandler);
		findViewById(R.id.one).setOnClickListener(buttonHandler);
		findViewById(R.id.both).setOnClickListener(buttonHandler);
	}
}
