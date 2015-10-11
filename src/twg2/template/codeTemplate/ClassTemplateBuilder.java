package twg2.template.codeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param <T> the {@link ClassInfo} to build
 * 
 * @author TeamworkGuy2
 * @since 2015-1-24
 */
public final class ClassTemplateBuilder<T extends ClassInfo> {
	private T tmpl;


	public ClassTemplateBuilder(T tmpl) {
		this.tmpl = tmpl;
	}


	public ClassTemplateBuilder(T tmpl, String className, String packageName) {
		this.tmpl = tmpl;
		tmpl.setClassName(className);
		tmpl.setPackageName(packageName);
	}


	public ClassTemplateBuilder<T> setPackageName(String packageName) {
		tmpl.setPackageName(packageName);
		return this;
	}


	/** Alias for {@link #addImportStatement(Class...)} */
	@SafeVarargs
	public final ClassTemplateBuilder<T> imports(Class<?>... importStatements) {
		return addImportStatement(importStatements);
	}


	@SafeVarargs
	public final ClassTemplateBuilder<T> addImportStatement(Class<?>... importStatements) {
		List<String> statements = tmpl.getImportStatements();

		if(statements == null) {
			statements = new ArrayList<>();
			tmpl.setImportStatements(statements);
		}
		if(importStatements == null) {
			return this;
		}

		for(Class<?> importStatement : importStatements) {
			statements.add(importStatement.getCanonicalName());
		}
		return this;
	}


	/** Alias for {@link #addImportStatement(String...)} */
	@SafeVarargs
	public final ClassTemplateBuilder<T> imports(String... importStatements) {
		return addImportStatement(importStatements);
	}


	@SafeVarargs
	public final ClassTemplateBuilder<T> addImportStatement(String... importStatements) {
		List<String> imports = tmpl.getImportStatements();

		if(imports == null) {
			imports = new ArrayList<>();
			tmpl.setImportStatements(imports);
		}
		if(importStatements == null) {
			return this;
		}

		for(String importStatement : importStatements) {
			imports.add(importStatement);
		}
		return this;
	}


	public ClassTemplateBuilder<T> setClassModifier(String classAccessModifier) {
		tmpl.setClassModifier(classAccessModifier);
		return this;
	}


	public ClassTemplateBuilder<T> setClassType(String classType) {
		tmpl.setClassType(classType);
		return this;
	}


	public ClassTemplateBuilder<T> addTypeParameters(Iterable<? extends Map.Entry<String, String>> classParameterNamesAndTypes) {
		for(Map.Entry<String, String> classParam : classParameterNamesAndTypes) {
			addTypeParameter(classParam.getKey(), classParam.getValue());
		}
		return this;
	}


	public ClassTemplateBuilder<T> addTypeParameter(String classParameterName, String classTypeParameterDefinition) {
		if(tmpl.getClassTypeParameterDefinitions() == null) {
			tmpl.setClassTypeParameterDefinitions(new ArrayList<>());
		}
		if(tmpl.getClassTypeParameterNames() == null) {
			tmpl.setClassTypeParameterNames(new ArrayList<>());
		}
		tmpl.getClassTypeParameterNames().add(classParameterName);
		tmpl.getClassTypeParameterDefinitions().add(classTypeParameterDefinition);
		return this;
	}


	public ClassTemplateBuilder<T> setClassName(String className) {
		tmpl.setClassName(className);
		return this;
	}


	/** Alias for {@link #setExtendClassName(Class)} */
	public ClassTemplateBuilder<T> extend(Class<?> extendClassName) {
		return setExtendClassName(extendClassName);
	}


	public ClassTemplateBuilder<T> setExtendClassName(Class<?> extendClassName) {
		tmpl.setExtendClassName(extendClassName.getCanonicalName().replace("java.lang.", ""));
		return this;
	}


	/** Alias for {@link #setExtendClassName(String)} */
	public ClassTemplateBuilder<T> extend(String extendClassName) {
		return setExtendClassName(extendClassName);
	}


	public ClassTemplateBuilder<T> setExtendClassName(String extendClassName) {
		tmpl.setExtendClassName(extendClassName);
		return this;
	}


	/** Alias for {@link #addImplementClassNames(String...)} */
	@SafeVarargs
	public final ClassTemplateBuilder<T> implement(String... implementClassNames) {
		return addImplementClassNames(implementClassNames);
	}


	@SafeVarargs
	public final ClassTemplateBuilder<T> addImplementClassNames(String... implementClassNames) {
		List<String> implementNames = tmpl.getImplementClassNames();

		if(implementNames == null) {
			implementNames = new ArrayList<>();
			tmpl.setImplementClassNames(implementNames);
		}
		if(implementClassNames == null) {
			return this;
		}

		for(String implementClassName : implementClassNames) {
			implementNames.add(implementClassName);
		}
		return this;
	}


	/** Alias for {@link #addImplementClassNames(Class...)} */
	@SafeVarargs
	public final ClassTemplateBuilder<T> implement(Class<?>... implementClassNames) {
		return addImplementClassNames(implementClassNames);
	}


	@SafeVarargs
	public final ClassTemplateBuilder<T> addImplementClassNames(Class<?>... implementClassNames) {
		List<String> implementNames = tmpl.getImplementClassNames();

		if(implementNames == null) {
			implementNames = new ArrayList<>();
			tmpl.setImplementClassNames(implementNames);
		}
		if(implementClassNames == null) {
			return this;
		}
		for(Class<?> implementClassName : implementClassNames) {
			implementNames.add(implementClassName.getCanonicalName().replace("java.lang.", ""));
		}
		return this;
	}


	public T getTemplate() {
		return tmpl;
	}


	public static ClassTemplateBuilder<ClassInfo> newInst() {
		return new ClassTemplateBuilder<ClassInfo>(new ClassTemplate());
	}


	public static <T extends ClassInfo> ClassTemplateBuilder<T> of(T inst) {
		return new ClassTemplateBuilder<>(inst);
	}


	public static <T extends ClassInfo> ClassTemplateBuilder<T> of(T inst, String className, String packageName) {
		return new ClassTemplateBuilder<>(inst, className, packageName);
	}


	public static ClassTemplateBuilder<ClassTemplate> of(String className) {
		return of(className, null);
	}


	public static ClassTemplateBuilder<ClassTemplate> of(String className, String packageName) {
		return new ClassTemplateBuilder<>(new ClassTemplate(), className, packageName);
	}

}
