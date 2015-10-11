package twg2.template.templates;

import java.util.List;

import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-8-24
 */
public class PrimitiveTemplateGenCode {
	public List<String> templateBuilderSetupLines;
	public PrimitiveTypeTemplate type;


	public PrimitiveTemplateGenCode(PrimitiveTypeTemplate type, List<String> lines) {
		this.type = type;
		this.templateBuilderSetupLines = lines;
	}

}
