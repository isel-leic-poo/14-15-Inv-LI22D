package poo.demos.helloandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class CustomView extends View {

	private final Paint brush;

	public CustomView(Context context) 
	{
		super(context);
		brush = new Paint();
		brush.setStyle(Style.FILL_AND_STROKE);
		brush.setColor(Color.RED);
		brush.setStrokeWidth(6);
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		canvas.drawLine(0, 0, getWidth(), getHeight(), brush);
	}
}
