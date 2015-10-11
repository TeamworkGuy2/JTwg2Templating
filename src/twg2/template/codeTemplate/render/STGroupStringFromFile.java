package twg2.template.codeTemplate.render;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;

import org.stringtemplate.v4.STGroupString;

public class STGroupStringFromFile extends STGroupString {
	private URL rootDir;


	public STGroupStringFromFile(File rootDir, File sourceFile, String text, char delimiterStartChar, char delimiterStopChar) {
		super(sourceFile.getName(), text, delimiterStartChar, delimiterStopChar);
		try {
			this.rootDir = rootDir.toURI().toURL();
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	@Override
	public URL getRootDirURL() {
		return rootDir;
	}

}
