package cn.bran.japid.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;

public class ControllerUtilsTest {

	@Test
	public void testRender() {
		RenderResult render;
		
		render = (RenderResult) RenderInvokerUtils.render(new Foo(null), "hi", new Integer(12));
		assertNotNull(render);

		render = (RenderResult) RenderInvokerUtils.render(new Foo(null), "hi", 12);
		assertNotNull(render);

		render = (RenderResult) RenderInvokerUtils.render(new Foo(null), "hi", null);
		assertNotNull(render);
		
		try {
			render = (RenderResult) RenderInvokerUtils.render(new Foo2(null), 12);
		} catch (Exception e) {
			System.out.println(e);
		}

		render = (RenderResult) RenderInvokerUtils.render(new Foo2(null), new ArrayList<String>());
		assertNotNull(render);

		testSingleNull();

		testEmptyArgs();
		
	}

	/**
	 * 
	 */
	@Test
	public void testSingleNull() {
		RenderResult render;
		// if cast to Object, the null is treated as an argument.
		// Otherwise the varargs is set to null. 
		render = (RenderResult) RenderInvokerUtils.render(new Foo2(null), (Object)null);
		assertNotNull(render);
	}

	/**
	 * 
	 */
	@Test
	public void testEmptyArgs() {
		RenderResult render;
		render = (RenderResult) RenderInvokerUtils.render(new Bar(null));
		assertNotNull(render);
	}

	static class Foo extends JapidTemplateBase {
		/* based on https://github.com/branaway/Japid/issues/12
		 */
		public static final String[] argNames = new String[] {/* args of the template*/"str","i"  };
		public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "Integer"  };
		public static java.lang.reflect.Method renderMethod = getRenderMethod(cn.bran.japid.util.ControllerUtilsTest.Foo.class);
		{
			setRenderMethod(renderMethod);
			setArgNames(argNames);
			setArgTypes(argTypes);
		}
		public Foo(StringBuilder out) {
			super(out);
		}

		@Override
		protected void doLayout() {
		}
		
		public RenderResult render(String str, Integer i) {
			return new RenderResult();
		}
		
	}

	static class Foo2 extends Foo{
		public static final String[] argNames = new String[] {/* args of the template*/"lists", };
		public static final String[] argTypes = new String[] {/* arg types of the template*/"List<String>", };
		public static java.lang.reflect.Method renderMethod = getRenderMethod(cn.bran.japid.util.ControllerUtilsTest.Foo2.class);
		{
			setRenderMethod(renderMethod);
			setArgNames(argNames);
			setArgTypes(argTypes);
		}
		public Foo2(StringBuilder out) {
			super(out);
		}

		public RenderResult render(List<String> lists) {
			return new RenderResult();
		}
		
	}

	static class Bar extends Foo{
		public static final String[] argNames = new String[] {/* args of the template*/ };
		public static final String[] argTypes = new String[] {/* arg types of the template*/};
		public static java.lang.reflect.Method renderMethod = getRenderMethod(cn.bran.japid.util.ControllerUtilsTest.Bar.class);
		{
			setRenderMethod(renderMethod);
			setArgNames(argNames);
			setArgTypes(argTypes);
		}
		public Bar(StringBuilder out) {
			super(out);
		}
		
		public RenderResult render() {
			return new RenderResult();
		}
		
	}
}
