package twg2.template.codeTemplate.primitiveTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import twg2.template.codeTemplate.ClassInfo;

/**
 * @author TeamworkGuy2
 * @since 2015-6-23
 */
@NoArgsConstructor
@AllArgsConstructor
public class PrimitiveClassAndType {
	public @Getter @Setter ClassInfo classInfo;
	public @Getter @Setter PrimitiveTypeInfo typeInfo;

}
