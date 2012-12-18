package com.kembel.farmhand;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class DataCollecting extends Activity {
	
    public static int index;
    
	private EditText farmName;
	private NumberPicker rowNum;
	private TextView status;
	private Button down, notDown;
	private Button save;
	
    private Farm farm;
    private State currentState;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_collecting);
        
        boolean newFarm = getIntent().getExtras().getBoolean("newFarm");
        	
        if (newFarm) {
        	farm = new Farm();
			FarmList.farms.add(farm);
			index = FarmList.farms.size() - 1;
        } else {
        	index = getIntent().getExtras().getInt("index");
        	farm = FarmList.farms.get(index);
        	
        	//Toast.makeText(DataCollecting.this, farm.getName(), Toast.LENGTH_LONG).show();
        }
              
        initializeFormComponents();
        setFormComponents();
        setListeners();  
    }
    
    private void initializeFormComponents() {
    	farmName = (EditText)findViewById(R.id.farm_name);
        status = (TextView)findViewById(R.id.current_status);
        rowNum = (NumberPicker)findViewById(R.id.row_number);

        down = (Button)findViewById(R.id.down_button);
        notDown = (Button)findViewById(R.id.not_down_button);
        save = (Button)findViewById(R.id.save);
        
        rowNum.setMinValue(1);
        rowNum.setMaxValue(1000);
        rowNum.setWrapSelectorWheel(true);
        rowNum.setOnValueChangedListener(onRowChange);
    }
    
    private void setFormComponents() {
    	// if a saved entry
    	if (index != -1) {
    		farmName.setText(farm.getName());
    		if (farm.getSize() == 0) {
    			rowNum.setValue(1);
    		} else {
        		rowNum.setValue(farm.getFirstRow());
    		}
    	}	
    }
    
    private void setListeners() {
    	 down.setOnClickListener(onDown);
         notDown.setOnClickListener(onNotDown);
         save.setOnClickListener(onSave);
    }
    
    private NumberPicker.OnValueChangeListener onRowChange = new NumberPicker.OnValueChangeListener() {
		
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			updateRowDisplay(rowNum.getValue());			
		}
		
    };
    
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
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		String name = String.valueOf(farmName.getText());

		if (name.trim().length() == 0) {
        	Toast.makeText(DataCollecting.this, "hello", Toast.LENGTH_LONG).show();

		}
		farm.setName(name);
	
		if (farm.getName().trim().length() == 0) {
			if (farm.getSize() == 0) {
				// empty entry shouldn't be saved
				// need to remove farm that was initially added in onCreate
				FarmList.farms.remove(index);
	        	Toast.makeText(DataCollecting.this, "Empty entry was not saved", Toast.LENGTH_LONG).show();	
			} else {
				Toast.makeText(DataCollecting.this, "Entry was saved with name: " + " foobar", Toast.LENGTH_LONG).show();
			}
	
		} else {
			FarmList.farms.set(index,farm);
		}
	
		FarmList.farmAdapter.notifyDataSetChanged();
		finish();
		
	}
	
	private View.OnClickListener onSave = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			String name = String.valueOf(farmName.getText());
			farm.setName(name);
		
			//if (index == -1) {
			//	FarmList.farms.add(farm);
			//} else {
				FarmList.farms.set(index,farm);
			//}
			
			FarmList.farmAdapter.notifyDataSetChanged();
			finish();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.data_collecting_options, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.view) {
			
			String name = String.valueOf(farmName.getText());
			farm.setName(name);
			
			Intent intent = new Intent(DataCollecting.this, ViewEntry.class);
			//intent.putExtra("Farm", farm);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
