package codeTemplate.primitiveTemplate;

import twg2.primitiveIoTypes.JPrimitiveType;
import codeTemplate.ClassInfo;
import codeTemplate.ClassTemplate;

/** Basic {@link ClassTemplate} templates for primitive Java types.<br>
 * e.g. boolean, byte, char, etc.
 * @author TeamworkGuy2
 * @since 2014-12-30
 */
@javax.annotation.Generated("StringTemplate")
public final class PrimitiveTemplates {

	private PrimitiveTemplates() { throw new AssertionError("cannot instantiate static class PrimitiveTemplates"); }


	// boolean templates
	public static final PrimitiveTypeClassTemplate newBooleanTemplate(String className) {
		return newBooleanTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newBooleanTemplate(String className, String packageName) {
		return newBooleanTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newBooleanTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupBooleanTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupBooleanTemplate(T t) {
		setTypeInfo(t, JPrimitiveType.BOOLEAN);
		t.setDefaultValue("false");
		t.setAggregatable(false);
		return t;
	}


	// byte templates
	public static final PrimitiveTypeClassTemplate newByteTemplate(String className) {
		return newByteTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newByteTemplate(String className, String packageName) {
		return newByteTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newByteTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupByteTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupByteTemplate(T t) {
		t.setAverageType("float");
		t.setDefaultValue("(byte)0");
		t.setAggregatable(true);
		t.setMaxValue("Byte.MAX_VALUE");
		t.setMinValue("Byte.MIN_VALUE");
		t.setSumType("int");
		setTypeInfo(t, JPrimitiveType.BYTE);
		return t;
	}


	// short templates
	public static final PrimitiveTypeClassTemplate newShortTemplate(String className) {
		return newShortTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newShortTemplate(String className, String packageName) {
		return newShortTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newShortTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupShortTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupShortTemplate(T t) {
		t.setAverageType("float");
		t.setDefaultValue("(short)0");
		t.setAggregatable(true);
		t.setMaxValue("Short.MAX_VALUE");
		t.setMinValue("Short.MIN_VALUE");
		t.setSumType("int");
		setTypeInfo(t, JPrimitiveType.SHORT);
		return t;
	}


	// char templates
	public static final PrimitiveTypeClassTemplate newCharTemplate(String className) {
		return newCharTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newCharTemplate(String className, String packageName) {
		return newCharTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newCharTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupCharTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupCharTemplate(T t) {
		t.setAverageType("float");
		t.setDefaultValue("(char)0");
		t.setAggregatable(true);
		t.setMaxValue("Character.MAX_VALUE");
		t.setMinValue("Character.MIN_VALUE");
		t.setSumType("int");
		setTypeInfo(t, JPrimitiveType.CHAR);
		return t;
	}


	// int templates
	public static final PrimitiveTypeClassTemplate newIntTemplate(String className) {
		return newIntTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newIntTemplate(String className, String packageName) {
		return newIntTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newIntTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupIntTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupIntTemplate(T t) {
		t.setAverageType("float");
		t.setDefaultValue("0");
		t.setAggregatable(true);
		t.setMaxValue("Integer.MAX_VALUE");
		t.setMinValue("Integer.MIN_VALUE");
		t.setSumType("int");
		setTypeInfo(t, JPrimitiveType.INT);
		return t;
	}


	// float templates
	public static final PrimitiveTypeClassTemplate newFloatTemplate(String className) {
		return newFloatTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newFloatTemplate(String className, String packageName) {
		return newFloatTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newFloatTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupFloatTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupFloatTemplate(T t) {
		t.setAverageType("float");
		t.setDefaultValue("0f");
		t.setAggregatable(true);
		t.setMaxValue("Float.MAX_VALUE");
		t.setMinValue("Float.MIN_VALUE");
		t.setSumType("float");
		setTypeInfo(t, JPrimitiveType.FLOAT);
		return t;
	}


	// long templates
	public static final PrimitiveTypeClassTemplate newLongTemplate(String className) {
		return newLongTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newLongTemplate(String className, String packageName) {
		return newLongTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newLongTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupLongTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupLongTemplate(T t) {
		t.setAverageType("double");
		t.setDefaultValue("0L");
		t.setAggregatable(true);
		t.setMaxValue("Long.MAX_VALUE");
		t.setMinValue("Long.MIN_VALUE");
		t.setSumType("long");
		setTypeInfo(t, JPrimitiveType.LONG);
		return t;
	}


	// double templates
	public static final PrimitiveTypeClassTemplate newDoubleTemplate(String className) {
		return newDoubleTemplate(className, null);
	}


	public static final PrimitiveTypeClassTemplate newDoubleTemplate(String className, String packageName) {
		return newDoubleTemplate(new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T newDoubleTemplate(T t, String className, String packageName) {
		setupClassTemplate(t, className, packageName);
		setupDoubleTemplate(t);
		return t;
	}


	public static final <T extends PrimitiveTypeInfo> T setupDoubleTemplate(T t) {
		t.setAverageType("double");
		t.setDefaultValue("0");
		t.setAggregatable(true);
		t.setMaxValue("Double.MAX_VALUE");
		t.setMinValue("Double.MIN_VALUE");
		t.setSumType("double");
		setTypeInfo(t, JPrimitiveType.DOUBLE);
		return t;
	}


	public static final PrimitiveTypeClassTemplate ofType(Class<?> primitiveType, String className) {
		return ofType(primitiveType, className, null);
	}


	public static final PrimitiveTypeClassTemplate ofType(Class<?> primitiveType, String className, String packageName) {
		return ofType(primitiveType, new PrimitiveTypeClassTemplate(), className, packageName);
	}


	public static final <T extends PrimitiveTypeClassTemplate> T ofType(Class<?> type, T t, String className, String packageName) {
		if(type == Boolean.TYPE || type == Boolean.class) {
			PrimitiveTemplates.newBooleanTemplate(t, className, packageName);
		}
		else if(type == Byte.TYPE || type == Byte.class) {
			PrimitiveTemplates.newByteTemplate(t, className, packageName);
		}
		else if(type == Character.TYPE || type == Character.class) {
			PrimitiveTemplates.newCharTemplate(t, className, packageName);
		}
		else if(type == Short.TYPE || type == Short.class) {
			PrimitiveTemplates.newShortTemplate(t, className, packageName);
		}
		else if(type == Integer.TYPE || type == Integer.class) {
			PrimitiveTemplates.newIntTemplate(t, className, packageName);
		}
		else if(type == Float.TYPE || type == Float.class) {
			PrimitiveTemplates.newFloatTemplate(t, className, packageName);
		}
		else if(type == Long.TYPE || type == Long.class) {
			PrimitiveTemplates.newLongTemplate(t, className, packageName);
		}
		else if(type == Double.TYPE || type == Double.class) {
			PrimitiveTemplates.newDoubleTemplate(t, className, packageName);
		}
		else {
			throw new IllegalArgumentException("unknown primitive type for class template: " + type);
		}

		return t;
	}


	public static final void setupClassTemplate(ClassInfo t, String className, String packageName) {
		t.setClassName(className);
		t.setPackageName(packageName);
	}


	public static final <T extends PrimitiveTypeInfo> T setupPrimitiveTypeInfo(Class<?> type, T t) {
		if(type == Boolean.TYPE || type == Boolean.class) {
			PrimitiveTemplates.setupBooleanTemplate(t);
		}
		else if(type == Byte.TYPE || type == Byte.class) {
			PrimitiveTemplates.setupByteTemplate(t);
		}
		else if(type == Character.TYPE || type == Character.class) {
			PrimitiveTemplates.setupCharTemplate(t);
		}
		else if(type == Short.TYPE || type == Short.class) {
			PrimitiveTemplates.setupShortTemplate(t);
		}
		else if(type == Integer.TYPE || type == Integer.class) {
			PrimitiveTemplates.setupIntTemplate(t);
		}
		else if(type == Float.TYPE || type == Float.class) {
			PrimitiveTemplates.setupFloatTemplate(t);
		}
		else if(type == Long.TYPE || type == Long.class) {
			PrimitiveTemplates.setupLongTemplate(t);
		}
		else if(type == Double.TYPE || type == Double.class) {
			PrimitiveTemplates.setupDoubleTemplate(t);
		}
		else {
			throw new IllegalArgumentException("unknown primitive type for class template: " + type);
		}

		return t;
	}


	private static final <T extends PrimitiveTypeInfo> T setTypeInfo(T t, JPrimitiveType primitiveType) {
		t.setTypeClass(primitiveType.getType());
		t.setType(primitiveType.getJavaPrimitiveName());
		t.setObjectType(primitiveType.getJavaObjectName());
		t.setTypeShort(primitiveType.getShortName());
		t.setTypeShortTitleCase(primitiveType.getShortTitleCaseName());
		return t;
	}

}