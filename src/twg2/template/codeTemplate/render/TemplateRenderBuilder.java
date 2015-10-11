package twg2.template.codeTemplate.render;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.stringtemplate.v4.ST;

import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.ClassLocation;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;

/**
 * @author TeamworkGuy2
 * @since 2015-6-6
 */
public class TemplateRenderBuilder {


	public static interface SetParamsOrDstStep<R extends SetDstStep> extends SetDstBeforeParamsStep, SetParamsStep<R> {
	}


	public static interface SetDstBeforeParamsStep {
		SetParamsStep<Builder> writeDst(ClassLocation locationInfo);

		SetParamsStep<Builder> writeDst(Appendable out);
	}


	public static interface SetParamsStep<R extends SetDstStep> {
		R addParam(String name, Object value);

		R addParams(Map<String, Object> params);

		R setParams(Map<String, Object> params);
	}


	public static interface SetDstStep {
		Builder writeDst(ClassLocation locationInfo);

		Builder writeDst(Appendable out);
	}




	private static String defaultSourceParamName = "var";

	public static String getDefaultArgName() {
		return defaultSourceParamName;
	}


	public static final SetParamsOrDstStep<Builder> newInst() {
		return new Builder();
	}


	public static <T extends ClassInfo> Builder fromClassTemplate(T tmpl) {
		return new Builder().setSourceParam(tmpl);
	}


	public static <T extends PrimitiveTypeClassTemplate> Builder fromPrimitiveClassTemplate(T tmpl) {
		return new Builder().setSourceParam(tmpl);
	}


	public static class Builder implements SetDstBeforeParamsStep, SetParamsStep<Builder>, SetDstStep, SetParamsOrDstStep<Builder> {
		private Map<String, Object> params = new HashMap<>();
		private Appendable dst;


		public Builder() {
		}


		public Builder copy() {
			Builder copy = new Builder();
			copy.params = new HashMap<>(this.params);
			copy.dst = this.dst;
			return copy;
		}


		public Object getSourceParam() {
			return params.get(defaultSourceParamName);
		}


		public Builder setSourceParam(Object src) {
			this.addParam(defaultSourceParamName, src);
			return this;
		}


		public Object getParam(String paramName) {
			return this.params.get(paramName);
		}


		public Map<String, Object> getParams() {
			return this.params;
		}


		@Override
		public Builder addParam(String name, Object param) {
			this.params.put(name, param);
			return this;
		}


		@Override
		public Builder addParams(Map<String, Object> params) {
			for(Entry<String, Object> param : params.entrySet()) {
				this.params.put(param.getKey(), param.getValue());
			}
			return this;
		}


		@Override
		public Builder setParams(Map<String, Object> params) {
			this.params = params;
			return this;
		}


		public Appendable getDst() {
			return dst;
		}


		@Override
		public Builder writeDst(ClassLocation locationInfo) {
			this.dst = TemplateFilesIo.getDefaultInst().getSrcRelativeStream(locationInfo);
			return this;
		}


		@Override
		public Builder writeDst(Appendable out) {
			this.dst = out;
			return this;
		}


		public void render(ST template) {
			params.forEach(template::add);

			StringTemplatesUtil.render(template, dst);

			params.keySet().forEach(template::remove);
		}

	}

}
