package com.kembel.farmhand;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewEntry extends Activity{
	
	private Farm farm;
	private ArrayAdapter<String> farmAdapter;
	private TextView farmName, firstRow, lastRow;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_farm);
		
		farm = (Farm) getIntent().getSerializableExtra("Farm");
		
		farmName = (TextView) findViewById(R.id.farm_name);
		firstRow = (TextView) findViewById(R.id.first_row_number);
		lastRow = (TextView) findViewById(R.id.last_row_number);
		
		farmName.setText(String.valueOf(farm.getName()));
		firstRow.setText(String.valueOf(farm.getFirstRow()));
		lastRow.setText(String.valueOf(farm.getLastRow()));
	
		GridView rowsGrid = (GridView)findViewById(R.id.row_grid);
		
		rowsGrid.setAdapter(new RowAdapter(this,farm));	
		rowsGrid.setOnItemClickListener(onGridItemClick);
	}
	
	private OnItemClickListener onGridItemClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
			Toast.makeText(ViewEntry.this, "" + ++position + ": " + farm.getState(position), Toast.LENGTH_SHORT).show();
		}
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.view_entry_options, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.edit) {
			finish();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
