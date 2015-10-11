package twg2.template.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import twg2.template.codeTemplate.ClassTemplate;
import twg2.template.codeTemplate.ClassTemplateBuilder;
import twg2.template.codeTemplate.JavaClassDefTmpl;
import twg2.template.codeTemplate.JavaClassDefTmpl.Parts;
import twg2.template.codeTemplate.render.TemplateWriters;

/**
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public class UnitTest {


	public static void generateTestClass() {
		ClassTemplate config = ClassTemplateBuilder.of("GeneratedClass", "twg2.template.test")
				.addImportStatement("java.util.HashMap")
				.addImplementClassNames(Runnable.class)
				.getTemplate();

		final HashMap<JavaClassDefTmpl.Parts, Supplier<List<String>>> parts = new HashMap<>();
		parts.put(Parts.CLASS_FIELDS, () -> {
			return Arrays.asList(
				"public static int a;",
				"public static int b = 5;"
			);
		});
		parts.put(Parts.CLASS_INSTANCE_METHODS, () -> {
			return Arrays.asList(
				"@Override",
				"public void run() {",
				"\tHashMap<String, Object> c = new HashMap<String, Object>();",
				"\tc.put(\"\", null);",
				"}"
			);
		});

		TemplateWriters.writeClassTemplate((tmpl) -> JavaClassDefTmpl.generateClassDefinition(tmpl, parts), config);
	}


	public static void main(String[] args) {
		//TemplateExampleTest ex = new TemplateExampleTest();
		//ex.test();
		generateTestClass();
	}

}
