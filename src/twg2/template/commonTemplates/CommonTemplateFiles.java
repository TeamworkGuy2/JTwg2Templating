package twg2.template.commonTemplates;

/** Paths to common template files
 * @author TeamworkGuy2
 * @since 2015-10-10
 */
public final class CommonTemplateFiles {

	public static final class TemplateFileInfo {
		final String pathString;

		public TemplateFileInfo(String pathString) {
			this.pathString = pathString;
		}

		public String getPathString() {
			return pathString;
		}

	}

	public static final TemplateFileInfo ExampleTemplate = new TemplateFileInfo("twg2/template/commonTemplates/ExampleTemplate.stg");
	public static final TemplateFileInfo JavaClass = new TemplateFileInfo("twg2/template/commonTemplates/JavaClass.stg");

}
