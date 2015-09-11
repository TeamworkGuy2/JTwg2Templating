package codeTemplate.primitiveTemplate;

import lombok.Getter;
import lombok.Setter;


/** A basic template for {@code ANTLR StringTemplates}.
 * This is designed for a class that leverages a primitive data type,
 * such as a primitive list or map implementation.
 * @author TeamworkGuy2
 * @since 2015-1-17
 */
public class PrimitiveTypeTemplate implements PrimitiveTypeInfo {
	/** the name of the data type of the values in this list */
	public @Getter @Setter String type;
	/** the data type class of this primitive template */
	public @Getter @Setter Class<?> typeClass;
	/** short type name, for example type {@code integer} would have a short name of {@code int} */
	public @Getter @Setter String typeShort;
	/** short upper case type name, for example type {@code integer} would have a short upper case name of {@code Int} */
	public @Getter @Setter String typeShortTitleCase;
	/** the name of the object data type of the values in this list, for example {@link Integer} */
	public @Getter @Setter String objectType;
	/** whether this primitive type is generic or not, probably false */
	public @Getter @Setter boolean generic;
	/** whether this primitive type is primitive or not, probably true */
	public @Getter @Setter boolean primitive = true;
	/** a default value for empty values, for example {@code false} for {@link Boolean booleans},
	 * {@code 0} for {@link Integer integers}, etc. */
	public @Getter @Setter Object defaultValue;
	/** true if the average, sum, min, max, etc. of the data type can be calculated */
	public @Getter @Setter boolean isAggregatable;
	/** the data type for averages of this data type */
	public @Getter @Setter String averageType;
	/** the data type for sums of this data type */
	public @Getter @Setter String sumType;
	/** the index to return when no index is found when searching for a value */
	public @Getter @Setter String indexNotFoundValue;
	/** an expression to get the minimum value of this type */
	public @Getter @Setter String minValue;
	/** an expression to get the maximum value of this type */
	public @Getter @Setter String maxValue;


	/** Create an empty primitive template information class
	 */
	public PrimitiveTypeTemplate() {
	}


	/** Create an primitive template with type information about the specified primitive type
	 */
	public PrimitiveTypeTemplate(Class<?> type) {
		PrimitiveTemplates.setupPrimitiveTypeInfo(type, this);
	}

}
