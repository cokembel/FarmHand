package com.kembel.farmhand;

import java.util.ArrayList;
import java.util.TreeSet;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText farmName;
	private TextView rowNum, status;
	private Button down, notDown;
	private Button prev, next;
	
    private int currentRowNum;
    private Farm farm;
    private State currentState;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        currentRowNum = 1;
        farm = new Farm();
        
        initializeFormComponents();
        setListeners();  
    }
    
    private void initializeFormComponents() {
    	farmName = (EditText)findViewById(R.id.farm_name);
        status = (TextView)findViewById(R.id.current_status);
        rowNum = (TextView)findViewById(R.id.row_number);

        down = (Button)findViewById(R.id.down_button);
        notDown = (Button)findViewById(R.id.not_down_button);
        next = (Button)findViewById(R.id.next_row_button);
        prev = (Button)findViewById(R.id.prev_row_button);
        
        next.setBackgroundResource(R.drawable.next_arrow);
        prev.setBackgroundResource(R.drawable.prev_arrow);
    }
    
    private void setListeners() {
    	 down.setOnClickListener(onDown);
         notDown.setOnClickListener(onNotDown);
         next.setOnClickListener(onNext);
         prev.setOnClickListener(onPrev);
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
    		recordRowState(State.DOWN);
    		updateRowDisplay();
    	}
    };
    
    private View.OnClickListener onNotDown = new View.OnClickListener() {
		
		public void onClick(View v) {
			recordRowState(State.NOT_DOWN);
			updateRowDisplay();
		}
		
	};
	
	private View.OnClickListener onNext = new View.OnClickListener() {
		
		public void onClick(View v) {
			currentRowNum++;
			updateRowDisplay();
		}
		
	};
	
	
	private View.OnClickListener onPrev = new View.OnClickListener() {
		
		public void onClick(View v) {
			if (currentRowNum == 1){
				return;
			}
			
			currentRowNum--;
			updateRowDisplay();
		}
		
	};
	
	private void recordRowState(State state) {
		farm.insert(currentRowNum, state);
				
		currentRowNum++;
		rowNum.setText(String.valueOf(currentRowNum));
	}
    
	
	private void updateRowDisplay() {
		if (farm.contains(currentRowNum)) {
			currentState = farm.getState(currentRowNum);
		} else {
			currentState = State.NOT_SPECIFIED;
			farm.insert(currentRowNum, currentState);
		}
		
		if (currentState == State.DOWN) {
			status.setTextColor(Color.GREEN);
		} else if (currentState == State.NOT_DOWN) {
			status.setTextColor(Color.RED);
		} else {
			status.setTextColor(Color.BLACK);
		}
		
		// displays the appropriate row number and state
		rowNum.setText(String.valueOf(currentRowNum));
		status.setText(String.valueOf(currentState));
	}

	

    
    
}
