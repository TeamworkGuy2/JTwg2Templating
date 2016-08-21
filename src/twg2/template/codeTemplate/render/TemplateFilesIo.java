package twg2.template.codeTemplate.render;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Getter;
import lombok.Setter;
import twg2.template.codeTemplate.ClassLocation;

/**
 * @author TeamworkGuy2
 * @since 2015-8-25
 */
public class TemplateFilesIo {
	private static TemplateFilesIo writeThrough = new TemplateFilesIo(true);
	private static TemplateFilesIo compareWritten = new TemplateFilesIo(false);
	private static @Getter @Setter boolean globalWriteFilesFlag = true;


	public static final TemplateFilesIo getDefaultInst() {
		if(globalWriteFilesFlag) {
			return writeThrough;
		}
		else {
			return compareWritten;
		}
	}


	public static final TemplateFilesIo getValidationInst() {
		return compareWritten;
	}


	private boolean writeFiles;


	private TemplateFilesIo(boolean writeFiles) {
		this.writeFiles = writeFiles;
	}


	public Path getSrcRelativePath(ClassLocation t) {
		return getSrcRelativePath(t.getPackageName(), t.getClassName(), ".java");
	}


	public Path getSrcRelativePath(String pkgName, String className, String fileExt) {
		return Paths.get("src/" + pkgName.replace('.', '/') + '/' + className + fileExt);
	}


	public Writer getSrcRelativeStream(ClassLocation t) {
		return getSrcRelativeStream(getSrcRelativePath(t));
	}


	public Writer getSrcRelativeStream(Path path) {
		if(this.writeFiles) {
			return getSrcRelativePlainStream(path);
		}
		else {
			return getSrcRelativeValidationStream(path);
		}
	}


	private Writer getSrcRelativePlainStream(Path path) {
		Writer out = null;
		try {
			out = new FileWriter(path.toFile());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return out;
	}


	private Writer getSrcRelativeValidationStream(Path path) {
		StringBuilder dstBuf = new StringBuilder();
		Writer out = new Writer() {
			@Override
			public void write(char[] cbuf, int off, int len) {
				dstBuf.append(cbuf, off, len);
			}

			@Override
			public void flush() {
			}

			@Override
			public void close() {
				String str = dstBuf.toString();
				compareFileToString(path, str);
			}
		};
		return out;
	}


	public void compareFileToString(Path path, CharSequence string) {
		int bufSize = 4096;
		FileReader reader = null;
		try {
			reader = new FileReader(path.toString());
			StringBuilder strB = new StringBuilder(bufSize);

			char[] cbuf = new char[bufSize];
			int read = 0;
			while((read = reader.read(cbuf, 0, bufSize)) > -1) {
				strB.append(cbuf, 0, read);
			}
			String fileContents = strB.toString();
			if(!string.equals(fileContents)) {
				throw new RuntimeException("file '" + path + "' did not equal input string, length file=" + fileContents.length() + ", input string=" + string.length());
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			}
		}
		
	}

}
