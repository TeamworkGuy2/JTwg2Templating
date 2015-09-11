package codeTemplate.render;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.misc.STMessage;

import codeTemplate.ClassLocation;

/**
 * @author TeamworkGuy2
 * @since 2015-8-25
 */
public class StringTemplatesUtil {

	private StringTemplatesUtil() { throw new AssertionError("cannot instantiate static class StringTemplatesUtil"); }


	public static final ST createTemplate(String templateFile, String templateName) {
		return createTemplate(templateFile, templateName, null);
	}


	public static final ST createTemplate(String templateFile, String templateName, STErrorListener listener) {
		return createTemplate(templateFile, templateName, listener, '$', '$');
	}


	public static final ST createTemplate(String templateFile, String templateName, STErrorListener listener, char startDelimiter, char endDelimiter) {
		STGroupFile stg = new STGroupFile(templateFile, startDelimiter, endDelimiter);

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


	public static final <T> void writeVarTemplate(ST st, Appendable out, T config) {
		st.add("var", config);
		render(st, out);
		st.remove("var");
	}


	public static final <T> void writeArgsTemplate(ST st, Appendable out, Map<String, ? extends T> args) {
		for(Map.Entry<String, ? extends T> entry : args.entrySet()) {
			st.add(entry.getKey(), entry.getValue());
		}

		render(st, out);

		for(Map.Entry<String, ? extends T> entry : args.entrySet()) {
			st.remove(entry.getKey());
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


	public static final <T extends ClassLocation> void renderClassTemplate(ST st, T config) {
		renderClassTemplate(st, config, config);
	}


	public static final void renderClassTemplate(ST st, ClassLocation locationInfo, Object config) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(locationInfo);
			writeVarTemplate(st, out, config);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final void renderClassTemplateArgs(ST st, ClassLocation locationInfo, Map<String, ? extends Object> args) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(locationInfo);
			writeArgsTemplate(st, out, args);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
