package twg2.template.templateString;

import twg2.primitiveIoTypes.JPrimitiveType;
import twg2.template.codeTemplate.NameFormat;

/**
 * @author TeamworkGuy2
 * @since 2015-5-31
 */
public class TypeNameTmpl extends TemplateStringBuilder<NameFormat> implements TemplateString<JPrimitiveType> {

	public TypeNameTmpl() {
		super(NameFormat.class);
	}


	@Override
	public String toString(JPrimitiveType t) {
		StringBuilder strB = new StringBuilder();
		super.forEach((str) -> strB.append(str), (elem) -> strB.append(NameFormat.getFromPrimitiveType(elem, t)));
		return strB.toString();
	}

}
