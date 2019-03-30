package twg2.template.codeTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-8-28
 */
public interface HasTypeName {

	public static class Impl implements HasTypeName {
		public String type;
		public String typeShort;
		public String typeShortTitleCase;
		public String objectType;

		@Override public String getType() { return type; }

		@Override public void setType(String type) { this.type = type; }

		@Override public String getTypeShort() { return typeShort; }

		@Override public void setTypeShort(String typeShort) { this.typeShort = typeShort; }

		@Override public String getTypeShortTitleCase() { return typeShortTitleCase; }

		@Override public void setTypeShortTitleCase(String typeShortTitleCase) { this.typeShortTitleCase = typeShortTitleCase; }

		@Override public String getObjectType() { return objectType; }

		@Override public void setObjectType(String objectType) { this.objectType = objectType; }


		@Override
		public String toString() {
			return getType();
		}

	}




	public static HasTypeName.Impl of(String type) {
		Impl impl = of(type, type, type, type);
		return impl;
	}


	public static HasTypeName.Impl ofWithShort(String type, String typeShort) {
		Impl impl = of(type, typeShort, typeShort, type);
		return impl;
	}


	public static HasTypeName.Impl of(String type, String typeShort, String typeShortTitleCase, String objectType) {
		Impl impl = new Impl();
		impl.type = type;
		impl.typeShort = typeShort;
		impl.typeShortTitleCase = typeShortTitleCase;
		impl.objectType = objectType;
		return impl;
	}


	/** the type name, for example {@code Integer} */
	public String getType();
	public void setType(String type);

	/** short type name, for example type {@code integer} would have a short name of {@code int} */
	public String getTypeShort();
	public void setTypeShort(String typeShortName);

	/** short upper case type name, for example type {@code integer} would have a short upper case name of {@code Int} */
	public String getTypeShortTitleCase();
	public void setTypeShortTitleCase(String typeShortTitleName);

	/** the name of the object data type of the values in this list, for example {@link Integer} */
	public String getObjectType();
	public void setObjectType(String objectTypeName);

}
