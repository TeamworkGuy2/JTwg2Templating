package twg2.template.codeTemplate.primitiveTemplate;

import twg2.template.codeTemplate.GenericTypeInfo;
import twg2.template.codeTemplate.HasType;
import twg2.template.codeTemplate.HasTypeName;


public interface PrimitiveTypeInfo extends HasType, HasTypeName, GenericTypeInfo {

	/** a default value for empty values, for example {@code false} for {@link Boolean booleans},
	 * {@code 0} for {@link Integer integers}, etc. */
	public Object getDefaultValue();
	public void setDefaultValue(Object defaultValue);

	/** true if the average, sum, min, max, etc. of the data type can be calculated */
	public boolean isAggregatable();
	public void setAggregatable(boolean aggregatable);

	/** the data type for averages of this data type */
	public String getAverageType();
	public void setAverageType(String averageTypeName);

	/** the data type for sums of this data type */
	public String getSumType();
	public void setSumType(String sumTypeName);

	/** the index to return when no index is found when searching for a value */
	public String getIndexNotFoundValue();
	public void setIndexNotFoundValue(String indexNotFoundValue);

	/** an expression to get the minimum value of this type */
	public String getMinValue();
	public void setMinValue(String minValue);

	/** an expression to get the maximum value of this type */
	public String getMaxValue();
	public void setMaxValue(String maxValue);

}
