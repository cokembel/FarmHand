package com.kembel.farmhand;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
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
	private int index;
	
	public RowAdapter(Context context, int index) {
		this.context = context;
		this.index = index;
		
		this.farm = FarmList.farms.get(index);
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
		
		Button rowButton;
		
		if (convertView == null) {
			rowButton = new Button(context);
			rowButton.setLayoutParams(new GridView.LayoutParams(85,85));
			rowButton.setPadding(2,	2, 2, 2);
		} else {
			rowButton = (Button) convertView;
		}
		position += farm.getFirstRow();
		rowButton.setText(String.valueOf(position));
		
		if (farm.getState(position) == State.DOWN) {
			rowButton.setBackgroundResource(R.drawable.green_holo);
		} else if (farm.getState(position) == State.NOT_DOWN) {
			rowButton.setBackgroundResource(R.drawable.red_holo);
		} else {
			rowButton.setBackgroundResource(R.drawable.gray_ball);
		}
		
		rowButton.setOnClickListener(onRowClick);
		
		return rowButton;
	}
	
	private View.OnClickListener onRowClick = new View.OnClickListener() {

		public void onClick(View v) {
			
			final Button rowButton = (Button) v;
			 
			new AlertDialog.Builder(context)
				.setTitle("Change State")
				.setPositiveButton("Down", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						farm.insert(Integer.valueOf((String) rowButton.getText()),State.DOWN);
						rowButton.setBackgroundResource(R.drawable.green_holo);
						if (ViewEntry.farmAdapter != null) {
							ViewEntry.farmAdapter.notifyDataSetChanged();
						}
					}
				}).setNegativeButton("Not Down", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						farm.insert(Integer.valueOf((String) rowButton.getText()),State.NOT_DOWN);
						rowButton.setBackgroundResource(R.drawable.red_holo);

						if (ViewEntry.farmAdapter != null) {
							ViewEntry.farmAdapter.notifyDataSetChanged();	
						}
					}
				}).setMessage(String.valueOf(rowButton.getText()))
				.show();
		}
		
	};

}
