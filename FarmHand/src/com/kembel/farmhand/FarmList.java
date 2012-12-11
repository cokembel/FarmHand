package com.kembel.farmhand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FarmList extends ListActivity {
	
	private ListView listView;
	public static FarmListAdapter farmAdapter; 
	public static ArrayList<Farm> farms = new ArrayList<Farm>();

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//farms = FarmHelper.readFarms();
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.entry_list);
				
		listView = (ListView) findViewById(android.R.id.list);
		
		farmAdapter = new FarmListAdapter();
		
		listView.setAdapter(farmAdapter);
		listView.setOnItemClickListener(onItemClick);
	}

	private OnItemClickListener onItemClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getApplicationContext(),
				      farms.get(position).getName() + position, Toast.LENGTH_LONG)
				      .show();
			Intent intent = new Intent(FarmList.this, DataCollecting.class);
			intent.putExtra("newFarm", false);
			intent.putExtra("Farm", position);
			startActivity(intent);			
		}
		
	};
	
	@Override
	public void onResume() {
		super.onResume();
			//farms = FarmHelper.readFarms();
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
			intent.putExtra("newFarm", true);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public class FarmListAdapter extends ArrayAdapter<Farm> {
		
		private Context context;
		
		@SuppressWarnings("unchecked")
		public FarmListAdapter() {
			super(FarmList.this, android.R.layout.simple_list_item_1, farms);
		}
		
		public int getCount() {
			return farms.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {	

			View view;
	
			LayoutInflater inflater = getLayoutInflater();
			view = inflater.inflate(R.layout.farm_list_item, null);
			
			TextView name = (TextView)view.findViewById(R.id.name);
			TextView numberOfRows = (TextView)view.findViewById(R.id.row_number);
			TextView date = (TextView)view.findViewById(R.id.date);

			name.setText(String.valueOf(farms.get(position).getName()));
			numberOfRows.setText(String.valueOf(farms.get(position).getSize()));
			
			return view;
		}
	}
	
	
}
