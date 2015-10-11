package twg2.template.codeTemplate.render;

import org.stringtemplate.v4.ST;

/**
 * @author TeamworkGuy2
 * @since 2015-10-10
 */
public class STTemplates {

	public static final ST fromFile(String templateFileName, String templateName, TemplateImports importMapper) {
		ST template = StringTemplatesUtil.fileTemplate(templateFileName, templateName, importMapper);
		return template;
	}


	public static final ST fromString(String templateSrcName, String templateSrc, String templateName, TemplateImports importMapper) {
		ST template = StringTemplatesUtil.stringTemplate(templateSrcName, templateSrc, templateName, importMapper);
		return template;
	}

}
