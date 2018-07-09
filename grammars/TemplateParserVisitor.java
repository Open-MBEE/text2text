// Generated from TemplateParser.g4 by ANTLR 4.7.1
package gov.nasa.jpl.text2text;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TemplateParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TemplateParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TemplateParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(TemplateParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(TemplateParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#component}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponent(TemplateParser.ComponentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(TemplateParser.FieldContext ctx);
}