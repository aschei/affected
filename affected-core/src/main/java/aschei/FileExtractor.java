package aschei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts affected files from unified diff content.
 * 
 */
public class FileExtractor {

	private InputStream in;
	private Collection<String> result = new HashSet<>();
	private Pattern filenamePattern = Pattern
			.compile("(\\+\\+\\+|---) (a|b)/(.*)");

	public FileExtractor(InputStream in) {
		this.in = in;
	}

	public static void main(String[] args) throws IOException {
		try (InputStream in = args.length > 0? new FileInputStream(new File(args[0])) : System.in) {
			for (String file : new FileExtractor(in).process()) {
				System.out.println(file);
			}
		}
	}

	public Collection<String> process() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			processLine(line);
		}
		return result;
	}

	private void processLine(String line) {
		Matcher m = filenamePattern.matcher(line);
		if (m.matches()) {
			result.add(m.group(3));
		}
	}
}
