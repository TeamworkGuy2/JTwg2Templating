package twg2.template.codeTemplate.render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import twg2.template.commonTemplates.CommonTemplateFiles;

/** Inexact parser for StringTemplate '.stg' file 'import' parser and transformer
 * @author TeamworkGuy2
 * @since 2015-10-10
 */
public class TemplateImports {
	private static TemplateImports _emptyInst = new TemplateImports(false);
	private Map<String, String> importAliasesToFullPath;
	private boolean trimImportAliasNameQuotes;


	public TemplateImports(boolean trimImportAliasNameQuotes) {
		this.importAliasesToFullPath = new HashMap<>();
		this.trimImportAliasNameQuotes = trimImportAliasNameQuotes;
	}


	public TemplateImports(Map<String, String> importAliasesToFullPath, boolean trimImportAliasNameQuotes) {
		this.importAliasesToFullPath = importAliasesToFullPath;
		this.trimImportAliasNameQuotes = trimImportAliasNameQuotes;
	}


	public void replaceImportLines(List<String> lines) {
		Map<Integer, String> importAliases = parseImportLines(lines, trimImportAliasNameQuotes);
		for(Entry<Integer, String> aliasEntry : importAliases.entrySet()) {
			String fullPath = importAliasesToFullPath.get(aliasEntry.getValue());
			if(fullPath != null) {
				int idx = aliasEntry.getKey();
				String resultStr = lines.get(idx).replace(aliasEntry.getValue(), fullPath);
				lines.set(idx, resultStr);
			}
		}
	}


	public static final Map<Integer, String> parseImportLines(List<String> lines, boolean trimImportAliasNameQuotes) {
		Map<Integer, String> importLines = findImportLines(lines);
		Map<Integer, String> importsParsed = new HashMap<>();

		for(Entry<Integer, String> importLine : importLines.entrySet()) {
			String importStr = importLine.getValue();
			if(trimImportAliasNameQuotes) {
				importStr = importStr.split("\"", 3)[1];
			}
			else {
				importStr = importStr.split("import ", 2)[1];
			}
			importsParsed.put(importLine.getKey(), importStr);
		}
		return importsParsed;
	}


	/**
	 * @return associates line numbers containing import statements with those lines from {@code lines} after calling {@link String#trim()} on each
	 */
	public static final Map<Integer, String> findImportLines(List<String> lines) {
		Map<Integer, String> importLines = new HashMap<>();

		for(int i = 0, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			String lineTrim = line.trim();
			// search for lines that could mark the beginning of the import section (blank lines and 'delimiters' definition at beginning of a file)
			// see https://theantlrguy.atlassian.net/wiki/display/ST4/Group+file+syntax
			if(lineTrim.length() == 0 || lineTrim.startsWith("delimiters")) {
				continue;
			}
			boolean startsWithImport = lineTrim.startsWith("import");
			// import statement line
			if(startsWithImport) {
				importLines.put(i, lineTrim);
			}
			// first if statement filters out empty lines, if we reach this, it's a non 'delimiters', non 'import', non-blank line
			// i.e. the imports section of the file has ended
			if(!startsWithImport) {
				// end the loop
				break;
			}
		}
		return importLines;
	}


	public static TemplateImports emptyInst() {
		return _emptyInst;
	}


	public static TemplateImports fromCommonTemplateFiles(String javaClassAlias, boolean trimImportAliasNameQuotes) {
		TemplateImports importsInst = new TemplateImports(trimImportAliasNameQuotes);
		importsInst.importAliasesToFullPath.put(javaClassAlias, CommonTemplateFiles.JavaClass.getPathString());
		return importsInst;
	}

}
