package poo.demos.helloandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class CustomView extends View {

	private final Paint brush;
	private boolean isReversed;

	public CustomView(Context context) 
	{
		super(context);
		brush = new Paint();
		brush.setStyle(Style.FILL_AND_STROKE);
		brush.setColor(Color.RED);
		brush.setStrokeWidth(6);
		isReversed = false;
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		if(!isReversed)
			canvas.drawLine(0, 0, getWidth(), getHeight(), brush);
		else
			canvas.drawLine(getWidth(), 0, 0, getHeight(), brush);
	}
	
	public void toggleReversedState()
	{
		isReversed = !isReversed;
		invalidate();
	}
	
	public boolean getReversedState()
	{
		return isReversed;
	}
	
	public void setReversedState(boolean state)
	{
		isReversed = state;
		invalidate();
	}
}
