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
 * The
 *
 */
public class FileExtractor {

	private InputStream in;
	private Collection<String> result = new HashSet<String>();
	private Pattern filenamePattern = Pattern
			.compile("(\\+\\+\\+|---) (a|b)/(.*)");

	public FileExtractor(InputStream in) {
		this.in = in;
	}

	public static void main(String[] args) throws IOException {
		InputStream in = System.in;
		if (args.length > 0) {
			in = new FileInputStream(new File(args[0]));
		}
		new FileExtractor(in).run();
	}

	private void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			processLine(line);
		}
		for (String file : result) {
			System.out.println(file);
		}
	}

	private void processLine(String line) {
		Matcher m = filenamePattern.matcher(line);
		if (m.matches()) {
			result.add(m.group(3));
		}
	}
}
