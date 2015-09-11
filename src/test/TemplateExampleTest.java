package test;

import java.util.Arrays;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public class TemplateExampleTest {


	public void test() {
		STGroupFile stg = new STGroupFile("src/test/TemplateExample.stg", '$', '$');
		ST st = stg.getInstanceOf("TemplateExample");
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