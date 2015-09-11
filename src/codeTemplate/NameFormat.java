package codeTemplate;

import twg2.primitiveIoTypes.JavaPrimitive;


/**
 * @author TeamworkGuy2
 * @since 2015-1-18
 */
public enum NameFormat {
	SHORT,
	SHORT_UPPER,
	FULL,
	FULL_UPPER,
	JAVA_PRIMITIVE,
	JAVA_OBJECT;


	public static final String getFromPrimitiveType(NameFormat fmt, JavaPrimitive type) {
		switch(fmt) {
		case FULL:
			return type.getFullName();
		case FULL_UPPER:
			return type.getFullTitleCaseName();
		case JAVA_OBJECT:
			return type.getJavaObjectName();
		case JAVA_PRIMITIVE:
			return type.getJavaPrimitiveName();
		case SHORT:
			return type.getShortName();
		case SHORT_UPPER:
			return type.getShortTitleCaseName();
		default:
			throw new AssertionError("unrecognized enum " + fmt);
		}
	}

}
