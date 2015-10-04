package test;

import org.junit.Assert;
import org.junit.Test;

import templateString.TypeNameTmpl;
import twg2.primitiveIoTypes.JPrimitiveType;
import codeTemplate.NameFormat;

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
