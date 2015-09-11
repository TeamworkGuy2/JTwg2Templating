package codeTemplate.render;

import java.util.HashMap;
import java.util.Map;

import org.stringtemplate.v4.ST;

import codeTemplate.ClassInfo;
import codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-6-6
 */
public class TemplateRenderBuilder {
	private static String defaultSourceParamName = "var";
	private ST template;
	private Map<String, Object> params = new HashMap<>();
	private Appendable dst;


	public TemplateRenderBuilder() {
	}


	public ST getTemplate() {
		return template;
	}


	public void setTemplateFromFile(String templateFileName, String templateName) {
		this.template = StringTemplatesUtil.createTemplate(templateFileName, templateName);
	}


	public TemplateRenderBuilder setTemplate(ST template) {
		this.template = template;
		return this;
	}


	public Object getSourceParam() {
		return params.get(defaultSourceParamName);
	}


	public TemplateRenderBuilder setSourceParam(Object src) {
		this.addParam(defaultSourceParamName, src);
		return this;
	}


	public Object getParam(String paramName) {
		return this.params.get(paramName);
	}


	public Map<String, Object> getParams() {
		return this.params;
	}


	public TemplateRenderBuilder addParam(String name, Object param) {
		this.params.put(name, param);
		return this;
	}


	public TemplateRenderBuilder setParams(Map<String, Object> params) {
		this.params = params;
		return this;
	}


	public Appendable getDst() {
		return dst;
	}


	public TemplateRenderBuilder setDst(Appendable dst) {
		this.dst = dst;
		return this;
	}


	public void render() {
		params.forEach(template::add);

		StringTemplatesUtil.render(template, dst);

		params.keySet().forEach(template::remove);
	}


	public static <T extends ClassInfo> TemplateRenderBuilder fromClassTemplate(T tmpl) {
		return new TemplateRenderBuilder().setSourceParam(tmpl);
	}


	public static <T extends PrimitiveTypeClassTemplate> TemplateRenderBuilder fromPrimitiveClassTemplate(T tmpl) {
		return new TemplateRenderBuilder().setSourceParam(tmpl);
	}

}
