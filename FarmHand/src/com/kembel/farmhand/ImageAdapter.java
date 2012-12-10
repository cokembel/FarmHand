package com.kembel.farmhand;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setPadding(2,	2, 2, 2);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			//imageView.setBackgroundColor(Color.RED);
			imageView.setImageResource(R.drawable.green_ball);
			
		} else {
			imageView = (ImageView) convertView;
		}
		
		return imageView;
	}

}
