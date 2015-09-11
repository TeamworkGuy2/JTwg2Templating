package codeTemplate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author TeamworkGuy2
 * @since 2015-8-28
 */
public interface GenericTypeInfo extends HasType, HasTypeName {

	public static class Impl implements GenericTypeInfo {
		public @Getter @Setter String type;
		public @Getter @Setter String typeShort;
		public @Getter @Setter String typeShortTitleCase;
		public @Getter @Setter String objectType;
		public @Getter @Setter Class<?> typeClass;
		public @Getter @Setter boolean generic;
		public @Getter @Setter boolean primitive;


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
