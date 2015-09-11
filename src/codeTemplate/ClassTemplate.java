package codeTemplate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/** A basic template for {@code ANTLR StringTemplates}.
 * This is designed for a Java class template.
 * @author TeamworkGuy2
 * @since 2014-12-20
 */
public class ClassTemplate implements ClassInfo {
	/** the name of the package */
	public @Getter @Setter String packageName;
	/** list of type names to import, or import statements */
	public @Getter @Setter List<String> importStatements;

	/** the class' access modifier (e.g. 'public', 'protected', 'strictfp') */
	public @Getter @Setter String classModifier;
	/** the class' class type (e.g. 'class', 'interface', 'enum') */
	public @Getter @Setter String classType;
	/** a list of the generic types of this class, (i.e. the types from a {@code <T, R, S extends Number>} statement) */
	public @Getter @Setter List<String> classTypeParameterDefinitions;
	/** a list of the generic parameter type names of this class, (i.e. the names {@code <T, R, S>}) */
	public @Getter @Setter List<String> classTypeParameterNames;

	/** the class name */
	public @Getter @Setter String className;
	/** the name of the class extended by this class */
	public @Getter @Setter String extendClassName;
	/** list of interface names implemented by this class */
	public @Getter @Setter List<String> implementClassNames;


	/** Create an unsorted group of items with a default size of 16
	 */
	public ClassTemplate() {
	}

}
