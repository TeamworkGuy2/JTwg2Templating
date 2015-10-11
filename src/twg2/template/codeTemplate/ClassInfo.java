package twg2.template.codeTemplate;

import java.util.List;

public interface ClassInfo extends ClassLocation {

	@Override
	public String getPackageName();

	@Override
	public void setPackageName(String packageName);

	@Override
	public String getClassName();

	@Override
	public void setClassName(String className);

	public List<String> getImportStatements();

	public void setImportStatements(List<String> importStatements);

	public String getClassModifier();

	public void setClassModifier(String classModifier);

	public String getClassType();

	public void setClassType(String classType);

	public List<String> getClassTypeParameterDefinitions();

	public void setClassTypeParameterDefinitions(List<String> classTypeParameterDefinitions);

	public List<String> getClassTypeParameterNames();

	public void setClassTypeParameterNames(List<String> classTypeParameterNames);

	public String getExtendClassName();

	public void setExtendClassName(String extendClassName);

	public List<String> getImplementClassNames();

	public void setImplementClassNames(List<String> implementClassNames);

}