package poo.demos.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView msgBox;
	private CustomView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);		
		msgBox = new TextView(this);
		msgBox.setText("SLB rules!!!");
		msgBox.setTextSize(18);
		boolean isVisible = false;
		if(savedInstanceState != null)
			isVisible = savedInstanceState.getBoolean("isVisible");
		msgBox.setVisibility(!isVisible ? View.INVISIBLE : View.VISIBLE);
		root.addView(msgBox);
		
		Button button = new Button(this); 
		button.setText("Click Me!");
		root.addView(button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View source) 
			{
				source.postDelayed(new Runnable() {
					@Override
					public void run() 
					{
						int visibility = msgBox.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE;
						msgBox.setVisibility(visibility);
					}
				}, 1000);
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
			reversedState = savedInstanceState.getBoolean("reversedState");
		myView.setReversedState(reversedState);
		root.addView(myView);
		
		setContentView(root);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		
		outState.putBoolean("isVisible", msgBox.getVisibility() == View.VISIBLE);
		outState.putBoolean("reversedState", myView.getReversedState());
	}
	
	
}
