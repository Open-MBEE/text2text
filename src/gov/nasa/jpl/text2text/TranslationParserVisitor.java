// Generated from TranslationParser.g4 by ANTLR 4.7.1
package gov.nasa.jpl.text2text;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TranslationParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TranslationParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TranslationParser#templatefile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatefile(TranslationParser.TemplatefileContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#optionspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionspec(TranslationParser.OptionspecContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#optionname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionname(TranslationParser.OptionnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#optionvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionvalue(TranslationParser.OptionvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#templatedef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatedef(TranslationParser.TemplatedefContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#templateoption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateoption(TranslationParser.TemplateoptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(TranslationParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#fielddef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFielddef(TranslationParser.FielddefContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#fieldname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldname(TranslationParser.FieldnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(TranslationParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#fieldmodifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldmodifiers(TranslationParser.FieldmodifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#fieldflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldflag(TranslationParser.FieldflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#noduplicates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoduplicates(TranslationParser.NoduplicatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#triggerflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTriggerflag(TranslationParser.TriggerflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#requiredflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequiredflag(TranslationParser.RequiredflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#keywordflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordflag(TranslationParser.KeywordflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#rawflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawflag(TranslationParser.RawflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#bodyflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyflag(TranslationParser.BodyflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#multiflag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiflag(TranslationParser.MultiflagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#alternatesanitizer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternatesanitizer(TranslationParser.AlternatesanitizerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#bodyjoiner}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyjoiner(TranslationParser.BodyjoinerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#formatstring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatstring(TranslationParser.FormatstringContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#valuejoiner}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuejoiner(TranslationParser.ValuejoinerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(TranslationParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#recursivepath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursivepath(TranslationParser.RecursivepathContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#recursiveuppath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursiveuppath(TranslationParser.RecursiveuppathContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(TranslationParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#tagelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagelement(TranslationParser.TagelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#roottag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoottag(TranslationParser.RoottagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#reftag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReftag(TranslationParser.ReftagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#heretag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeretag(TranslationParser.HeretagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#indexelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexelement(TranslationParser.IndexelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(TranslationParser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#attributefilterelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributefilterelement(TranslationParser.AttributefilterelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#attributevalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributevalue(TranslationParser.AttributevalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#doubleattributefilterelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleattributefilterelement(TranslationParser.DoubleattributefilterelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#negationfilterelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegationfilterelement(TranslationParser.NegationfilterelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#regexfilterelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexfilterelement(TranslationParser.RegexfilterelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#alternationelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternationelement(TranslationParser.AlternationelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#repeatelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatelement(TranslationParser.RepeatelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TranslationParser#branches}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranches(TranslationParser.BranchesContext ctx);
}