package codeTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CodeTemplates {

	private CodeTemplates() { throw new AssertionError("cannot instantiate static class CodeTemplates"); }


	/** Replace instances of a specified string pattern with an array of replacement strings.
	 * For example if the {@code source} string was {@code "private static final volatile %var;\n"}<br/>
	 * and {@code replace} = {@code ["%var"]}<br/>
	 * and {@code replacement} = {@code ["byte", "int", "long"]}<br/>
	 * the result would be <pre>{@code "private static final volatile byte;
	 * private static final volatile int;
	 * private static final volatile long;"}</pre>
	 * 
	 * or for example if {@code source} = {@code "%var = ((%type)thing.get(#index#));\n"}<br/>
	 * and {@code replace} = {@code ["%var", "%type"]}<br/>
	 * and {@code replacements} = {@code [["b1", "byte"], ["i1", "int"], ["l1", "long"]]}<br/>
	 * and the dynamicReplacer returns.
	 * the result would be <pre>{@code "b1 = ((byte)thing.get());}</pre>
	 * 
	 * @param source the source string to replace matching strings in
	 * @param replace the string to replace, must prepend special regex characters with '\'
	 * @param replacements the array of replacement strings to replace matching strings with, must prepend
	 * special regex characters with '\'
	 * @param restartReplacements if there are more <code>replace</code> strings than <code>replacement</code>
	 * strings, then true reuses <code>replacement</code> strings starting from index 0 and continuing back through
	 * the replacement array, if false then reuses  <code>replacement[replacement.length-1]</code> as the
	 * replacement for further <code>replace</code> strings.
	 * @param counterMarker for each instance (n) of this string in the {@code source} string it is replaced with
	 * the integer {@code firstCounterMarker+(n*counterStride)}.
	 * @param firstCounterMarker the value to replace the first {@code counterMarker} with, each subsequent
	 * replacement is replaced with a value equal to {@code firstCounterMarker+(n*counterStride)}.
	 * @param counterStride the value to add to {@code firstCounterMarker} for each replacement after the first replacement.
	 * @return the resulting string after replacing the matching string as described.
	 */
	public static final String generateFrom(String source, String[] replace, String[][] replacements, boolean restartReplacements,
			String counterMarker, int firstCounterMarker, int counterStride) {
		StringBuilder result = new StringBuilder(source.length()*replacements.length);
		String str = null;
		for(int i = 0; i < replacements.length; i++) {
			str = replace(source, replace, replacements[i], restartReplacements,
					counterMarker, firstCounterMarker, counterStride);
			result.append(str);
		}
		return result.toString();
	}


	/** Replace the specified series of string matches from the source string with the specified series of
	 * replacement strings.<br/>
	 * For example if the {@code source} string was {@code "%var a = (%var)value;"}<br/>
	 * and {@code replace} = {@code ["%var"]}<br/>
	 * and {@code replacement} = {@code ["int", "byte"]}<br/>
	 * the result would be <pre>{@code "int a = (byte)value;"}</pre>
	 * 
	 * @param source the source string to replace matching stings from
	 * @param replace the list of string patterns to replace
	 * @param replacement the list of strings to replace matching patterns with
	 * @param restartReplacements if there are more <code>replace</code> strings than <code>replacement</code>
	 * strings, then true reuses <code>replacement</code> strings starting from index 0 and continuing back through
	 * the replacement array, if false then reuses  <code>replacement[replacement.length-1]</code> as the
	 * replacement for further <code>replace</code> strings.
	 * @return the source string with each instance of <code>replace[i]</code> replaced with <code>replacement[i]</code>.
	 */
	public static final String replace(String source, String[] replace, String[] replacement, boolean restartReplacements) {
		return replace(source, replace, replacement, restartReplacements, null, 0, 0);
	}


	/** Tokenize a string and extract the specified groups. See {@link Pattern} 'Groups and capturing'.<br/>
	 * <br/>
	 * For example if {@code source = "1. stuff\t123.00\t36.00"}<br/>
	 * and {@code regex = "(.+\\t)(.+\\t)(.+)"}<br/>
	 * and {@code regexIndices = [2, 3]}<br/>
	 * the result would be {@code ["123.00", "36.00"]}.<br/>
	 * <br/>
	 * Or for example if {@code source = "KEY_EVENT(KeyListener.class, KeyEvent.class)"}<br/>
	 * and {@code regex} = {@code "(.+)\\((.+),(.+),(.+)\\)"}<br/>
	 * and {@code regexIndices} = {@code [1, 2, 3]}<br/>
	 * the result would be {@code ["KEY_EVENT", "KeyListener.class", "KeyEvent.class"]}.<br/>
	 * 
	 * @param source the source string to divide based on the regex
	 * @param regex the regular expression (should contain at least as many capture groups as the
	 * highest index in {@code regexIndices}).
	 * @param regexIndices an array of indices representing which capture groups to return from the
	 * parsed {@code source} string. If this array is {@code null} or {@code length == 0} then all
	 * regex groups parsed from the source string are returned.
	 * @return an array of strings equal in length to the number of items in {@code regexIndices}
	 * where index i contains the parsed regex capture group i.
	 * @see Pattern
	 */
	public static final String[] tokenize(String source, String regex, int[] regexIndices) {
		boolean saveAllGroups = (regexIndices == null || regexIndices.length == 0);
		ArrayList<String> allGroups = null;
		if(saveAllGroups) { allGroups = new ArrayList<String>(); }
		Pattern p = Pattern.compile(regex);
		//System.out.println("Pattern: " + p.toString());
		Matcher m = p.matcher(source);
		String[] results = null;
		//System.out.println("Tokenize: " + regex + ", source=" + source);
		if(m.find()) {
			results = new String[regexIndices.length];
			int index = 0;
			//System.out.println("Group count: " + m.groupCount());
			for(int i = 0, count = m.groupCount(); i <= count; i++) {
				//System.out.println(i + ": " + m.group(i));
				if(saveAllGroups) {
					allGroups.add(m.group(i));
				}
				else if(i == regexIndices[index]) {
					results[index] = m.group(i);
					index++;
				}
			}
		}
		//System.out.println("Tokenize - done: " + m.hitEnd());
		if(saveAllGroups) {
			return allGroups.toArray(new String[allGroups.size()]);
		}
		else {
			return results;
		}
	}


	/** Replace the specified series of string matches from the source string with the specified series of
	 * replacement strings.<br/>
	 * For example if the {@code source} string was {@code "%var a = (%var)value;"}<br/>
	 * and {@code replace} = {@code ["%var"]}<br/>
	 * and {@code replacement} = {@code ["int", "byte"]}<br/>
	 * the result would be <pre>{@code "int a = (byte)value;"}</pre>
	 * 
	 * @param source the source string to replace matching stings from
	 * @param replace the list of string patterns to replace
	 * @param replacement the list of strings to replace matching patterns with
	 * @param restartReplacements if there are more <code>replace</code> strings than <code>replacement</code>
	 * strings, then true reuses <code>replacement</code> strings starting from index 0 and continuing back through
	 * the replacement array, if false then reuses  <code>replacement[replacement.length-1]</code> as the
	 * replacement for further <code>replace</code> strings.
	 * TODO implement counter marker and replacement
	 * @param counterMarker for each instance (n) of this string in the {@code source} string it is replaced with
	 * the integer {@code firstCounterMarker+(n*counterStride)}.
	 * @param firstCounterMarker the value to replace the first {@code counterMarker} with, each subsequent
	 * replacement is replaced with a value equal to {@code firstCounterMarker+(n*counterStride)}.
	 * @param counterStride the value to add to {@code firstCounterMarker} for each replacement after the first replacement.
	 * @return the source string with each instance of <code>replace[i]</code> replaced with <code>replacement[i]</code>.
	 */
	public static final String replace(String source, String[] replace, String[] replacement, boolean restartReplacements,
			String counterMarker, int firstCounterMarker, int counterStride) {
		String stringSource = source;
		String replaceStr = null;
		StringBuilder result = new StringBuilder(stringSource.length());
		for(int strNum = 0; strNum < replace.length; strNum++) {
			result.setLength(0);
			Pattern sourceFind = Pattern.compile(replace[strNum]);
			String[] strings = sourceFind.split(stringSource);
			int patternCount = strings.length;
			int replaceCount = replacement.length;
			// If there are enough or more matching strings than replacement strings, overwrite matching strings with replacement
			// strings and reuse replacement strings when we get to the end of the replacement string array
			if(restartReplacements == true) {
				replaceStr = replacement[strNum%replaceCount];
			}
			// If there are more matching strings than replacement strings, overwrite matching strings with replacement
			// strings and reuse the last replacement string when we get to the end of the replacement string array
			else {
				replaceStr = replacement[((strNum > replaceCount-1) ? replaceCount-1 : strNum)];
			}
			for(int i = 0; i < patternCount-1; i++) {
				result.append(strings[i]);
				result.append(replaceStr);
			}
			result.append(strings[patternCount-1]);

			stringSource = result.toString();
		}
		// Return the resulting string with the matching strings replaced with the replacement strings
		return stringSource;
	}


	public static void main(String[] args) throws IOException {

		/*
		String source = "public boolean put(K key, $val$ value) {\n" +
			"	keys.add(key);\n" +
			"	boolean added = values.add(values);\n" +
			"	return added;\n" +
			"}\n\n";
		//String[][] replacements = new String[][] {{"int", "vint"}, {"float", "vfloat"}, {"String", "vString"}, };
		String[][] replacements = new String[][] {{"byte"}, {"short"}, {"int"}, {"long"}, {"float"}, {"double"}, {"String"}};
		String[] matches = new String[] {"\\$val\\$"}; // '\\' because $ signs are special characters in regex
		String result = CodeTemplates.generateFrom(source, matches, replacements, true, null, 0, 0);
		System.out.println(result);
		*/

		/*
		String[] str = CodeTemplates.tokenize("1. 324 stuff with other\t123.00\t5634.00",
				"(.+\\t)(.+\\t)(.+)",
				new int[] {2, 3});
		System.out.println(Arrays.toString(str));
		/*

		String code = "COLLISION_EVENT(CollisionEventListener.class, CollisionListenerRegisterable.class, CollisionEvent.class),\n" +
				"ENTITY_EVENT(EntityListener.class, EntityListenerRegisterable.class, EntityEvent.class),\n" +
				"INPUT_CONTROLS_EVENT(InputControlsListener.class, InputControlsListenerRegisterable.class, InputControlsEvent.class),\n" +
				"KEY_EVENT(KeyListener.class, KeyListenerRegisterable.class, KeyEvent.class),\n" +
				"MOVEMENT_EVENT(MovementListener.class, MovementListenerRegisterable.class, MovementEvent.class),\n" +
				"POINTER_EVENT(PointerListener.class, PointerListenerRegisterable.class, PointerEvent.class),\n" +
				"UI_EVENT(UiEventListener.class, UiListenerRegisterable.class, UiEvent.class),\n";
		String[] methods = {
				"@Override public Class<EntityEvent> getEventType() { return this; }",
				"@Override public Class<EventListener> getListenerType() { return this; }",
				"@Override public Class<RegisterableListener<?>> getRegisterableType() {\n\t\t@SuppressWarnings({ \"unchecked\", \"rawtypes\" })  Class<RegisterableListener<?>> clazz = (Class<RegisterableListener<?>>)(Class<? extends RegisterableListener>)this;\n\t\treturn clazz;\n\t}"};
		String[] parts = code.split(",\n");
		for(String part : parts) {
			String[] types = CodeTemplates.tokenize(part, "(.+)\\((.+), (.+), (.+)\\)", new int[] {1, 2, 3, 4});
			String method = null;
			System.out.println(types[0] + "(" + types[3] + ", " + types[1] + ", " + types[2] + ") {");
			method = CodeTemplates.replace(methods[0], new String[] {"EntityEvent", "this"}, new String[] {types[3].split("\\.class")[0], types[3]}, true);
			System.out.println("\t" + method);
			method = CodeTemplates.replace(methods[1], new String[] {"EventListener", "this"}, new String[] {types[1].split("\\.class")[0], types[1]}, true);
			System.out.println("\t" + method);
			method = CodeTemplates.replace(methods[2], new String[] {"RegisterableListener", "this"}, new String[] {types[2].split("\\.class")[0], types[2]}, true);
			System.out.println("\t" + method);
			System.out.println("},");
			//System.out.println("Split: " + part);
			//System.out.println("-into: " + java.util.Arrays.toString(types));
		}
		*/
	}

}
