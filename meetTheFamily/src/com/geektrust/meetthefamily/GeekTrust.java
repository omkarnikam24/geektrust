package com.geektrust.meetthefamily;

import com.geektrust.meetthefamily.model.Family;
import com.geektrust.meetthefamily.reader.InputFileReader;

public class GeekTrust {

	public static void main(String[] args) {
		Family family = new Family();
		InputFileReader fileReader = new InputFileReader();
		fileReader.readInitializerFile(family);
		try {
			fileReader.readInputFile(family, args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Empty/Invalid file location");
		}
	}
}
