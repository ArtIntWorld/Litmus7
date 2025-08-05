package com.litmus7.checkedexception2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileService {
	public void loadData() throws IOException{
		System.out.println("Inside FileService.loadData");
		readFile();
	}

	private void readFile() throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("D:\\Litmus7\\exception_practice\\ExceptionHandling\\resources\\myself.txt"));
			String line = br.readLine();
			System.out.println(line);
			br.close();
		} finally {
			if(br != null) {
				br.close();
				System.out.println("BufferedReader closed in finally block");
			}
		}
	}
}

