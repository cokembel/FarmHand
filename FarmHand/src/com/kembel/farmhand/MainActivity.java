package com.kembel.farmhand;

import java.util.ArrayList;
import java.util.TreeSet;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText farmName, status;
	private TextView rowNum;
	private Button down, notDown;
	
	private Button prev, next;
    private Row currentRow;
    private int currentRowNum, numRowsRecorded;
    
    private TreeSet<Row> farm = new TreeSet<Row>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        currentRowNum = 0;
        numRowsRecorded = 0;
        currentRow = new Row(0,State.NOT_DOWN);
        
        farmName = (EditText)findViewById(R.id.farm_name);
        status = (EditText)findViewById(R.id.current_status);
        down = (Button)findViewById(R.id.down_button);
        notDown = (Button)findViewById(R.id.not_down_button);
        rowNum = (TextView)findViewById(R.id.row_number);
        down.setOnClickListener(onDown);
        notDown.setOnClickListener(onNotDown);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private View.OnClickListener onDown = new View.OnClickListener() {
    	
    	public void onClick(View v) {
    		setRowState(State.DOWN);
    	}
    };
    
    private View.OnClickListener onNotDown = new View.OnClickListener() {
		
		public void onClick(View v) {
			setRowState(State.NOT_DOWN);
		}
	};
	
	private void setRowState(State state){
		currentRow.setNumber(currentRowNum);
		currentRow.setState(state);
		
		// if row with current row number exists
		if (farm.contains(currentRow)) {
			// removes row with same row number
			// row may not have the same row state
			farm.remove(currentRow);
		}
		
		farm.add(currentRow);
		
		currentRowNum++;
		rowNum.setText(String.valueOf(currentRowNum));
	}
    

	

    
    
}
