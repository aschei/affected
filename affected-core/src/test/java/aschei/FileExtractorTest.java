package aschei;

import java.io.IOException;

import org.junit.Test;

public class FileExtractorTest {

	@Test
	public void test() throws IOException {
		FileExtractor.main(new String[]{"target/test-classes/example.diff"});
	}

}
