package poo.demos.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView msgBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);		
		msgBox = new TextView(this);
		msgBox.setText("SLB rules!!!");
		msgBox.setTextSize(18);
		msgBox.setVisibility(View.INVISIBLE);
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
		
		CustomView myView = new CustomView(this);
		myView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View source, MotionEvent event) 
			{
				if(event.getPointerCount() == 1 && event.getAction() == MotionEvent.ACTION_DOWN)
				{
					StringBuilder builder = new StringBuilder();
					builder.append("X: ").append(event.getX());
					builder.append("; Y: ").append(event.getY());
					Log.v("MyAPP", builder.toString());
					
					return true;
				}
				return false;
			}
		});
		root.addView(myView);
		
		setContentView(root);
	}
}
