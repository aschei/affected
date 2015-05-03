package aschei;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileExtractorTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	@Test
	public void test() throws IOException {
		FileExtractor.main(new String[]{"target/test-classes/example.diff"});
		Assert.assertTrue("Errors occured", errContent.size()==0);
		String result = outContent.toString();
		Assert.assertTrue(result.contains("affected-core/pom.xml"));
		Assert.assertTrue(result.contains("affected-core/src/main/java/aschei/App.java"));
		Assert.assertFalse(result.contains("affected-core/.project"));
	}

}
