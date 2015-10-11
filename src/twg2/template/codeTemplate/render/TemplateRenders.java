package twg2.template.codeTemplate.render;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.stringtemplate.v4.ST;

import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.ClassLocation;

/** Static utility methods for creating and rendering {@code ANTLR StringTemplates}
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
public final class TemplateRenders {


	private TemplateRenders() { throw new AssertionError("cannot instantiate static class TemplateRenders_"); }


	// -------- ClassInfo renderers --------
	@SafeVarargs
	public static final void renderClassTemplates(ST stTmpl, ClassInfo... configs) {
		for(ClassInfo config : configs) {
			StringTemplatesUtil.renderClassTemplate(stTmpl, TemplateRenderBuilder.getDefaultArgName(), config);
		}
	}


	public static final void renderClassTemplates(ST stTmpl, Iterable<? extends ClassInfo> configs) {
		for(ClassInfo config : configs) {
			StringTemplatesUtil.renderClassTemplate(stTmpl, TemplateRenderBuilder.getDefaultArgName(), config);
		}
	}


	public static final void renderClassTemplate(ST stTmpl, ClassInfo config) {
		try {
			Writer out = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(config);
			StringTemplatesUtil.writeArgTemplate(stTmpl, out, TemplateRenderBuilder.getDefaultArgName(), config);
			out.close();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static final <T> void renderClassTemplates(ST stTmpl, Function<T, ClassLocation> toLocation, Function<T, Map<String, ? extends Object>> toArgs, List<T> templates) {
		for(T template : templates) {
			StringTemplatesUtil.renderClassTemplate(stTmpl, toLocation.apply(template), TemplateRenderBuilder.getDefaultArgName(), toArgs.apply(template));
		}
	}

}
