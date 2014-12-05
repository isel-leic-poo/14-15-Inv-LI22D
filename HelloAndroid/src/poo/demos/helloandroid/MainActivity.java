package poo.demos.helloandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String MSG_BOX_STATE_KEY = "msgBoxState";
	private static final String GAME_STATE_KEY = "reversedState";
	
	private TextView msgBox;
	private CustomView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);		
		msgBox = new TextView(this);
		msgBox.setTextSize(18);
		String msgBoxState = "";
		if(savedInstanceState != null)
			msgBoxState = savedInstanceState.getString(MSG_BOX_STATE_KEY);
		msgBox.setText(msgBoxState);
		
		root.addView(msgBox);
		
		Button button = new Button(this); 
		button.setText("Click Me!");
		root.addView(button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View source) 
			{
				final EditText playerNameView = new EditText(MainActivity.this);
				
				new AlertDialog.Builder(MainActivity.this)
					.setTitle("New entry")
					.setMessage("Player name ?")
					.setView(playerNameView)
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							playerNameView.postDelayed(new Runnable() {
								@Override
								public void run() 
								{
									msgBox.setText(playerNameView.getText());
								}
							}, 1000);
						}
					})
					.setNegativeButton("Cancel", null)
					.create()
					.show();
				
				/*
				*/
			}
		});
		
		Button nextButton = new Button(this); 
		nextButton.setText("Next");
		root.addView(nextButton);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View source) 
			{
				Intent nextIntent = new Intent(MainActivity.this, poo.demos.helloandroid.OtherActivity.class);
				startActivity(nextIntent);
			}
		});
		
		myView = new CustomView(this);
		myView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View source, MotionEvent event) 
			{
				if(event.getPointerCount() == 1 && event.getAction() == MotionEvent.ACTION_DOWN)
				{
					StringBuilder builder = new StringBuilder();
					builder.append("X: ").append(event.getX());
					builder.append("; Y: ").append(event.getY());
					((CustomView) source).toggleReversedState();
					Toast.makeText(MainActivity.this, builder, Toast.LENGTH_SHORT).show();
					return true;
				}
				return false;
			}
		});
		boolean reversedState = false;
		if(savedInstanceState != null)
			reversedState = savedInstanceState.getBoolean(GAME_STATE_KEY);
		myView.setReversedState(reversedState);
		root.addView(myView);
		
		setContentView(root);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		
		outState.putString(MSG_BOX_STATE_KEY, msgBox.getText().toString());
		outState.putBoolean(GAME_STATE_KEY, myView.getReversedState());
	}
	
	
}
