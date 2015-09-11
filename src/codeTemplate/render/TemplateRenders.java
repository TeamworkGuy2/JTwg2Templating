package codeTemplate.render;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;

import codeTemplate.ClassInfo;
import codeTemplate.ClassLocation;

/** Static utility methods for creating and rendering {@code ANTLR StringTemplates}
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
public final class TemplateRenders {


	private TemplateRenders() { throw new AssertionError("cannot instantiate static class TemplateRenders"); }


	public static final void writeVarTemplate(String templateFile, String templateName, Appendable output, Object config) {
		ST st = StringTemplatesUtil.createTemplate(templateFile, templateName);
		StringTemplatesUtil.writeVarTemplate(st, output, config);
	}


	@SafeVarargs
	public static final void writeWithParameters(String fileName, String templateName, Appendable output, Map.Entry<String, ? extends Object>... params) {
		ST st = StringTemplatesUtil.createTemplate(fileName, templateName);
		for(Map.Entry<String, ? extends Object> param : params) {
			st.add(param.getKey(), param.getValue());
		}

		StringTemplatesUtil.render(st, output);

		for(Map.Entry<String, ? extends Object> param : params) {
			st.remove(param.getKey());
		}
	}


	// -------- ClassInfo renderers --------
	@SafeVarargs
	public static final void renderClassTemplates(String templateFile, String templateName, ClassInfo... configs) {
		ST tmpl = StringTemplatesUtil.createTemplate(templateFile, templateName);
		for(ClassInfo config : configs) {
			StringTemplatesUtil.renderClassTemplate(tmpl, config);
		}
	}


	public static final void renderClassTemplates(String templateFile, String templateName, Collection<? extends ClassInfo> configs) {
		ST tmpl = StringTemplatesUtil.createTemplate(templateFile, templateName);
		for(ClassInfo config : configs) {
			StringTemplatesUtil.renderClassTemplate(tmpl, config);
		}
	}


	public static final void renderClassTemplate(String templateFile, String templateName, ClassInfo config) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(config);
			TemplateRenders.writeVarTemplate(templateFile, templateName, out, config);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final void renderClassTemplateEntries(String templateFile, String templateName, List<? extends Map.Entry<? extends ClassLocation, ? extends Object>> configs) {
		ST tmpl = StringTemplatesUtil.createTemplate(templateFile, templateName);
		for(Map.Entry<? extends ClassLocation, ? extends Object> config : configs) {
			StringTemplatesUtil.renderClassTemplate(tmpl, config.getKey(), config.getValue());
		}
	}


	public static final void renderClassTemplateVarsEntries(String templateFile, String templateName, List<? extends Map.Entry<? extends ClassLocation, ? extends Map<String, ? extends Object>>> configVars) {
		ST tmpl = StringTemplatesUtil.createTemplate(templateFile, templateName);
		for(Map.Entry<? extends ClassLocation, ? extends Map<String, ? extends Object>> config : configVars) {
			StringTemplatesUtil.renderClassTemplateArgs(tmpl, config.getKey(), config.getValue());
		}
	}

}
