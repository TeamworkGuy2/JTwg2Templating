package twg2.template.templateString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @param <E> the type of elements insertable into the template string
 * @author TeamworkGuy2
 * @since 2015-5-31
 */
public class TemplateStringBuilder<E> {
	private Class<E> elemClass;
	private List<Object> objs;


	{
		this.objs = new ArrayList<>();
	}


	public TemplateStringBuilder(Class<E> clazz) {
		this.elemClass = clazz;
	}


	public TemplateStringBuilder<E> and(String str) {
		this.objs.add(str);
		return this;
	}


	@SafeVarargs
	public final TemplateStringBuilder<E> and(String... strs) {
		for(String str : strs) {
			this.objs.add(str);
		}
		return this;
	}


	public TemplateStringBuilder<E> and(E obj) {
		this.objs.add(obj);
		return this;
	}


	@SafeVarargs
	public final TemplateStringBuilder<E> and(E... objs) {
		for(E obj : objs) {
			this.objs.add(obj);
		}
		return this;
	}


	// package-private
	List<Object> getRawObjects() {
		return objs;
	}


	public void forEach(Consumer<String> strPartConsumer, Consumer<E> elemPartConsumer) {
		for(int i = 0, size = objs.size(); i < size; i++) {
			Object obj = objs.get(i);
			if(elemClass.isInstance(obj)) {
				@SuppressWarnings("unchecked")
				E elem = (E)obj;
				elemPartConsumer.accept(elem);
			}
			else if(obj instanceof String) {
				strPartConsumer.accept((String)obj);
			}
			else {
				throw new IllegalStateException("cannot process string template object " + (obj != null ? ("of type " + obj.getClass()) : obj));
			}
		}
	}

}
