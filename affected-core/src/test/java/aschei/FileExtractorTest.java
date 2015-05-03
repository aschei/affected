package aschei;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class FileExtractorTest {

	@Test
	public void test() throws IOException {
		Collection<String> result = new FileExtractor(new FileInputStream(
				"target/test-classes/example.diff")).process();
		Assert.assertTrue(result.contains("affected-core/pom.xml"));
		Assert.assertTrue(result
				.contains("affected-core/src/main/java/aschei/App.java"));
		Assert.assertFalse(result.contains("affected-core/.project"));
	}

}
