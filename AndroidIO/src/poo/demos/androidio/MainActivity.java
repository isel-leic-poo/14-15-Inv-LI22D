package poo.demos.androidio;


import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String filename = "Table.txt";
	private ArrayList<String> table;
	
	private LinearLayout root;
	private Button newEntryButton;
	
	private void buildTableView()
	{
		 root.removeAllViews();
		 root.addView(newEntryButton);
		 for(String elem : table) 
		 {
			 String[] pair = elem.split(":");
			 String className = "android.widget." + pair[0];
			 try {
				 TextView view = (TextView) Class.forName(className).getConstructor(Context.class).newInstance(this);
				 view.setText(pair[1]);
				 root.addView(view);
			 }
			 catch(Exception e) { }
		 }
		
	}
	
	private void loadTable()
	{
		table = new ArrayList<String>();
		try {
			Scanner in = new Scanner(this.openFileInput(filename));
			String line;
			while((line = in.nextLine()) != null) 
				table.add(line);
			in.close();
			
		} 
		catch (FileNotFoundException e) { }
		catch (NoSuchElementException nsee) { }
	}
	
	private void saveTable()
	{
		try {
			PrintStream out = new PrintStream(this.openFileOutput(filename, MODE_PRIVATE));
			for(String elem : table)
				out.println(elem.trim());
			out.close();
			
		} catch (FileNotFoundException e) { }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		 super.onCreate(savedInstanceState);
		 loadTable();
		 root = new LinearLayout(this);
		 root.setOrientation(LinearLayout.VERTICAL);
		 newEntryButton = new Button(this);
		 newEntryButton.setText("New entry");
		 newEntryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
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
							table.add(playerNameView.getText().toString());
							saveTable();
							buildTableView();
						}
					})
					.setNegativeButton("Cancel", null)
					.create()
					.show();
			}
		 });
		 
		 root.addView(newEntryButton);
		 buildTableView();
		 setContentView(root);
	}
}
