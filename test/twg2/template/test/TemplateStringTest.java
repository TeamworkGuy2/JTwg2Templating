package twg2.template.test;

import org.junit.Assert;
import org.junit.Test;

import twg2.simpleTypes.ioPrimitives.JPrimitiveType;
import twg2.template.codeTemplate.NameFormat;
import twg2.template.templateString.TypeNameTmpl;

/**
 * @author TeamworkGuy2
 * @since 2015-6-1
 */
public class TemplateStringTest {

	@Test
	public void testTemplateString() {
		TypeNameTmpl typeStr = new TypeNameTmpl();
		typeStr.and(NameFormat.SHORT_UPPER).and("Object");
		Assert.assertEquals("ByteObject", typeStr.toString(JPrimitiveType.BYTE));
		Assert.assertEquals("BooleanObject", typeStr.toString(JPrimitiveType.BOOLEAN));
	}

}
