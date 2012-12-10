package com.kembel.farmhand;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class EntryList extends Activity{
	
	private Farm farm;
	private ListView list;
	private ArrayAdapter<String> farmAdapter;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_farm);
		
		farm = (Farm) getIntent().getSerializableExtra("Farm");
	
		GridView rowsGrid = (GridView)findViewById(R.id.row_grid);
		rowsGrid.setAdapter(new ImageAdapter(this,farm));
		
		rowsGrid.setOnItemClickListener(onGridItemClick);
	}
	
	private OnItemClickListener onGridItemClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
			Toast.makeText(EntryList.this, "" + position++ + ": " + farm.getState(position), Toast.LENGTH_SHORT).show();
		}
		
	};
	
}

