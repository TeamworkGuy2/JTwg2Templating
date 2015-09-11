package templateString;

import twg2.primitiveIoTypes.JavaPrimitive;
import codeTemplate.NameFormat;

/**
 * @author TeamworkGuy2
 * @since 2015-5-31
 */
public class TypeNameTmpl extends TemplateStringBuilder<NameFormat> implements TemplateString<JavaPrimitive> {

	public TypeNameTmpl() {
		super(NameFormat.class);
	}


	@Override
	public String toString(JavaPrimitive t) {
		StringBuilder strB = new StringBuilder();
		super.forEach((str) -> strB.append(str), (elem) -> strB.append(NameFormat.getFromPrimitiveType(elem, t)));
		return strB.toString();
	}

}
