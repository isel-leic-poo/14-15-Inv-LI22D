package poo.testes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameView extends LinearLayout {

	/* Sugerir uma implementação derivando de View (como no trabalho) e assumir que o onDraw
	 já está implementado */
	private void updateView()
	{
		firstView.setText(Integer.toString(first));
		secondView.setText(Integer.toString(second));
	}
	
	public GameView(Context context) 
	{ 
		this(context, null, 0); 
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) 
	{ 
		super(context, attrs, defStyle);
	}

	public GameView(Context context, AttributeSet attrs) 
	{ 
		this(context, attrs, 0);
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.addView(firstView = new TextView(context));
		this.addView(secondView = new TextView(context));
		firstView.setTextSize(100);
		secondView.setTextSize(100);
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) firstView.getLayoutParams();
		params.setMargins(0, 0, 100, 0); 
		firstView.setLayoutParams(params);
		first = second = 0;
		updateView();
	}
	
	private TextView firstView, secondView;
	private int first, second; 
	
	public void setDigits(int first, int second)
	{
		this.first = first;
		this.second = second;
		updateView();
	}
}
