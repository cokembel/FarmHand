package com.kembel.farmhand;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FarmListAdapter extends ArrayAdapter<Farm> {
	
	private Context context;
	public static ArrayList<Farm> farms = new ArrayList<Farm>();
	
	@SuppressWarnings("unchecked")
	public FarmListAdapter(Context ctxt, int textViewResourceId) {
		super(ctxt, textViewResourceId, farms);
		
		this.farms = (ArrayList<Farm>) farms;
	}
	
	public int getCount() {
		return farms.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	

		TextView view;
		
		if (convertView == null) {
			
			//LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//	LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		//	inflater = LayoutInflater.from(context);
			
			//itemView = inflater.inflate(R.layout.farm_list_item, parent, false);
			
			view = new TextView(context);
			
			//view.setText(farms.get(position).getName());
			
			/*
			TextView name = (TextView)itemView.findViewById(R.id.name);
			TextView numberOfRows = (TextView)itemView.findViewById(R.id.row_number);
			TextView date = (TextView)itemView.findViewById(R.id.date);
		
			name.setText(farms.get(position).getName());
			numberOfRows.setText(String.valueOf(farms.get(position).getSize()));
			
			
			ImageView = new ImageView();*/
			
		} else {
			view = (TextView) convertView;
			//itemView = (View) convertView;
		}
		
		return view;
	}
}
