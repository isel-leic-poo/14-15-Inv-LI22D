package poo.testes;

import java.util.Random;

public class GameState {

	public static interface OnGameStateListener {
		public void onDigitsChanged(int first, int second);
	}

	private int firstDigit, secondDigit;
	private Random generator;
	private OnGameStateListener listener;
	
	private void updateScore(int count) { /* Implemented */ }
	private void nextDigits() { /* Implemented */
		firstDigit = generator.nextInt(9) + 1;
		secondDigit = generator.nextInt(9) + 1;
	}
	
	private void fireGameStateEvent(int firstDigit, int secondDigit) 
	{
		if(listener != null)
			listener.onDigitsChanged(firstDigit, secondDigit);
	}
	
	public GameState()
	{
		generator = new Random();
		nextDigits();
	}
	
	public void evenCount(int count)
	{
		updateScore(count);
		nextDigits();
		fireGameStateEvent(firstDigit, secondDigit);
	}

	public void setOnGameStateListener(OnGameStateListener listener)
	{
		this.listener = listener;
	}
	
	public int getFirstDigit()
	{
		return firstDigit;
	}

	public int getSecondDigit()
	{
		return secondDigit;
	}
}
