package edu.ucsy.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class FileTest {

	@Test
	void test() {
		try {
			var result = Files.deleteIfExists(Path.of("file", "file1.txt"));
			if(result) {
				System.out.println("File is deleted");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
