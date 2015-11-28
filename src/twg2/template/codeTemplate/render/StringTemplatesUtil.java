package twg2.template.codeTemplate.render;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;

import twg2.template.codeTemplate.ClassLocation;

/**
 * @author TeamworkGuy2
 * @since 2015-8-25
 */
public class StringTemplatesUtil {
	private static File rootDir = new File("");


	private StringTemplatesUtil() { throw new AssertionError("cannot instantiate static class StringTemplatesUtil"); }


	public static final ST fileTemplate(String templateFile, String templateName, TemplateImports importsConverter) {
		return fileTemplate(templateFile, templateName, importsConverter, null);
	}


	public static final ST fileTemplate(String templateFile, String templateName, TemplateImports importsConverter, STErrorListener listener) {
		return fileTemplate(templateFile, templateName, importsConverter, listener, '$', '$');
	}


	public static final ST fileTemplate(String templateFile, String templateName, TemplateImports importsConverter, STErrorListener listener, char startDelimiter, char endDelimiter) {
		String templateStr = null;
		Stream<String> linesStream = null;
		try {
			linesStream = Files.lines(Paths.get(templateFile));
			templateStr = convertImports(linesStream.iterator(), importsConverter);
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		finally {
			if(linesStream != null) {
				linesStream.close();
			}
		}

		return stringTemplate(templateFile, templateStr, templateName, importsConverter, listener, startDelimiter, endDelimiter);
	}


	public static final ST stringTemplate(String templateFile, String templateSrc, String templateName, TemplateImports importsConverter) {
		return stringTemplate(templateFile, templateSrc, templateName, importsConverter, null);
	}


	public static final ST stringTemplate(String templateFile, String templateSrc, String templateName, TemplateImports importsConverter, STErrorListener listener) {
		return stringTemplate(templateFile, templateSrc, templateName, importsConverter, listener, '$', '$');
	}


	public static final ST stringTemplate(String templateFile, String templateSrc, String templateName, TemplateImports importsConverter, STErrorListener listener, char startDelimiter, char endDelimiter) {
		List<String> srcLines = Arrays.asList(templateSrc.split("\r\n"));
		templateSrc = convertImports(srcLines.iterator(), importsConverter);

		STGroupStringFromFile stg = new STGroupStringFromFile(rootDir, new File(templateFile), templateSrc, startDelimiter, endDelimiter);

		stg.setListener(listener != null ? listener : (new STErrorListener() {

			@Override
			public void runTimeError(STMessage stErr) {
				throw new RuntimeException(stErr.toString(), stErr.cause);
			}

			@Override
			public void internalError(STMessage stErr) {
				throw new RuntimeException(stErr.toString(), stErr.cause);
			}

			@Override
			public void compileTimeError(STMessage stErr) {
				throw new RuntimeException(stErr.toString(), stErr.cause);
			}

			@Override
			public void IOError(STMessage stErr) {
				throw new RuntimeException(stErr.toString(), stErr.cause);
			}
		}));

		ST st = stg.getInstanceOf(templateName);
		return st;
	}


	public static final <T> void writeArg(ST st, Appendable out, String argName, T arg) {
		st.add(argName, arg);
		render(st, out);
		st.remove(argName);
	}


	public static final <T> void writeArgs(ST st, Appendable out, Map<String, ? extends T> args) {
		for(Map.Entry<String, ? extends T> entry : args.entrySet()) {
			st.add(entry.getKey(), entry.getValue());
		}

		render(st, out);

		for(Map.Entry<String, ? extends T> entry : args.entrySet()) {
			st.remove(entry.getKey());
		}
	}


	@SafeVarargs
	public static final <T> void writeArgs(ST st, Appendable out, Entry<String, ? extends T>... args) {
		for(Map.Entry<String, ? extends Object> arg : args) {
			st.add(arg.getKey(), arg.getValue());
		}

		render(st, out);

		for(Map.Entry<String, ? extends Object> arg : args) {
			st.remove(arg.getKey());
		}
	}


	public static final void render(ST st, Appendable out) {
		String str = st.render();
		try {
			out.append(str);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final <T extends ClassLocation> void renderClass(ST st, String paramName, T param) {
		renderClass(st, param, paramName, param);
	}


	public static final void renderClass(ST st, ClassLocation locationInfo, String paramName, Object param) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(locationInfo);
			writeArg(st, out, paramName, param);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final void renderClassArgs(ST st, ClassLocation locationInfo, Map<String, ? extends Object> args) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(locationInfo);
			writeArgs(st, out, args);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final String convertImports(Iterator<String> linesStream, TemplateImports importsConverter) {
		List<String> lines = new ArrayList<>();
		while(linesStream.hasNext()) {
			String line = linesStream.next();
			lines.add(line);
		}

		if(importsConverter != null) {
			importsConverter.replaceImportLines(lines);
		}

		String templateStr = String.join("\r\n", lines);
		return templateStr;
	}

}
