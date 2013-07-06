package test;

import java.io.File;
import java.io.IOException;

import logic.Generator;

import org.junit.Before;
import org.junit.Test;

import aux.TxtCommonMethods;

public class TextGeneratorTest {

	private String archiveName = "telex-free-text";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void txtTest() throws IOException {
		TxtCommonMethods.readTxtFile(new File(archiveName));
	}

	@Test
	public void generateTxtTest() throws IOException {
		Generator generator = new Generator(archiveName);
		System.out.println(generator.generateKGrama(5, 3));
	}

}
