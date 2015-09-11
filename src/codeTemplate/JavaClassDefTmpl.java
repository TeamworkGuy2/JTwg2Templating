package codeTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import stringUtils.StringCheck;
import stringUtils.StringJoin;

/**
 * @author TeamworkGuy2
 * @since 2015-5-19
 */
public class JavaClassDefTmpl {
	public static enum Parts {
		CLASS_JAVA_DOC,
		CLASS_ANNOTATIONS,
		CLASS_FIELDS,
		CLASS_CONSTRUCTORS,
		CLASS_INSTANCE_METHODS,
		CLASS_STATIC_METHODS;
	}


	public static class Indentation {
		int level = 0;
		String levelIndent = "\t";
		String currentIndent = levelIndent;

		public void indent() {
			level++;
			currentIndent += levelIndent;
		}

		public void deindent() {
			if(level < 1) {
				throw new IllegalStateException("cannot deindent because no indentation levels remain");
			}
			level--;
			currentIndent = currentIndent.substring(0, currentIndent.length() - levelIndent.length());
		}

		@Override
		public String toString() {
			return currentIndent;
		}
	}


	public static <T extends Collection<? extends CharSequence>> String generateClassDefinition(ClassTemplate config, Map<Parts, Supplier<T>> parts) {
		Indentation indent = new Indentation();
		String str = "" +
			"package " + config.packageName + ";\n" +
			"\nimport " +
			StringJoin.join(config.importStatements, ";\nimport ") +
			";\n\n" +
			gen(Parts.CLASS_JAVA_DOC, parts, indent, "\n", false) +
			gen(Parts.CLASS_ANNOTATIONS, parts, indent, "\n", false) +
			"@javax.annotation.Generated(\"JavaTemplates\")\n" +
			(is(config.classModifier) ? config.classModifier : "public") + " " +
				(is(config.classType) ? config.classType : "class") + " " +
				config.className + (is(config.classTypeParameterDefinitions) ? "<" + StringJoin.join(config.classTypeParameterDefinitions, ", ") + ">" : "") +
				(is(config.extendClassName) ? " extends " + config.extendClassName : "") +
				(is(config.implementClassNames) ? " implements " + StringJoin.join(config.implementClassNames, ", ") : "") + "{\n" +
			gen(Parts.CLASS_FIELDS, parts, indent, "\n", true) + "\n\n" +
			gen(Parts.CLASS_CONSTRUCTORS, parts, indent, "\n\n\n", true) +
			gen(Parts.CLASS_INSTANCE_METHODS, parts, indent, "\n", true) + "\n\n" +
			gen(Parts.CLASS_STATIC_METHODS, parts, indent, "\n\n\n", true) +
			"}\n";
		return str;
	}


	private static final boolean is(Collection<?> objs) {
		return objs != null && objs.size() > 0;
	}


	private static final boolean is(String str) {
		return !StringCheck.isNullOrEmpty(str);
	}


	private static final <T extends Collection<? extends CharSequence>> String gen(Parts part, Map<Parts, Supplier<T>> parts, Indentation indent, String postfix, boolean isBlock) {
		if(isBlock) {
			indent.indent();
		}

		String indentStr = indent.toString();
		StringBuilder strB = new StringBuilder();
		if(parts.containsKey(part)) {
			Collection<? extends CharSequence> strs = parts.get(part).get();
			for(CharSequence str : strs) {
				strB.append(indentStr);
				strB.append(str);
				strB.append(postfix);
			}
		}

		if(isBlock) {
			indent.deindent();
		}

		return strB.toString();
	}

}
