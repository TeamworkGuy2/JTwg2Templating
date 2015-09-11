package codeTemplate.render;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import codeTemplate.ClassInfo;
import codeTemplate.TemplateNames;
import codeTemplate.primitiveTemplate.PrimitiveClassAndType;

/**
 * @author TeamworkGuy2
 * @since 2015-8-25
 */
public class TemplateRendersUtil {

	private TemplateRendersUtil() { throw new AssertionError("cannot instantiate static class TemplateRendersUtil"); }


	// -------- String based templates --------
	public static final <T extends ClassInfo> void generateClassTemplate(Function<T, String> templateGenerator, T config) {
		generateClassTemplates(Arrays.asList(templateGenerator), config, "");
	}


	public static final void generatePrimitiveClassTemplates(String templateFileName, String templateName, List<Class<?>> types, Function<Class<?>, Map.Entry<PrimitiveClassAndType, Object>> supplier) {
		List<Map.Entry<ClassInfo, Object>> tmplList = new ArrayList<>();
		for(Class<?> type : types) {
			Map.Entry<PrimitiveClassAndType, Object> entry = supplier.apply(type);
			TemplateNames.inferClassNames(entry.getKey().getClassInfo(), entry.getKey().getTypeInfo());

			tmplList.add(new AbstractMap.SimpleImmutableEntry<>(entry.getKey().getClassInfo(), entry.getValue()));
		}
		TemplateRenders.renderClassTemplateEntries(templateFileName, templateName, tmplList);
	}


	public static final <T extends ClassInfo> void generateClassTemplates(Iterable<? extends Function<T, String>> templateGenerators, T config, String separator) {
		Writer out = null;
		try {
			out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(config);
			int count = 0;
			for(Function<T, String> template : templateGenerators) {
				if(count > 0) {
					out.write(separator);
				}
				generateClassTemplate(template, out, config);
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
	public static final <T extends ClassInfo> void generateClassTemplate(Function<T, String> templateGenerator, Appendable out, T config) {
		String str = templateGenerator.apply(config);
		try {
			out.append(str);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


}
