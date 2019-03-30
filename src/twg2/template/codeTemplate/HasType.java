package twg2.template.codeTemplate;

/** A template with a (class/data) type
 * @author TeamworkGuy2
 * @since 2015-8-27
 */
public interface HasType {

	public static class Impl implements HasType {
		public Class<?> typeClass;

		@Override public Class<?> getTypeClass() { return typeClass; }

		@Override public void setTypeClass(Class<?> typeClass) { this.typeClass = typeClass; }

	}




	/** the data type class of this primitive template */
	public Class<?> getTypeClass();

	public void setTypeClass(Class<?> clazz);


	public static HasType.Impl of(Class<?> typeClass) {
		Impl impl = new Impl();
		impl.typeClass = typeClass;
		return impl;
	}

}
