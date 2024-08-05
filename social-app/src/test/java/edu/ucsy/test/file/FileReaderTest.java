package edu.ucsy.test.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class FileReaderTest {

	@Test
	void test1() {
		try(var lines = Files.lines(Path.of("ex-source/test.txt"));) {
			lines.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test2() {
		var sb = new StringBuilder();
		try(var lines = Files.lines(Path.of("ex-source/load-user.txt"));) {
			
			for(var l : lines.toList()) {
				sb.append(l);
			}
			var sql = sb.toString();
			sb.setLength(0);
			System.out.println("Sql is " + sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
