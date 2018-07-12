// Generated from TranslationParser.g4 by ANTLR 4.7.1
package gov.nasa.jpl.text2text;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TranslationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STARTTEMPLATE=1, TRIGGER=2, REQUIRED=3, KEYWORD=4, RAW=5, BODY=6, MULTIVALUE=7, 
		NODUPLICATES=8, COMMENT=9, STRING=10, QUESTION=11, OPENROUND=12, CLOSEROUND=13, 
		WILDCARD=14, DOLLAR=15, DOT=16, CARET=17, TILDE=18, BANG=19, EQUALS=20, 
		COMMA=21, COLON=22, OPENSQUARE=23, CLOSESQUARE=24, DOC=25, REF=26, HERE=27, 
		LIB=28, IDENTIFIER=29, NATURALNUM=30, WS=31, ENDTEMPLATE=32, TEMPLATE_CHAR=33;
	public static final int
		RULE_templatefile = 0, RULE_optionspec = 1, RULE_optionname = 2, RULE_optionvalue = 3, 
		RULE_templatedef = 4, RULE_templateoption = 5, RULE_template = 6, RULE_fielddef = 7, 
		RULE_fieldname = 8, RULE_keyword = 9, RULE_fieldmodifiers = 10, RULE_fieldflag = 11, 
		RULE_noduplicates = 12, RULE_triggerflag = 13, RULE_requiredflag = 14, 
		RULE_keywordflag = 15, RULE_rawflag = 16, RULE_bodyflag = 17, RULE_multiflag = 18, 
		RULE_alternatesanitizer = 19, RULE_bodyjoiner = 20, RULE_formatstring = 21, 
		RULE_valuejoiner = 22, RULE_path = 23, RULE_recursivepath = 24, RULE_recursiveuppath = 25, 
		RULE_element = 26, RULE_tagelement = 27, RULE_roottag = 28, RULE_reftag = 29, 
		RULE_heretag = 30, RULE_indexelement = 31, RULE_index = 32, RULE_attributefilterelement = 33, 
		RULE_attributevalue = 34, RULE_doubleattributefilterelement = 35, RULE_negationfilterelement = 36, 
		RULE_regexfilterelement = 37, RULE_alternationelement = 38, RULE_repeatelement = 39, 
		RULE_branches = 40;
	public static final String[] ruleNames = {
		"templatefile", "optionspec", "optionname", "optionvalue", "templatedef", 
		"templateoption", "template", "fielddef", "fieldname", "keyword", "fieldmodifiers", 
		"fieldflag", "noduplicates", "triggerflag", "requiredflag", "keywordflag", 
		"rawflag", "bodyflag", "multiflag", "alternatesanitizer", "bodyjoiner", 
		"formatstring", "valuejoiner", "path", "recursivepath", "recursiveuppath", 
		"element", "tagelement", "roottag", "reftag", "heretag", "indexelement", 
		"index", "attributefilterelement", "attributevalue", "doubleattributefilterelement", 
		"negationfilterelement", "regexfilterelement", "alternationelement", "repeatelement", 
		"branches"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "'?'", 
		"'('", "')'", "'*'", "'$'", "'.'", "'^'", "'~'", "'!'", "'='", "','", 
		"':'", "'['", "']'", "'DOC'", "'REF'", "'HERE'", "'LIB'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STARTTEMPLATE", "TRIGGER", "REQUIRED", "KEYWORD", "RAW", "BODY", 
		"MULTIVALUE", "NODUPLICATES", "COMMENT", "STRING", "QUESTION", "OPENROUND", 
		"CLOSEROUND", "WILDCARD", "DOLLAR", "DOT", "CARET", "TILDE", "BANG", "EQUALS", 
		"COMMA", "COLON", "OPENSQUARE", "CLOSESQUARE", "DOC", "REF", "HERE", "LIB", 
		"IDENTIFIER", "NATURALNUM", "WS", "ENDTEMPLATE", "TEMPLATE_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TranslationParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TranslationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TemplatefileContext extends ParserRuleContext {
		public List<OptionspecContext> optionspec() {
			return getRuleContexts(OptionspecContext.class);
		}
		public OptionspecContext optionspec(int i) {
			return getRuleContext(OptionspecContext.class,i);
		}
		public List<TemplatedefContext> templatedef() {
			return getRuleContexts(TemplatedefContext.class);
		}
		public TemplatedefContext templatedef(int i) {
			return getRuleContext(TemplatedefContext.class,i);
		}
		public TemplatefileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templatefile; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTemplatefile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatefileContext templatefile() throws RecognitionException {
		TemplatefileContext _localctx = new TemplatefileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_templatefile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(82);
				optionspec();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STARTTEMPLATE || _la==OPENROUND) {
				{
				{
				setState(88);
				templatedef();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionspecContext extends ParserRuleContext {
		public OptionnameContext optionname() {
			return getRuleContext(OptionnameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TranslationParser.COLON, 0); }
		public List<OptionvalueContext> optionvalue() {
			return getRuleContexts(OptionvalueContext.class);
		}
		public OptionvalueContext optionvalue(int i) {
			return getRuleContext(OptionvalueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TranslationParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TranslationParser.COMMA, i);
		}
		public OptionspecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionspec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitOptionspec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionspecContext optionspec() throws RecognitionException {
		OptionspecContext _localctx = new OptionspecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_optionspec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			optionname();
			setState(95);
			match(COLON);
			setState(96);
			optionvalue();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(97);
				match(COMMA);
				setState(98);
				optionvalue();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionnameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TranslationParser.IDENTIFIER, 0); }
		public OptionnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitOptionname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionnameContext optionname() throws RecognitionException {
		OptionnameContext _localctx = new OptionnameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_optionname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionvalueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public OptionvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionvalue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitOptionvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionvalueContext optionvalue() throws RecognitionException {
		OptionvalueContext _localctx = new OptionvalueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplatedefContext extends ParserRuleContext {
		public TerminalNode STARTTEMPLATE() { return getToken(TranslationParser.STARTTEMPLATE, 0); }
		public TemplateContext template() {
			return getRuleContext(TemplateContext.class,0);
		}
		public TerminalNode ENDTEMPLATE() { return getToken(TranslationParser.ENDTEMPLATE, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public List<FielddefContext> fielddef() {
			return getRuleContexts(FielddefContext.class);
		}
		public FielddefContext fielddef(int i) {
			return getRuleContext(FielddefContext.class,i);
		}
		public List<TemplateoptionContext> templateoption() {
			return getRuleContexts(TemplateoptionContext.class);
		}
		public TemplateoptionContext templateoption(int i) {
			return getRuleContext(TemplateoptionContext.class,i);
		}
		public TemplatedefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templatedef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTemplatedef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatedefContext templatedef() throws RecognitionException {
		TemplatedefContext _localctx = new TemplatedefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_templatedef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPENROUND) {
				{
				setState(108);
				match(OPENROUND);
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NODUPLICATES) {
					{
					{
					setState(109);
					templateoption();
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(115);
				match(CLOSEROUND);
				}
			}

			setState(118);
			match(STARTTEMPLATE);
			setState(119);
			template();
			setState(120);
			match(ENDTEMPLATE);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRIGGER) | (1L << REQUIRED) | (1L << KEYWORD) | (1L << RAW) | (1L << BODY) | (1L << MULTIVALUE) | (1L << NODUPLICATES) | (1L << DOC) | (1L << REF) | (1L << HERE) | (1L << LIB) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(121);
				fielddef();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateoptionContext extends ParserRuleContext {
		public NoduplicatesContext noduplicates() {
			return getRuleContext(NoduplicatesContext.class,0);
		}
		public TemplateoptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateoption; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTemplateoption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateoptionContext templateoption() throws RecognitionException {
		TemplateoptionContext _localctx = new TemplateoptionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_templateoption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			noduplicates();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateContext extends ParserRuleContext {
		public List<TerminalNode> TEMPLATE_CHAR() { return getTokens(TranslationParser.TEMPLATE_CHAR); }
		public TerminalNode TEMPLATE_CHAR(int i) {
			return getToken(TranslationParser.TEMPLATE_CHAR, i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEMPLATE_CHAR) {
				{
				{
				setState(129);
				match(TEMPLATE_CHAR);
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FielddefContext extends ParserRuleContext {
		public FieldnameContext fieldname() {
			return getRuleContext(FieldnameContext.class,0);
		}
		public List<TerminalNode> OPENROUND() { return getTokens(TranslationParser.OPENROUND); }
		public TerminalNode OPENROUND(int i) {
			return getToken(TranslationParser.OPENROUND, i);
		}
		public List<TerminalNode> CLOSEROUND() { return getTokens(TranslationParser.CLOSEROUND); }
		public TerminalNode CLOSEROUND(int i) {
			return getToken(TranslationParser.CLOSEROUND, i);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public FieldmodifiersContext fieldmodifiers() {
			return getRuleContext(FieldmodifiersContext.class,0);
		}
		public FormatstringContext formatstring() {
			return getRuleContext(FormatstringContext.class,0);
		}
		public FielddefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fielddef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitFielddef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FielddefContext fielddef() throws RecognitionException {
		FielddefContext _localctx = new FielddefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fielddef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			fieldname();
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(136);
				match(OPENROUND);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRIGGER) | (1L << REQUIRED) | (1L << KEYWORD) | (1L << RAW) | (1L << BODY) | (1L << MULTIVALUE))) != 0)) {
					{
					setState(137);
					fieldmodifiers();
					}
				}

				setState(140);
				match(CLOSEROUND);
				}
				break;
			}
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(143);
				match(OPENROUND);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(144);
					formatstring();
					}
				}

				setState(147);
				match(CLOSEROUND);
				}
				break;
			}
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(150);
				path();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldnameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TranslationParser.IDENTIFIER, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public FieldnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitFieldname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldnameContext fieldname() throws RecognitionException {
		FieldnameContext _localctx = new FieldnameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fieldname);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(IDENTIFIER);
				}
				break;
			case TRIGGER:
			case REQUIRED:
			case KEYWORD:
			case RAW:
			case BODY:
			case MULTIVALUE:
			case NODUPLICATES:
			case DOC:
			case REF:
			case HERE:
			case LIB:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				keyword();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public FieldflagContext fieldflag() {
			return getRuleContext(FieldflagContext.class,0);
		}
		public TemplateoptionContext templateoption() {
			return getRuleContext(TemplateoptionContext.class,0);
		}
		public TerminalNode DOC() { return getToken(TranslationParser.DOC, 0); }
		public TerminalNode REF() { return getToken(TranslationParser.REF, 0); }
		public TerminalNode LIB() { return getToken(TranslationParser.LIB, 0); }
		public TerminalNode HERE() { return getToken(TranslationParser.HERE, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_keyword);
		try {
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRIGGER:
			case REQUIRED:
			case KEYWORD:
			case RAW:
			case BODY:
			case MULTIVALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				fieldflag();
				}
				break;
			case NODUPLICATES:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				templateoption();
				}
				break;
			case DOC:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				match(DOC);
				}
				break;
			case REF:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				match(REF);
				}
				break;
			case LIB:
				enterOuterAlt(_localctx, 5);
				{
				setState(161);
				match(LIB);
				}
				break;
			case HERE:
				enterOuterAlt(_localctx, 6);
				{
				setState(162);
				match(HERE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldmodifiersContext extends ParserRuleContext {
		public List<FieldflagContext> fieldflag() {
			return getRuleContexts(FieldflagContext.class);
		}
		public FieldflagContext fieldflag(int i) {
			return getRuleContext(FieldflagContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TranslationParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TranslationParser.COMMA, i);
		}
		public FieldmodifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldmodifiers; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitFieldmodifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldmodifiersContext fieldmodifiers() throws RecognitionException {
		FieldmodifiersContext _localctx = new FieldmodifiersContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fieldmodifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			fieldflag();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(166);
				match(COMMA);
				setState(167);
				fieldflag();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldflagContext extends ParserRuleContext {
		public TriggerflagContext triggerflag() {
			return getRuleContext(TriggerflagContext.class,0);
		}
		public RequiredflagContext requiredflag() {
			return getRuleContext(RequiredflagContext.class,0);
		}
		public KeywordflagContext keywordflag() {
			return getRuleContext(KeywordflagContext.class,0);
		}
		public RawflagContext rawflag() {
			return getRuleContext(RawflagContext.class,0);
		}
		public BodyflagContext bodyflag() {
			return getRuleContext(BodyflagContext.class,0);
		}
		public MultiflagContext multiflag() {
			return getRuleContext(MultiflagContext.class,0);
		}
		public FieldflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitFieldflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldflagContext fieldflag() throws RecognitionException {
		FieldflagContext _localctx = new FieldflagContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fieldflag);
		try {
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRIGGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				triggerflag();
				}
				break;
			case REQUIRED:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				requiredflag();
				}
				break;
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(175);
				keywordflag();
				}
				break;
			case RAW:
				enterOuterAlt(_localctx, 4);
				{
				setState(176);
				rawflag();
				}
				break;
			case BODY:
				enterOuterAlt(_localctx, 5);
				{
				setState(177);
				bodyflag();
				}
				break;
			case MULTIVALUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(178);
				multiflag();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoduplicatesContext extends ParserRuleContext {
		public TerminalNode NODUPLICATES() { return getToken(TranslationParser.NODUPLICATES, 0); }
		public NoduplicatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noduplicates; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitNoduplicates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoduplicatesContext noduplicates() throws RecognitionException {
		NoduplicatesContext _localctx = new NoduplicatesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_noduplicates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(NODUPLICATES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriggerflagContext extends ParserRuleContext {
		public TerminalNode TRIGGER() { return getToken(TranslationParser.TRIGGER, 0); }
		public TriggerflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTriggerflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TriggerflagContext triggerflag() throws RecognitionException {
		TriggerflagContext _localctx = new TriggerflagContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_triggerflag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(TRIGGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequiredflagContext extends ParserRuleContext {
		public TerminalNode REQUIRED() { return getToken(TranslationParser.REQUIRED, 0); }
		public RequiredflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiredflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRequiredflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequiredflagContext requiredflag() throws RecognitionException {
		RequiredflagContext _localctx = new RequiredflagContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_requiredflag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(REQUIRED);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordflagContext extends ParserRuleContext {
		public TerminalNode KEYWORD() { return getToken(TranslationParser.KEYWORD, 0); }
		public KeywordflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitKeywordflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordflagContext keywordflag() throws RecognitionException {
		KeywordflagContext _localctx = new KeywordflagContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_keywordflag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(KEYWORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RawflagContext extends ParserRuleContext {
		public TerminalNode RAW() { return getToken(TranslationParser.RAW, 0); }
		public AlternatesanitizerContext alternatesanitizer() {
			return getRuleContext(AlternatesanitizerContext.class,0);
		}
		public RawflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rawflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRawflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RawflagContext rawflag() throws RecognitionException {
		RawflagContext _localctx = new RawflagContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_rawflag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(RAW);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(190);
				alternatesanitizer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyflagContext extends ParserRuleContext {
		public TerminalNode BODY() { return getToken(TranslationParser.BODY, 0); }
		public BodyjoinerContext bodyjoiner() {
			return getRuleContext(BodyjoinerContext.class,0);
		}
		public BodyflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitBodyflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyflagContext bodyflag() throws RecognitionException {
		BodyflagContext _localctx = new BodyflagContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_bodyflag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(BODY);
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(194);
				bodyjoiner();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiflagContext extends ParserRuleContext {
		public TerminalNode MULTIVALUE() { return getToken(TranslationParser.MULTIVALUE, 0); }
		public ValuejoinerContext valuejoiner() {
			return getRuleContext(ValuejoinerContext.class,0);
		}
		public MultiflagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiflag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitMultiflag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiflagContext multiflag() throws RecognitionException {
		MultiflagContext _localctx = new MultiflagContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_multiflag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(MULTIVALUE);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(198);
				valuejoiner();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlternatesanitizerContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public AlternatesanitizerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternatesanitizer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitAlternatesanitizer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternatesanitizerContext alternatesanitizer() throws RecognitionException {
		AlternatesanitizerContext _localctx = new AlternatesanitizerContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_alternatesanitizer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyjoinerContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public BodyjoinerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyjoiner; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitBodyjoiner(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyjoinerContext bodyjoiner() throws RecognitionException {
		BodyjoinerContext _localctx = new BodyjoinerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_bodyjoiner);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormatstringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public FormatstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatstring; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitFormatstring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormatstringContext formatstring() throws RecognitionException {
		FormatstringContext _localctx = new FormatstringContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_formatstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValuejoinerContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public ValuejoinerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valuejoiner; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitValuejoiner(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuejoinerContext valuejoiner() throws RecognitionException {
		ValuejoinerContext _localctx = new ValuejoinerContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_valuejoiner);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public RecursivepathContext recursivepath() {
			return getRuleContext(RecursivepathContext.class,0);
		}
		public RecursiveuppathContext recursiveuppath() {
			return getRuleContext(RecursiveuppathContext.class,0);
		}
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public BranchesContext branches() {
			return getRuleContext(BranchesContext.class,0);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_path);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(209);
				recursivepath();
				}
				break;
			case 2:
				{
				setState(210);
				recursiveuppath();
				}
				break;
			case 3:
				{
				setState(211);
				element();
				}
				break;
			}
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(214);
				branches();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecursivepathContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(TranslationParser.DOT, 0); }
		public List<TerminalNode> WILDCARD() { return getTokens(TranslationParser.WILDCARD); }
		public TerminalNode WILDCARD(int i) {
			return getToken(TranslationParser.WILDCARD, i);
		}
		public RecursivepathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recursivepath; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRecursivepath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursivepathContext recursivepath() throws RecognitionException {
		RecursivepathContext _localctx = new RecursivepathContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_recursivepath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(DOT);
			setState(218);
			match(WILDCARD);
			setState(219);
			match(WILDCARD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecursiveuppathContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(TranslationParser.DOT, 0); }
		public List<TerminalNode> CARET() { return getTokens(TranslationParser.CARET); }
		public TerminalNode CARET(int i) {
			return getToken(TranslationParser.CARET, i);
		}
		public RecursiveuppathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recursiveuppath; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRecursiveuppath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursiveuppathContext recursiveuppath() throws RecognitionException {
		RecursiveuppathContext _localctx = new RecursiveuppathContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_recursiveuppath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(DOT);
			setState(222);
			match(CARET);
			setState(223);
			match(CARET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public TagelementContext tagelement() {
			return getRuleContext(TagelementContext.class,0);
		}
		public IndexelementContext indexelement() {
			return getRuleContext(IndexelementContext.class,0);
		}
		public RegexfilterelementContext regexfilterelement() {
			return getRuleContext(RegexfilterelementContext.class,0);
		}
		public AttributefilterelementContext attributefilterelement() {
			return getRuleContext(AttributefilterelementContext.class,0);
		}
		public DoubleattributefilterelementContext doubleattributefilterelement() {
			return getRuleContext(DoubleattributefilterelementContext.class,0);
		}
		public NegationfilterelementContext negationfilterelement() {
			return getRuleContext(NegationfilterelementContext.class,0);
		}
		public AlternationelementContext alternationelement() {
			return getRuleContext(AlternationelementContext.class,0);
		}
		public RepeatelementContext repeatelement() {
			return getRuleContext(RepeatelementContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_element);
		try {
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				tagelement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				indexelement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(227);
				regexfilterelement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(228);
				attributefilterelement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(229);
				doubleattributefilterelement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(230);
				negationfilterelement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(231);
				alternationelement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(232);
				repeatelement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagelementContext extends ParserRuleContext {
		public RoottagContext roottag() {
			return getRuleContext(RoottagContext.class,0);
		}
		public ReftagContext reftag() {
			return getRuleContext(ReftagContext.class,0);
		}
		public HeretagContext heretag() {
			return getRuleContext(HeretagContext.class,0);
		}
		public TerminalNode DOT() { return getToken(TranslationParser.DOT, 0); }
		public TerminalNode CARET() { return getToken(TranslationParser.CARET, 0); }
		public TerminalNode WILDCARD() { return getToken(TranslationParser.WILDCARD, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TranslationParser.IDENTIFIER, 0); }
		public TerminalNode DOC() { return getToken(TranslationParser.DOC, 0); }
		public TerminalNode HERE() { return getToken(TranslationParser.HERE, 0); }
		public TerminalNode REF() { return getToken(TranslationParser.REF, 0); }
		public TerminalNode LIB() { return getToken(TranslationParser.LIB, 0); }
		public TagelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitTagelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagelementContext tagelement() throws RecognitionException {
		TagelementContext _localctx = new TagelementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_tagelement);
		int _la;
		try {
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOLLAR:
			case DOC:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				roottag();
				}
				break;
			case CARET:
			case REF:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				reftag();
				}
				break;
			case TILDE:
			case HERE:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				heretag();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				match(DOT);
				setState(239);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WILDCARD) | (1L << CARET) | (1L << DOC) | (1L << REF) | (1L << HERE) | (1L << LIB) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoottagContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(TranslationParser.DOLLAR, 0); }
		public TerminalNode DOC() { return getToken(TranslationParser.DOC, 0); }
		public TerminalNode COLON() { return getToken(TranslationParser.COLON, 0); }
		public RoottagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roottag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRoottag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoottagContext roottag() throws RecognitionException {
		RoottagContext _localctx = new RoottagContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_roottag);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOLLAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(DOLLAR);
				}
				break;
			case DOC:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(DOC);
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(244);
					match(COLON);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReftagContext extends ParserRuleContext {
		public TerminalNode CARET() { return getToken(TranslationParser.CARET, 0); }
		public TerminalNode REF() { return getToken(TranslationParser.REF, 0); }
		public TerminalNode COLON() { return getToken(TranslationParser.COLON, 0); }
		public ReftagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reftag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitReftag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReftagContext reftag() throws RecognitionException {
		ReftagContext _localctx = new ReftagContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_reftag);
		int _la;
		try {
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARET:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(CARET);
				}
				break;
			case REF:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
				match(REF);
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(251);
					match(COLON);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeretagContext extends ParserRuleContext {
		public TerminalNode TILDE() { return getToken(TranslationParser.TILDE, 0); }
		public TerminalNode HERE() { return getToken(TranslationParser.HERE, 0); }
		public TerminalNode COLON() { return getToken(TranslationParser.COLON, 0); }
		public HeretagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heretag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitHeretag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeretagContext heretag() throws RecognitionException {
		HeretagContext _localctx = new HeretagContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_heretag);
		int _la;
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(TILDE);
				}
				break;
			case HERE:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(HERE);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(258);
					match(COLON);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexelementContext extends ParserRuleContext {
		public TerminalNode OPENSQUARE() { return getToken(TranslationParser.OPENSQUARE, 0); }
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode CLOSESQUARE() { return getToken(TranslationParser.CLOSESQUARE, 0); }
		public IndexelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitIndexelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexelementContext indexelement() throws RecognitionException {
		IndexelementContext _localctx = new IndexelementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_indexelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(OPENSQUARE);
			setState(264);
			index();
			setState(265);
			match(CLOSESQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(TranslationParser.WILDCARD, 0); }
		public TerminalNode NATURALNUM() { return getToken(TranslationParser.NATURALNUM, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_la = _input.LA(1);
			if ( !(_la==WILDCARD || _la==NATURALNUM) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributefilterelementContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(TranslationParser.QUESTION, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TranslationParser.EQUALS, 0); }
		public AttributevalueContext attributevalue() {
			return getRuleContext(AttributevalueContext.class,0);
		}
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public AttributefilterelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributefilterelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitAttributefilterelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributefilterelementContext attributefilterelement() throws RecognitionException {
		AttributefilterelementContext _localctx = new AttributefilterelementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_attributefilterelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(QUESTION);
			setState(270);
			match(OPENROUND);
			setState(271);
			path();
			setState(272);
			match(EQUALS);
			setState(273);
			attributevalue();
			setState(274);
			match(CLOSEROUND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributevalueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TranslationParser.STRING, 0); }
		public AttributevalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributevalue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitAttributevalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributevalueContext attributevalue() throws RecognitionException {
		AttributevalueContext _localctx = new AttributevalueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_attributevalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleattributefilterelementContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(TranslationParser.QUESTION, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public List<PathContext> path() {
			return getRuleContexts(PathContext.class);
		}
		public PathContext path(int i) {
			return getRuleContext(PathContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TranslationParser.EQUALS, 0); }
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public DoubleattributefilterelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleattributefilterelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitDoubleattributefilterelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleattributefilterelementContext doubleattributefilterelement() throws RecognitionException {
		DoubleattributefilterelementContext _localctx = new DoubleattributefilterelementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_doubleattributefilterelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(QUESTION);
			setState(279);
			match(OPENROUND);
			setState(280);
			path();
			setState(281);
			match(EQUALS);
			setState(282);
			path();
			setState(283);
			match(CLOSEROUND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationfilterelementContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(TranslationParser.QUESTION, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public TerminalNode BANG() { return getToken(TranslationParser.BANG, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public NegationfilterelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negationfilterelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitNegationfilterelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegationfilterelementContext negationfilterelement() throws RecognitionException {
		NegationfilterelementContext _localctx = new NegationfilterelementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_negationfilterelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(QUESTION);
			setState(286);
			match(OPENROUND);
			setState(287);
			match(BANG);
			setState(288);
			path();
			setState(289);
			match(CLOSEROUND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexfilterelementContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(TranslationParser.QUESTION, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode TILDE() { return getToken(TranslationParser.TILDE, 0); }
		public TerminalNode EQUALS() { return getToken(TranslationParser.EQUALS, 0); }
		public AttributevalueContext attributevalue() {
			return getRuleContext(AttributevalueContext.class,0);
		}
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public RegexfilterelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexfilterelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRegexfilterelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexfilterelementContext regexfilterelement() throws RecognitionException {
		RegexfilterelementContext _localctx = new RegexfilterelementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_regexfilterelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(QUESTION);
			setState(292);
			match(OPENROUND);
			setState(293);
			path();
			setState(294);
			match(TILDE);
			setState(295);
			match(EQUALS);
			setState(296);
			attributevalue();
			setState(297);
			match(CLOSEROUND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlternationelementContext extends ParserRuleContext {
		public TerminalNode OPENSQUARE() { return getToken(TranslationParser.OPENSQUARE, 0); }
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TerminalNode CLOSESQUARE() { return getToken(TranslationParser.CLOSESQUARE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(TranslationParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TranslationParser.COMMA, i);
		}
		public AlternationelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternationelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitAlternationelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternationelementContext alternationelement() throws RecognitionException {
		AlternationelementContext _localctx = new AlternationelementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_alternationelement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(OPENSQUARE);
			setState(300);
			element();
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(301);
				match(COMMA);
				setState(302);
				element();
				}
				}
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(308);
			match(CLOSESQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatelementContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(TranslationParser.WILDCARD, 0); }
		public TerminalNode OPENROUND() { return getToken(TranslationParser.OPENROUND, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode CLOSEROUND() { return getToken(TranslationParser.CLOSEROUND, 0); }
		public RepeatelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatelement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitRepeatelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatelementContext repeatelement() throws RecognitionException {
		RepeatelementContext _localctx = new RepeatelementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_repeatelement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(WILDCARD);
			setState(311);
			match(OPENROUND);
			setState(312);
			path();
			setState(313);
			match(CLOSEROUND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchesContext extends ParserRuleContext {
		public List<PathContext> path() {
			return getRuleContexts(PathContext.class);
		}
		public PathContext path(int i) {
			return getRuleContext(PathContext.class,i);
		}
		public TerminalNode OPENSQUARE() { return getToken(TranslationParser.OPENSQUARE, 0); }
		public TerminalNode CLOSESQUARE() { return getToken(TranslationParser.CLOSESQUARE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(TranslationParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TranslationParser.COMMA, i);
		}
		public BranchesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branches; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TranslationParserVisitor ) return ((TranslationParserVisitor<? extends T>)visitor).visitBranches(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchesContext branches() throws RecognitionException {
		BranchesContext _localctx = new BranchesContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_branches);
		int _la;
		try {
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				path();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				match(OPENSQUARE);
				setState(317);
				path();
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(318);
					match(COMMA);
					setState(319);
					path();
					}
					}
					setState(324);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(325);
				match(CLOSESQUARE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u014c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\7\2"+
		"V\n\2\f\2\16\2Y\13\2\3\2\7\2\\\n\2\f\2\16\2_\13\2\3\3\3\3\3\3\3\3\3\3"+
		"\7\3f\n\3\f\3\16\3i\13\3\3\4\3\4\3\5\3\5\3\6\3\6\7\6q\n\6\f\6\16\6t\13"+
		"\6\3\6\5\6w\n\6\3\6\3\6\3\6\3\6\7\6}\n\6\f\6\16\6\u0080\13\6\3\7\3\7\3"+
		"\b\7\b\u0085\n\b\f\b\16\b\u0088\13\b\3\t\3\t\3\t\5\t\u008d\n\t\3\t\5\t"+
		"\u0090\n\t\3\t\3\t\5\t\u0094\n\t\3\t\5\t\u0097\n\t\3\t\5\t\u009a\n\t\3"+
		"\n\3\n\5\n\u009e\n\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a6\n\13\3\f"+
		"\3\f\3\f\7\f\u00ab\n\f\f\f\16\f\u00ae\13\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r"+
		"\u00b6\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\5\22\u00c2"+
		"\n\22\3\23\3\23\5\23\u00c6\n\23\3\24\3\24\5\24\u00ca\n\24\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\5\31\u00d7\n\31\3\31\5\31"+
		"\u00da\n\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\5\34\u00ec\n\34\3\35\3\35\3\35\3\35\3\35\5\35\u00f3"+
		"\n\35\3\36\3\36\3\36\5\36\u00f8\n\36\5\36\u00fa\n\36\3\37\3\37\3\37\5"+
		"\37\u00ff\n\37\5\37\u0101\n\37\3 \3 \3 \5 \u0106\n \5 \u0108\n \3!\3!"+
		"\3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\7(\u0132\n(\f("+
		"\16(\u0135\13(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\7*\u0143\n*\f*\16*"+
		"\u0146\13*\3*\3*\5*\u014a\n*\3*\2\2+\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\4\5\2\20\20\23\23\33\37\4"+
		"\2\20\20  \2\u0153\2W\3\2\2\2\4`\3\2\2\2\6j\3\2\2\2\bl\3\2\2\2\nv\3\2"+
		"\2\2\f\u0081\3\2\2\2\16\u0086\3\2\2\2\20\u0089\3\2\2\2\22\u009d\3\2\2"+
		"\2\24\u00a5\3\2\2\2\26\u00a7\3\2\2\2\30\u00b5\3\2\2\2\32\u00b7\3\2\2\2"+
		"\34\u00b9\3\2\2\2\36\u00bb\3\2\2\2 \u00bd\3\2\2\2\"\u00bf\3\2\2\2$\u00c3"+
		"\3\2\2\2&\u00c7\3\2\2\2(\u00cb\3\2\2\2*\u00cd\3\2\2\2,\u00cf\3\2\2\2."+
		"\u00d1\3\2\2\2\60\u00d6\3\2\2\2\62\u00db\3\2\2\2\64\u00df\3\2\2\2\66\u00eb"+
		"\3\2\2\28\u00f2\3\2\2\2:\u00f9\3\2\2\2<\u0100\3\2\2\2>\u0107\3\2\2\2@"+
		"\u0109\3\2\2\2B\u010d\3\2\2\2D\u010f\3\2\2\2F\u0116\3\2\2\2H\u0118\3\2"+
		"\2\2J\u011f\3\2\2\2L\u0125\3\2\2\2N\u012d\3\2\2\2P\u0138\3\2\2\2R\u0149"+
		"\3\2\2\2TV\5\4\3\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X]\3\2\2\2Y"+
		"W\3\2\2\2Z\\\5\n\6\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\3\3\2\2"+
		"\2_]\3\2\2\2`a\5\6\4\2ab\7\30\2\2bg\5\b\5\2cd\7\27\2\2df\5\b\5\2ec\3\2"+
		"\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\5\3\2\2\2ig\3\2\2\2jk\7\37\2\2k\7"+
		"\3\2\2\2lm\7\f\2\2m\t\3\2\2\2nr\7\16\2\2oq\5\f\7\2po\3\2\2\2qt\3\2\2\2"+
		"rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uw\7\17\2\2vn\3\2\2\2vw\3\2\2"+
		"\2wx\3\2\2\2xy\7\3\2\2yz\5\16\b\2z~\7\"\2\2{}\5\20\t\2|{\3\2\2\2}\u0080"+
		"\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\13\3\2\2\2\u0080~\3\2\2\2\u0081\u0082"+
		"\5\32\16\2\u0082\r\3\2\2\2\u0083\u0085\7#\2\2\u0084\u0083\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\17\3\2\2"+
		"\2\u0088\u0086\3\2\2\2\u0089\u008f\5\22\n\2\u008a\u008c\7\16\2\2\u008b"+
		"\u008d\5\26\f\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3"+
		"\2\2\2\u008e\u0090\7\17\2\2\u008f\u008a\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0096\3\2\2\2\u0091\u0093\7\16\2\2\u0092\u0094\5,\27\2\u0093\u0092\3"+
		"\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\7\17\2\2\u0096"+
		"\u0091\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u009a\5\60"+
		"\31\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\21\3\2\2\2\u009b\u009e"+
		"\7\37\2\2\u009c\u009e\5\24\13\2\u009d\u009b\3\2\2\2\u009d\u009c\3\2\2"+
		"\2\u009e\23\3\2\2\2\u009f\u00a6\5\30\r\2\u00a0\u00a6\5\f\7\2\u00a1\u00a6"+
		"\7\33\2\2\u00a2\u00a6\7\34\2\2\u00a3\u00a6\7\36\2\2\u00a4\u00a6\7\35\2"+
		"\2\u00a5\u009f\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\25\3\2\2\2\u00a7"+
		"\u00ac\5\30\r\2\u00a8\u00a9\7\27\2\2\u00a9\u00ab\5\30\r\2\u00aa\u00a8"+
		"\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\27\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b6\5\34\17\2\u00b0\u00b6\5\36"+
		"\20\2\u00b1\u00b6\5 \21\2\u00b2\u00b6\5\"\22\2\u00b3\u00b6\5$\23\2\u00b4"+
		"\u00b6\5&\24\2\u00b5\u00af\3\2\2\2\u00b5\u00b0\3\2\2\2\u00b5\u00b1\3\2"+
		"\2\2\u00b5\u00b2\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6"+
		"\31\3\2\2\2\u00b7\u00b8\7\n\2\2\u00b8\33\3\2\2\2\u00b9\u00ba\7\4\2\2\u00ba"+
		"\35\3\2\2\2\u00bb\u00bc\7\5\2\2\u00bc\37\3\2\2\2\u00bd\u00be\7\6\2\2\u00be"+
		"!\3\2\2\2\u00bf\u00c1\7\7\2\2\u00c0\u00c2\5(\25\2\u00c1\u00c0\3\2\2\2"+
		"\u00c1\u00c2\3\2\2\2\u00c2#\3\2\2\2\u00c3\u00c5\7\b\2\2\u00c4\u00c6\5"+
		"*\26\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6%\3\2\2\2\u00c7\u00c9"+
		"\7\t\2\2\u00c8\u00ca\5.\30\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\'\3\2\2\2\u00cb\u00cc\7\f\2\2\u00cc)\3\2\2\2\u00cd\u00ce\7\f\2\2\u00ce"+
		"+\3\2\2\2\u00cf\u00d0\7\f\2\2\u00d0-\3\2\2\2\u00d1\u00d2\7\f\2\2\u00d2"+
		"/\3\2\2\2\u00d3\u00d7\5\62\32\2\u00d4\u00d7\5\64\33\2\u00d5\u00d7\5\66"+
		"\34\2\u00d6\u00d3\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"\u00d9\3\2\2\2\u00d8\u00da\5R*\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2"+
		"\2\u00da\61\3\2\2\2\u00db\u00dc\7\22\2\2\u00dc\u00dd\7\20\2\2\u00dd\u00de"+
		"\7\20\2\2\u00de\63\3\2\2\2\u00df\u00e0\7\22\2\2\u00e0\u00e1\7\23\2\2\u00e1"+
		"\u00e2\7\23\2\2\u00e2\65\3\2\2\2\u00e3\u00ec\58\35\2\u00e4\u00ec\5@!\2"+
		"\u00e5\u00ec\5L\'\2\u00e6\u00ec\5D#\2\u00e7\u00ec\5H%\2\u00e8\u00ec\5"+
		"J&\2\u00e9\u00ec\5N(\2\u00ea\u00ec\5P)\2\u00eb\u00e3\3\2\2\2\u00eb\u00e4"+
		"\3\2\2\2\u00eb\u00e5\3\2\2\2\u00eb\u00e6\3\2\2\2\u00eb\u00e7\3\2\2\2\u00eb"+
		"\u00e8\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\67\3\2\2"+
		"\2\u00ed\u00f3\5:\36\2\u00ee\u00f3\5<\37\2\u00ef\u00f3\5> \2\u00f0\u00f1"+
		"\7\22\2\2\u00f1\u00f3\t\2\2\2\u00f2\u00ed\3\2\2\2\u00f2\u00ee\3\2\2\2"+
		"\u00f2\u00ef\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f39\3\2\2\2\u00f4\u00fa\7"+
		"\21\2\2\u00f5\u00f7\7\33\2\2\u00f6\u00f8\7\30\2\2\u00f7\u00f6\3\2\2\2"+
		"\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f5"+
		"\3\2\2\2\u00fa;\3\2\2\2\u00fb\u0101\7\23\2\2\u00fc\u00fe\7\34\2\2\u00fd"+
		"\u00ff\7\30\2\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3"+
		"\2\2\2\u0100\u00fb\3\2\2\2\u0100\u00fc\3\2\2\2\u0101=\3\2\2\2\u0102\u0108"+
		"\7\24\2\2\u0103\u0105\7\35\2\2\u0104\u0106\7\30\2\2\u0105\u0104\3\2\2"+
		"\2\u0105\u0106\3\2\2\2\u0106\u0108\3\2\2\2\u0107\u0102\3\2\2\2\u0107\u0103"+
		"\3\2\2\2\u0108?\3\2\2\2\u0109\u010a\7\31\2\2\u010a\u010b\5B\"\2\u010b"+
		"\u010c\7\32\2\2\u010cA\3\2\2\2\u010d\u010e\t\3\2\2\u010eC\3\2\2\2\u010f"+
		"\u0110\7\r\2\2\u0110\u0111\7\16\2\2\u0111\u0112\5\60\31\2\u0112\u0113"+
		"\7\26\2\2\u0113\u0114\5F$\2\u0114\u0115\7\17\2\2\u0115E\3\2\2\2\u0116"+
		"\u0117\7\f\2\2\u0117G\3\2\2\2\u0118\u0119\7\r\2\2\u0119\u011a\7\16\2\2"+
		"\u011a\u011b\5\60\31\2\u011b\u011c\7\26\2\2\u011c\u011d\5\60\31\2\u011d"+
		"\u011e\7\17\2\2\u011eI\3\2\2\2\u011f\u0120\7\r\2\2\u0120\u0121\7\16\2"+
		"\2\u0121\u0122\7\25\2\2\u0122\u0123\5\60\31\2\u0123\u0124\7\17\2\2\u0124"+
		"K\3\2\2\2\u0125\u0126\7\r\2\2\u0126\u0127\7\16\2\2\u0127\u0128\5\60\31"+
		"\2\u0128\u0129\7\24\2\2\u0129\u012a\7\26\2\2\u012a\u012b\5F$\2\u012b\u012c"+
		"\7\17\2\2\u012cM\3\2\2\2\u012d\u012e\7\31\2\2\u012e\u0133\5\66\34\2\u012f"+
		"\u0130\7\27\2\2\u0130\u0132\5\66\34\2\u0131\u012f\3\2\2\2\u0132\u0135"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\u0136\u0137\7\32\2\2\u0137O\3\2\2\2\u0138\u0139\7\20\2"+
		"\2\u0139\u013a\7\16\2\2\u013a\u013b\5\60\31\2\u013b\u013c\7\17\2\2\u013c"+
		"Q\3\2\2\2\u013d\u014a\5\60\31\2\u013e\u013f\7\31\2\2\u013f\u0144\5\60"+
		"\31\2\u0140\u0141\7\27\2\2\u0141\u0143\5\60\31\2\u0142\u0140\3\2\2\2\u0143"+
		"\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0147\u0148\7\32\2\2\u0148\u014a\3\2\2\2\u0149"+
		"\u013d\3\2\2\2\u0149\u013e\3\2\2\2\u014aS\3\2\2\2\"W]grv~\u0086\u008c"+
		"\u008f\u0093\u0096\u0099\u009d\u00a5\u00ac\u00b5\u00c1\u00c5\u00c9\u00d6"+
		"\u00d9\u00eb\u00f2\u00f7\u00f9\u00fe\u0100\u0105\u0107\u0133\u0144\u0149";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}