package com.litmus7.checkedexception2;

import java.io.IOException;

public class CheckedMain {
	public static void main(String[] args) {
		FileService fileService = new FileService();
		try {
			fileService.loadData();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("CheckedMain execution completed");
		}
	}
}
