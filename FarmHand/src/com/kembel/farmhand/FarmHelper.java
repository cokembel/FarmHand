package com.kembel.farmhand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


public class FarmHelper {
	
	private final static String FILE_NAME = "com.kembel.farmhand.txt";
	
	public static ArrayList<Farm> farms = new ArrayList<Farm>();
	
	private FarmHelper() {}
	
	public static void writeFarm(Farm farm) throws FileNotFoundException, IOException {
			ObjectOutputStream outputStream;
			outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			outputStream.writeObject(farm);
			outputStream.flush();
			outputStream.close();
	}
	
	public static ArrayList<Farm>  readFarms() {
		farms.clear();
		
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
				farms.add(farm);
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
		}
		
		return farms;
	}


}