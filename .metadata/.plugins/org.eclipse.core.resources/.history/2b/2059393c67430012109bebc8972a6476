package com.kembel.farmhand;

import java.util.ArrayList;

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
import android.widget.ListView;
import android.widget.Toast;

public class FarmList extends ListActivity {
	
	private ListView listView;
	private ArrayList<Farm> farms = new ArrayList<Farm>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.entry_list);
				
		listView = (ListView) findViewById(android.R.id.list);
		
		listView.setAdapter(new FarmListAdapter(this, android.R.layout.simple_list_item_1, farms));
		listView.setOnItemClickListener(onItemClick);
	}

	private OnItemClickListener onItemClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getApplicationContext(),
				      "Click ListItem Number " + position, Toast.LENGTH_LONG)
				      .show();			
		}
		
	};
	
	@Override
	public void onResume() {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.options, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.add) {
			Intent intent = new Intent(FarmList.this, DataCollecting.class);
			//intent.putExtra("Farm", farm);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
