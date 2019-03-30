package twg2.template.codeTemplate.primitiveTemplate;

import twg2.template.codeTemplate.ClassTemplate;

/** A basic template for {@code ANTLR StringTemplates}.
 * This is designed for a class that leverages a primitive data type,
 * such as a primitive list or map implementation.
 * @author TeamworkGuy2
 * @since 2015-1-17
 */
public class PrimitiveTypeClassTemplate extends ClassTemplate implements PrimitiveTypeInfo {
	/** the name of the data type of the values in this list */
	public String type;
	/** the data type class of this primitive template */
	public Class<?> typeClass;
	/** short type name, for example type {@code integer} would have a short name of {@code int} */
	public String typeShort;
	/** short upper case type name, for example type {@code integer} would have a short upper case name of {@code Int} */
	public String typeShortTitleCase;
	/** the name of the object data type of the values in this list, for example {@link Integer} */
	public String objectType;
	/** whether this primitive type is generic or not, probably false */
	public boolean generic;
	/** whether this primitive type is primitive or not, probably true */
	public boolean primitive = true;
	/** a default value for empty values, for example {@code false} for {@link Boolean booleans},
	 * {@code 0} for {@link Integer integers}, etc. */
	public Object defaultValue;
	/** true if the average, sum, min, max, etc. of the data type can be calculated */
	public boolean isAggregatable;
	/** the data type for averages of this data type */
	public String averageType;
	/** the data type for sums of this data type */
	public String sumType;
	/** the index to return when no index is found when searching for a value */
	public String indexNotFoundValue;
	/** an expression to get the minimum value of this type */
	public String minValue;
	/** an expression to get the maximum value of this type */
	public String maxValue;


	@Override public String getType() { return type; }

	@Override public void setType(String type) { this.type = type; }

	@Override public Class<?> getTypeClass() { return typeClass; }

	@Override public void setTypeClass(Class<?> typeClass) { this.typeClass = typeClass; }

	@Override public String getTypeShort() { return typeShort; }

	@Override public void setTypeShort(String typeShort) { this.typeShort = typeShort; }

	@Override public String getTypeShortTitleCase() { return typeShortTitleCase; }

	@Override public void setTypeShortTitleCase(String typeShortTitleCase) { this.typeShortTitleCase = typeShortTitleCase; }

	@Override public String getObjectType() { return objectType; }

	@Override public void setObjectType(String objectType) { this.objectType = objectType; }

	@Override public boolean isGeneric() { return generic; }

	@Override public void setGeneric(boolean generic) { this.generic = generic; }

	@Override public boolean isPrimitive() { return primitive; }

	@Override public void setPrimitive(boolean primitive) { this.primitive = primitive; }

	@Override public Object getDefaultValue() { return defaultValue; }

	@Override public void setDefaultValue(Object defaultValue) { this.defaultValue = defaultValue; }

	@Override public boolean isAggregatable() { return isAggregatable; }

	@Override public void setAggregatable(boolean isAggregatable) { this.isAggregatable = isAggregatable; }

	@Override public String getAverageType() { return averageType; }

	@Override public void setAverageType(String averageType) { this.averageType = averageType; }

	@Override public String getSumType() { return sumType; }

	@Override public void setSumType(String sumType) { this.sumType = sumType; }

	@Override public String getIndexNotFoundValue() { return indexNotFoundValue; }

	@Override public void setIndexNotFoundValue(String indexNotFoundValue) { this.indexNotFoundValue = indexNotFoundValue; }

	@Override public String getMinValue() { return minValue; }

	@Override public void setMinValue(String minValue) { this.minValue = minValue; }

	@Override public String getMaxValue() { return maxValue; }

	@Override public void setMaxValue(String maxValue) { this.maxValue = maxValue; }


	/** Create an empty primitive template information class
	 */
	public PrimitiveTypeClassTemplate() {
	}


	/** Create an primitive template with type information about the specified primitive type
	 */
	public PrimitiveTypeClassTemplate(Class<?> type) {
		PrimitiveTemplates.setupPrimitiveTypeInfo(type, this);
	}

}
