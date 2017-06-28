package com.sdettraining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* Read SSNs into a Collection
 * Create an account # based on:
 * 2-Digit ID + Random 4-digit# + last 4 of SSN
 * Write the result to a text file
 * Push to GitHub
 */

public class AccountApp {

	public static void main(String[] args) {
		// 1. Define filename
		String filename = "C:/Users/owner/Desktop/SDET-June2017/TestData/SSNs.txt";
		String fileOutput = "C:\\Users\\owner\\Desktop\\SDET-June2017\\TestData\\AccountNumbers.txt";
		
		// 2. Defining the local array
		ArrayList<String> ssN = new ArrayList<String>();
		
		// 3. Initializing local array by calling the sSN method, which returns a Collection
		ssN = readSsn(filename);
		// System.out.println(ssN);
		
		// 6. Create an ArrayList of AccountNumbers to export to a txt file
		ArrayList<String> accNums = new ArrayList<String>();
		
		// ----------------------- //
		
		// Iterate through the collection
		
		for (String element : ssN) {
		
			// 4. For each account, assign an account number
			System.out.println("*******\nNEW RECORD");
			System.out.println("SSN Number Read: " + element);
			// a. 2-digit Unique ID
			int twoDigitId = Account.getId();
			System.out.println("Two Digit ID: " + twoDigitId);
	
			// b. Random 4-digit number
			int randomFour = Account.getRandomNum(250, 750);
			System.out.println("Random 4-Digit # " + randomFour);
			
			// c. Last 4-digits of the SSN
			String lastFour = Account.trimSsn(element);
			System.out.println("Last 4-Digits of SSN: " + lastFour);
			
			// 5. Concatenate / Join / Merge the Pieces
			String accountNumber = Account.concatAccount(twoDigitId, randomFour, lastFour);
			System.out.println("NEW ACCOUNT CREATED: " + accountNumber);
			
			// 6a. Add Account Number to the Account Numbers List (accNums)
			accNums.add(accountNumber);
		}
		
		// 7. Write account numbers to a file -- Call Method
		writeAccountNums(fileOutput, accNums);		
	}
	
	public static void writeAccountNums(String filename, ArrayList<String> arr) {
		// Create the Java file
		File file = new File(filename);
		try {
			// Create the FileWriter
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (String accountNum : arr) {
				bw.write(accountNum);
				bw.newLine();
			}
			// Push & Close the File
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> readSsn(String filename) {
		// Define local array
		ArrayList<String> localList = new ArrayList<String>();
		// Define file
		File file = new File(filename);
		String ssn;
		try {
			// Open the file
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// As long as the reader can read
			// As long as the reader is reading empty
			// Then, the reader will pass value into ssn
			while ((ssn = br.readLine()) != null) {
				localList.add(ssn);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: FILE WAS NOT FOUND");
		} catch (IOException e) {
			System.out.println("ERROR: COULT NOT READ FILE");
		}
		return localList;
	}

}

class Account {
	private static int Id = 22;
	// private int userId;
	
	public static int getId() {
		Id++;
		// userId = Id;
		return Id;
	}

	public static int getRandomNum(int min, int max) {
		int rand = (int) ((Math.random() * ((max-min)*10)));
		rand = rand + min*10;
		return rand;
	}
	
	public static String trimSsn(String SSN) {
		return SSN.substring(5, 9);
	}
	
	public static String concatAccount(int a2, int b4, String c4) {
		String accountNum;
		accountNum = String.valueOf(a2) + String.valueOf(b4) + c4;
		return accountNum;
	}
}