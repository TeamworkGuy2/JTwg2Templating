package test;

import org.junit.Assert;
import org.junit.Test;

import templateString.TypeNameTmpl;
import twg2.primitiveIoTypes.JavaPrimitive;
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
		Assert.assertEquals("ByteObject", typeStr.toString(JavaPrimitive.BYTE));
		Assert.assertEquals("BooleanObject", typeStr.toString(JavaPrimitive.BOOLEAN));
	}

}
