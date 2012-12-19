package com.kembel.farmhand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class FarmHelper {
	
	private final static String FILE_NAME = "com.kembel.farmhand.txt";

	private FarmHelper() {}
	
	public static void writeFarms(Context context) {
		
			try {
				new RandomAccessFile(FILE_NAME, "rw" ).setLength(0);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			ObjectOutputStream outputStream = null;
			
			try {
				FileOutputStream fileOut = context.openFileOutput(FILE_NAME, Activity.MODE_PRIVATE);
				outputStream = new ObjectOutputStream(fileOut);
				
				for (Farm farm : FarmList.farms) {
					outputStream.writeObject(farm);
				}
				
				//outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void readFarms(Context context) {
		
		ObjectInputStream inputStream = null;
		
		try {
			FileInputStream fileIn = context.getApplicationContext().openFileInput(FILE_NAME);
			inputStream = new ObjectInputStream(fileIn);
			
			FarmList.farms = new ArrayList<Farm>();
			Farm farm;
			
			while ((farm = (Farm) inputStream.readObject()) != null) {
				FarmList.farms.add(farm);
			}
			
			inputStream.close();
			
			// wipes file clean
			new RandomAccessFile(FILE_NAME, "rw" ).setLength(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		/*
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		
			ObjectInputStream inputStream = null;
			try {
				inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Farm farm = new Farm();
			
			try {
				while ((farm = (Farm) inputStream.readObject()) != null) {
					FarmList.farms.add(farm);
				}
				
			} catch (OptionalDataException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}

}
