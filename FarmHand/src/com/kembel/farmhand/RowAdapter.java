package com.kembel.farmhand;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RowAdapter extends BaseAdapter {
	
	private Context context;
	private Farm farm;
	
	public RowAdapter(Context context, Farm farm) {
		this.context = context;
		this.farm = farm;
	}

	public int getCount() {
		return farm.getSize();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Button rowImage;
		
		if (convertView == null) {
			rowImage = new Button(context);
			rowImage.setLayoutParams(new GridView.LayoutParams(85,85));
			rowImage.setPadding(2,	2, 2, 2);
		} else {
			rowImage = (Button) convertView;
		}
		position += farm.getFirstRow();
		rowImage.setText(String.valueOf(position));
		
		if (farm.getState(position) == State.DOWN) {
			rowImage.setBackgroundResource(R.drawable.green_holo);
		} else if (farm.getState(position) == State.NOT_DOWN) {
			rowImage.setBackgroundResource(R.drawable.red_holo);
		} else {
			rowImage.setBackgroundResource(R.drawable.gray_ball);
		}
		
		return rowImage;
	}

}
