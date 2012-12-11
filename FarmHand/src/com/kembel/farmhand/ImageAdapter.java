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

public class ImageAdapter extends BaseAdapter {
	
	private Context context;
	private Farm farm;
	
	public ImageAdapter(Context context, Farm farm) {
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
		
		rowImage.setText(String.valueOf(++position));
		
		if (farm.getState(position) == State.DOWN) {
			rowImage.setBackgroundResource(R.drawable.green_holo);
		} else if (farm.getState(position) == State.NOT_DOWN) {
			rowImage.setBackgroundResource(R.drawable.red_holo);
		} else {
			rowImage.setBackgroundResource(R.drawable.gray_ball);
		}
		
		
		//ImageView imageView;
		/*
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setPadding(2,	2, 2, 2);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.

			if (farm.getState(position + 1) == State.DOWN) {
				imageView.setImageResource(R.drawable.green_holo);
			} else if (farm.getState(position + 1) == State.NOT_DOWN) {
				imageView.setImageResource(R.drawable.red_holo);
			} else {
				imageView.setImageResource(R.drawable.gray_ball);
			}
			
		} else {
			imageView = (ImageView) convertView;
		}*/
		
		//return imageView;
		/*
		View itemView = convertView;
		
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			itemView = inflater.inflate(R.layout.grid_item, null);
			
			//TextView rowNumber = (TextView) itemView.findViewById(R.id.item_text);
			//rowNumber.setText("hdddd");
			
	
			
			ImageView imageView = (ImageView) itemView.findViewById(R.id.item_image);
			
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setPadding(2,	2, 2, 2);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			if (farm.getState(position) == State.DOWN) {
				imageView.setImageResource(R.drawable.green_holo);
			} else if (farm.getState(position) == State.NOT_DOWN) {
				imageView.setImageResource(R.drawable.red_holo);
			} else {
				imageView.setImageResource(R.drawable.gray_ball);
			}
		} else {
			itemView = convertView;
		}*/
		
		return rowImage;
	}

}
