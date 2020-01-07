package com.geektrust.meetthefamily.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.geektrust.meetthefamily.GeekTrust;
import com.geektrust.meetthefamily.model.Family;
import com.geektrust.meetthefamily.processor.FamilyTreeProcessor;

public class InputFileReader {

	/**
	 * @param family
	 */
	public void readInitializerFile(Family family) {
		try (Scanner fileReader = new Scanner(
				new File(GeekTrust.class.getClassLoader().getResource("initialFamilyTree.txt").getFile()))) {
			FamilyTreeProcessor processor = new FamilyTreeProcessor();
			while (fileReader.hasNextLine()) {
				String command = fileReader.nextLine();
				processor.processCommand(family, command);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Initializer file not found in resources folder");
		}
	}

	/**
	 * @param family
	 * @param fileName
	 */
	public void readInputFile(Family family, String fileName) {
		try (Scanner fileReader = new Scanner(new File(fileName))) {
			FamilyTreeProcessor processor = new FamilyTreeProcessor();
			while (fileReader.hasNextLine()) {
				String command = fileReader.nextLine();
				System.out.println(processor.processCommand(family, command));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
