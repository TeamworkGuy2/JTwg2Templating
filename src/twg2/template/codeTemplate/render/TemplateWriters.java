package twg2.template.codeTemplate.render;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.stringtemplate.v4.ST;

import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.TemplateNames;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveClassAndType;

/**
 * @author TeamworkGuy2
 * @since 2015-8-25
 */
public class TemplateWriters {

	private TemplateWriters() { throw new AssertionError("cannot instantiate static class TemplateWriters"); }


	// -------- String based twg2.template.templates --------
	public static final <T extends ClassInfo> void writeClassTemplate(Function<T, String> templateGenerator, T config) {
		writeClassTemplates(Arrays.asList(templateGenerator), config, "");
	}


	public static final void renderPrimitiveClassTemplates(String templateFileName, String templateName, TemplateImports importMapper,
			List<Class<?>> types, Function<Class<?>, Entry<PrimitiveClassAndType, Object>> supplier) {

		List<Entry<ClassInfo, Object>> tmplList = new ArrayList<>();

		for(Class<?> type : types) {
			Entry<PrimitiveClassAndType, Object> entry = supplier.apply(type);
			TemplateNames.inferClassNames(entry.getKey().getClassInfo(), entry.getKey().getTypeInfo());

			tmplList.add(new AbstractMap.SimpleImmutableEntry<>(entry.getKey().getClassInfo(), entry.getValue()));
		}

		ST stTmpl = STTemplates.fromFile(templateFileName, templateName, importMapper);
		TemplateRenders.renderClassTemplates(stTmpl, (e) -> e.getKey(), (e) -> {
			Map<String, Object> args = new HashMap<>();
			args.put("var", e.getValue());
			return args;
		}, tmplList);
	}


	public static final <T extends ClassInfo> void writeClassTemplates(Iterable<? extends Function<T, String>> templateGenerators, T config, String separator) {
		Writer out = null;
		try {
			out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(config);
			int count = 0;
			for(Function<T, String> template : templateGenerators) {
				if(count > 0) {
					out.write(separator);
				}
				writeClassTemplate(template, out, config);
				count++;
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			}
		}
	}


	/** Run a template generate with a given template and write it to an output stream
	 */
	public static final <T extends ClassInfo> void writeClassTemplate(Function<T, String> templateGenerator, Appendable out, T config) {
		String str = templateGenerator.apply(config);
		try {
			out.append(str);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


}
