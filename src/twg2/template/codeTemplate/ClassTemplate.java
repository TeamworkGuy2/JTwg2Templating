package twg2.template.codeTemplate;

import java.util.List;

/** A basic template for {@code ANTLR StringTemplates}.
 * This is designed for a Java class template.
 * @author TeamworkGuy2
 * @since 2014-12-20
 */
public class ClassTemplate implements ClassInfo {
	/** the name of the package */
	public String packageName;
	/** list of type names to import, or import statements */
	public List<String> importStatements;

	/** the class' access modifier (e.g. 'public', 'protected', 'strictfp') */
	public String classModifier;
	/** the class' class type (e.g. 'class', 'interface', 'enum') */
	public String classType;
	/** a list of the generic types of this class, (i.e. the types from a {@code <T, R, S extends Number>} statement) */
	public List<String> classTypeParameterDefinitions;
	/** a list of the generic parameter type names of this class, (i.e. the names {@code <T, R, S>}) */
	public List<String> classTypeParameterNames;

	/** the class name */
	public String className;
	/** the name of the class extended by this class */
	public String extendClassName;
	/** list of interface names implemented by this class */
	public List<String> implementClassNames;


	@Override public String getPackageName() { return packageName; }

	@Override public void setPackageName(String packageName) { this.packageName = packageName; }

	@Override public List<String> getImportStatements() { return importStatements; }

	@Override public void setImportStatements(List<String> importStatements) { this.importStatements = importStatements; }

	@Override public String getClassModifier() { return classModifier; }

	@Override public void setClassModifier(String classModifier) { this.classModifier = classModifier; }

	@Override public String getClassType() { return classType; }

	@Override public void setClassType(String classType) { this.classType = classType; }

	@Override public List<String> getClassTypeParameterDefinitions() { return classTypeParameterDefinitions; }

	@Override public void setClassTypeParameterDefinitions(List<String> classTypeParameterDefinitions) { this.classTypeParameterDefinitions = classTypeParameterDefinitions; }

	@Override public List<String> getClassTypeParameterNames() { return classTypeParameterNames; }

	@Override public void setClassTypeParameterNames(List<String> classTypeParameterNames) { this.classTypeParameterNames = classTypeParameterNames; }

	@Override public String getClassName() { return className; }

	@Override public void setClassName(String className) { this.className = className; }

	@Override public String getExtendClassName() { return extendClassName; }

	@Override public void setExtendClassName(String extendClassName) { this.extendClassName = extendClassName; }

	@Override public List<String> getImplementClassNames() { return implementClassNames; }

	@Override public void setImplementClassNames(List<String> implementClassNames) { this.implementClassNames = implementClassNames; }


	/** Create an unsorted group of items with a default size of 16
	 */
	public ClassTemplate() {
	}

}
