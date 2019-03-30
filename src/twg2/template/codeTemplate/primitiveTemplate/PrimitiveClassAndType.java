package twg2.template.codeTemplate.primitiveTemplate;

import twg2.template.codeTemplate.ClassInfo;

/**
 * @author TeamworkGuy2
 * @since 2015-6-23
 */
public class PrimitiveClassAndType {
	public ClassInfo classInfo;
	public PrimitiveTypeInfo typeInfo;

	public ClassInfo getClassInfo() { return classInfo; }

	public void setClassInfo(ClassInfo classInfo) { this.classInfo = classInfo; }

	public PrimitiveTypeInfo getTypeInfo() { return typeInfo; }

	public void setTypeInfo(PrimitiveTypeInfo typeInfo) { this.typeInfo = typeInfo; }


	public PrimitiveClassAndType() {
	}


	public PrimitiveClassAndType(ClassInfo classInfo, PrimitiveTypeInfo typeInfo) {
		this.classInfo = classInfo;
		this.typeInfo = typeInfo;
	}

}
