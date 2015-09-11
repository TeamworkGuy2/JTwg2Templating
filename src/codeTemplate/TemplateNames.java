package codeTemplate;

import java.util.List;
import java.util.function.BiFunction;

import twg2.primitiveIoTypes.JavaPrimitive;
import codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-8-28
 */
public class TemplateNames {


	/** Replace {@code "$type$"} with {@link PrimitiveTypeClassTemplate#typeShort}
	 * and {@code "$Type$"} with {@link PrimitiveTypeClassTemplate#typeShortTitleCase}
	 * in a template's: {@link ClassTemplate#className className}, {@link ClassTemplate#extendClassName extendClassName},
	 * and {@link ClassTemplate#implementClassNames implementClassNames} properties.
	 * Useful for creating and reusing generic type templates for multiple primitive types
	 * @param t the template object to check and replace the specified strings in
	 * @return the original {@code t} argument object
	 */
	public static final <T extends GenericTypeInfo & ClassInfo> T inferClassNames(T t) {
		return inferClassNames(t, (BiFunction<T, String, String>)null);
	}


	public static final <T extends GenericTypeInfo & ClassInfo> T inferClassNames(T t, BiFunction<T, String, String> customConverter) {
		inferClassNames(t, t, "$type$", "$Type$", customConverter);
		return t;
	}


	public static final void inferClassNames(ClassInfo classInfo, GenericTypeInfo typeInfo) {
		inferClassNames(classInfo, typeInfo, "$type$", "$Type$");
	}


	public static final String inferClassName(String name, JavaPrimitive type) {
		return replaceTypeName(name, type, "$type$", "$Type$");
	}


	public static final void inferClassNames(ClassInfo classInfo, GenericTypeInfo typeInfo, String typeShortMarker, String typeShortUpperCaseMarker) {
		inferClassNames(classInfo, typeInfo, typeShortMarker, typeShortUpperCaseMarker, null);
	}


	public static final <T extends ClassInfo> T inferClassNames(T classInfo, GenericTypeInfo typeInfo, String typeShortMarker, String typeShortUpperCaseMarker, BiFunction<T, String, String> customConverter) {
		if(classInfo.getClassName() != null) {
			String str = replaceTypeName(classInfo.getClassName(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(classInfo, str) : str;
			classInfo.setClassName(str);
		}
		if(classInfo.getExtendClassName() != null) {
			String str = replaceTypeName(classInfo.getExtendClassName(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(classInfo, str) : str;
			classInfo.setExtendClassName(str);
		}
		if(classInfo.getImplementClassNames() != null) {
			List<String> implementsNames = classInfo.getImplementClassNames();
			for(int i = 0, size = implementsNames.size(); i < size; i++) {
				String str = replaceTypeName(implementsNames.get(i), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
				str = customConverter != null ? customConverter.apply(classInfo, str) : str;
				implementsNames.set(i, str);
			}
		}
		return classInfo;
	}


	public static final <T extends ClassInfo> T inferClassNames(T classInfo, BiFunction<T, String, String> customConverter) {
		if(classInfo.getClassName() != null) {
			String str = classInfo.getClassName();
			str = customConverter != null ? customConverter.apply(classInfo, str) : str;
			classInfo.setClassName(str);
		}
		if(classInfo.getExtendClassName() != null) {
			String str = classInfo.getExtendClassName();
			str = customConverter != null ? customConverter.apply(classInfo, str) : str;
			classInfo.setExtendClassName(str);
		}
		if(classInfo.getImplementClassNames() != null) {
			List<String> implementsNames = classInfo.getImplementClassNames();
			for(int i = 0, size = implementsNames.size(); i < size; i++) {
				String str = implementsNames.get(i);
				str = customConverter != null ? customConverter.apply(classInfo, str) : str;
				implementsNames.set(i, str);
			}
		}
		return classInfo;
	}


	public static final <T extends GenericTypeInfo> T inferTypeNames(T typeInfo, BiFunction<T, String, String> customConverter) {
		return inferTypeNames(typeInfo, "$type$", "$Type$", customConverter);
	}


	public static final <T extends GenericTypeInfo> T inferTypeNames(T typeInfo, String typeShortMarker, String typeShortUpperCaseMarker, BiFunction<T, String, String> customConverter) {
		if(typeInfo.getType() != null) {
			String str = replaceTypeName(typeInfo.getType(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(typeInfo, str) : str;
			typeInfo.setType(str);
		}
		if(typeInfo.getTypeShort() != null) {
			String str = replaceTypeName(typeInfo.getTypeShort(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(typeInfo, str) : str;
			typeInfo.setTypeShort(str);
		}
		if(typeInfo.getTypeShortTitleCase() != null) {
			String str = replaceTypeName(typeInfo.getTypeShortTitleCase(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(typeInfo, str) : str;
			typeInfo.setTypeShortTitleCase(str);
		}
		if(typeInfo.getObjectType() != null) {
			String str = replaceTypeName(typeInfo.getObjectType(), typeInfo, typeShortMarker, typeShortUpperCaseMarker, true);
			str = customConverter != null ? customConverter.apply(typeInfo, str) : str;
			typeInfo.setObjectType(str);
		}
		return typeInfo;
	}


	public static final <T extends GenericTypeInfo> String replaceTypeName(String name, T t, String typeShortMarker, String typeShortUpperCaseMarker, boolean genericToObject) {
		name = name.replace(typeShortMarker, genericToObject && t.isGeneric() ? "Object" : t.getTypeShort());
		name = name.replace(typeShortUpperCaseMarker, genericToObject && t.isGeneric() ? "Object" : t.getTypeShortTitleCase());
		return name;
	}


	public static final String replaceTypeName(String name, JavaPrimitive type, String typeShortMarker, String typeShortUpperCaseMarker) {
		name = name.replace(typeShortMarker, type.getShortName());
		name = name.replace(typeShortUpperCaseMarker, type.getShortTitleCaseName());
		return name;
	}

}
