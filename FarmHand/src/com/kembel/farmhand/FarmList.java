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
	
	public static FarmListAdapter farmAdapter; 
	public static ArrayList<Farm> farms = new ArrayList<Farm>();
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry_list);
				
		FarmHelper.readFarms();
		
		if (farms.size() == 0) {
			Toast.makeText(FarmList.this, "Hit 'ADD' on the action bar to create a farm entry", Toast.LENGTH_SHORT).show();
		}
		
		listView = (ListView) findViewById(android.R.id.list);
		
		farmAdapter = new FarmListAdapter();
		
		listView.setAdapter(farmAdapter);
		listView.setOnItemClickListener(onItemClick);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			FarmHelper.writeFarms();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OnItemClickListener onItemClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(FarmList.this, DataCollecting.class);
			intent.putExtra("newFarm", false);
			intent.putExtra("index", position);
			startActivity(intent);			
		}
		
	};
	
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

			name.setText(String.valueOf(farms.get(position).getName()));
			numberOfRows.setText(String.valueOf(farms.get(position).getSize()));
			
			return view;
		}
	}
	
	
}
