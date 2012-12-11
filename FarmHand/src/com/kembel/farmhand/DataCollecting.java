package com.kembel.farmhand;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class DataCollecting extends Activity {
	
	private EditText farmName;
	private NumberPicker rowNum;
	private TextView status;
	private Button down, notDown;
	private Button viewRows, save;
	
    private Farm farm;
    private State currentState;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_collecting);
        
        farm = new Farm();
        
        initializeFormComponents();
        setListeners();  
        /*
        TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources()
        .getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec=getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources()
        .getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);*/
    }
    
    private void initializeFormComponents() {
    	farmName = (EditText)findViewById(R.id.farm_name);
        status = (TextView)findViewById(R.id.current_status);
        rowNum = (NumberPicker)findViewById(R.id.row_number);

        down = (Button)findViewById(R.id.down_button);
        notDown = (Button)findViewById(R.id.not_down_button);
        viewRows = (Button)findViewById(R.id.view_rows);
        save = (Button)findViewById(R.id.save);
        
        rowNum.setMinValue(1);
        rowNum.setMaxValue(1000);
        rowNum.setWrapSelectorWheel(true);
        rowNum.setOnValueChangedListener(onRowChange);
        
    }
    
    private void setListeners() {
    	 down.setOnClickListener(onDown);
         notDown.setOnClickListener(onNotDown);
         viewRows.setOnClickListener(onViewRows);
         save.setOnClickListener(onSave);
    }
    
    private NumberPicker.OnValueChangeListener onRowChange = new NumberPicker.OnValueChangeListener() {
		
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			updateRowDisplay(rowNum.getValue());			
		}
		
    };
    
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
    		recordRowState(rowNum.getValue(), State.DOWN);
    		updateRowDisplay(rowNum.getValue());
    	}
    	
    };
    
    private View.OnClickListener onNotDown = new View.OnClickListener() {
		
		public void onClick(View v) {
			recordRowState(rowNum.getValue(), State.NOT_DOWN);
			updateRowDisplay(rowNum.getValue());
		}
		
	};
	
	private View.OnClickListener onViewRows = new View.OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent(DataCollecting.this, ViewEntry.class);
			intent.putExtra("Farm", farm);
			startActivity(intent);
		}
		
	};
	
	private View.OnClickListener onSave = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			String name = String.valueOf(farmName.getText());
			
			if (name == null) {
				Toast.makeText(DataCollecting.this, "Please Enter Farm Name.", Toast.LENGTH_LONG).show();
			} else {
				farm.setName(name);
				finish();
			}
		}
		
	};
	
	private void recordRowState(int currentRow, State state) {
		farm.insert(currentRow, state);		
		rowNum.setValue(++currentRow);
	}
    
	
	private void updateRowDisplay(int currentRow) {
		if (farm.contains(currentRow)) {
			currentState = farm.getState(currentRow);
		} else {
			currentState = State.NOT_SPECIFIED;
			farm.insert(currentRow, currentState);
		}
		
		if (currentState == State.DOWN) {
			status.setTextColor(Color.GREEN);
		} else if (currentState == State.NOT_DOWN) {
			status.setTextColor(Color.RED);
		} else {
			status.setTextColor(Color.BLACK);
		}
		
		// displays the appropriate row number and state
		status.setText(String.valueOf(currentState));
	}
	/*
	public static class FarmHolder {
		private TextView name, numRows, date;
		
		FarmHolder(View entry) {
			name = (TextView)entry.findViewById(R.id.entry_name);
			numRows = (TextView)entry.findViewById(R.id.num_rows);
			date = (TextView)entry.findViewById(R.id.entry_date);
		}
				
	};*/
	
	

    
    
}
