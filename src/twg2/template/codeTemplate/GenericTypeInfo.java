package twg2.template.codeTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-8-28
 */
public interface GenericTypeInfo extends HasType, HasTypeName {

	public static class Impl implements GenericTypeInfo {
		public String type;
		public String typeShort;
		public String typeShortTitleCase;
		public String objectType;
		public Class<?> typeClass;
		public boolean generic;
		public boolean primitive;

		@Override public String getType() { return type; }

		@Override public void setType(String type) { this.type = type; }

		@Override public String getTypeShort() { return typeShort; }

		@Override public void setTypeShort(String typeShort) { this.typeShort = typeShort; }

		@Override public String getTypeShortTitleCase() { return typeShortTitleCase; }

		@Override public void setTypeShortTitleCase(String typeShortTitleCase) { this.typeShortTitleCase = typeShortTitleCase; }

		@Override public String getObjectType() { return objectType; }

		@Override public void setObjectType(String objectType) { this.objectType = objectType; }

		@Override public Class<?> getTypeClass() { return typeClass; }

		@Override public void setTypeClass(Class<?> typeClass) { this.typeClass = typeClass; }

		@Override public boolean isGeneric() { return generic; }

		@Override public void setGeneric(boolean generic) { this.generic = generic; }

		@Override public boolean isPrimitive() { return primitive; }

		@Override public void setPrimitive(boolean primitive) { this.primitive = primitive; }


		@Override
		public String toString() {
			return getType();
		}

	}




	public static GenericTypeInfo.Impl of(String type, Class<?> typeClass) {
		Impl impl = of(type, type, type, type, typeClass, false, false);
		return impl;
	}


	public static GenericTypeInfo.Impl of(String type, Class<?> typeClass, boolean generic) {
		Impl impl = of(type, type, type, type, typeClass, generic, false);
		return impl;
	}


	public static GenericTypeInfo.Impl of(String type, Class<?> typeClass, boolean generic, boolean primitive) {
		Impl impl = of(type, type, type, type, typeClass, generic, primitive);
		return impl;
	}


	public static GenericTypeInfo.Impl ofWithShort(String type, String typeShort, Class<?> typeClass, boolean generic, boolean primitive) {
		Impl impl = of(type, typeShort, typeShort, type, typeClass, generic, primitive);
		return impl;
	}


	public static GenericTypeInfo.Impl of(String type, String typeShort, String typeShortTitleCase, String objectType, Class<?> typeClass, boolean generic, boolean primitive) {
		Impl impl = new Impl();
		impl.type = type;
		impl.typeShort = typeShort;
		impl.typeShortTitleCase = typeShortTitleCase;
		impl.objectType = objectType;
		impl.typeClass = typeClass;
		impl.generic = generic;
		impl.primitive = primitive;
		return impl;
	}


	/** whether this type (parameter) is generic or not */
	public boolean isGeneric();
	public void setGeneric(boolean generic);

	/** whether this type (parameter) is generic or not */
	public boolean isPrimitive();
	public void setPrimitive(boolean primitive);

}
