package twg2.template.test;

import java.util.Arrays;

import org.stringtemplate.v4.ST;

import twg2.template.codeTemplate.render.StringTemplatesUtil;
import twg2.template.codeTemplate.render.TemplateImports;

/**
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public class TemplateExampleTest {


	public void test() {
		ST st = StringTemplatesUtil.fileTemplate("src/twg2/template/test/TemplateExample.stg", "TemplateExample", TemplateImports.emptyInst());
		st.add("var", Arrays.asList(new DataType[] {
			new DataType("id", "int"),
			new DataType("name", "String"),
		}));
		String output = st.render();
		System.out.println(output);
	}

}


/**
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
class DataType {
	private String name;
	private String type;


	/**
	 * @param name
	 * @param type
	 */
	public DataType(String name, String type) {
		this.name = name;
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}

}