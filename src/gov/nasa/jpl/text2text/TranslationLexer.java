// Generated from TranslationLexer.g4 by ANTLR 4.7.1
package gov.nasa.jpl.text2text;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TranslationLexer extends Lexer {
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
		TEMPLATE_MODE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "TEMPLATE_MODE"
	};

	public static final String[] ruleNames = {
		"STARTTEMPLATE", "TRIGGER", "REQUIRED", "KEYWORD", "RAW", "BODY", "MULTIVALUE", 
		"NODUPLICATES", "COMMENT", "STRING", "QUESTION", "OPENROUND", "CLOSEROUND", 
		"WILDCARD", "DOLLAR", "DOT", "CARET", "TILDE", "BANG", "EQUALS", "COMMA", 
		"COLON", "OPENSQUARE", "CLOSESQUARE", "DOC", "REF", "HERE", "LIB", "IDENTIFIER", 
		"NATURALNUM", "WS", "ENDTEMPLATE", "TEMPLATE_CHAR", "A", "B", "C", "D", 
		"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
		"S", "T", "U", "V", "W", "X", "Y", "Z"
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


	public TranslationLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TranslationLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u0197\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u0097\n\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00a6\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00b7\n\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00c5\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00e5\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00fa\n\t\3\n\3\n\3\n"+
		"\3\n\7\n\u0100\n\n\f\n\16\n\u0103\13\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13"+
		"\u010b\n\13\f\13\16\13\u010e\13\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\7\36"+
		"\u0141\n\36\f\36\16\36\u0144\13\36\3\37\3\37\3\37\7\37\u0149\n\37\f\37"+
		"\16\37\u014c\13\37\5\37\u014e\n\37\3 \6 \u0151\n \r \16 \u0152\3 \3 \3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3"+
		"\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39"+
		"\39\3:\3:\3;\3;\3<\3<\3\u010c\2=\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24"+
		"\13\26\f\30\r\32\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31"+
		"\62\32\64\33\66\348\35:\36<\37> @!B\"D#F\2H\2J\2L\2N\2P\2R\2T\2V\2X\2"+
		"Z\2\\\2^\2`\2b\2d\2f\2h\2j\2l\2n\2p\2r\2t\2v\2x\2\4\2\3$\4\2\f\f\17\17"+
		"\3\2^^\3\2\f\f\5\2C\\aac|\6\2\62;C\\aac|\3\2\63;\3\2\62;\5\2\13\f\17\17"+
		"\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4"+
		"\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSs"+
		"s\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2"+
		"\\\\||\2\u018c\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2"+
		"\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2"+
		"\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3"+
		"\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2"+
		"\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3"+
		"\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\3B\3\2\2\2\3D\3\2\2\2\4z\3\2\2"+
		"\2\6\u0096\3\2\2\2\b\u00a5\3\2\2\2\n\u00b6\3\2\2\2\f\u00b8\3\2\2\2\16"+
		"\u00c4\3\2\2\2\20\u00e4\3\2\2\2\22\u00f9\3\2\2\2\24\u00fb\3\2\2\2\26\u0106"+
		"\3\2\2\2\30\u0111\3\2\2\2\32\u0113\3\2\2\2\34\u0115\3\2\2\2\36\u0117\3"+
		"\2\2\2 \u0119\3\2\2\2\"\u011b\3\2\2\2$\u011d\3\2\2\2&\u011f\3\2\2\2(\u0121"+
		"\3\2\2\2*\u0123\3\2\2\2,\u0125\3\2\2\2.\u0127\3\2\2\2\60\u0129\3\2\2\2"+
		"\62\u012b\3\2\2\2\64\u012d\3\2\2\2\66\u0131\3\2\2\28\u0135\3\2\2\2:\u013a"+
		"\3\2\2\2<\u013e\3\2\2\2>\u014d\3\2\2\2@\u0150\3\2\2\2B\u0156\3\2\2\2D"+
		"\u0161\3\2\2\2F\u0163\3\2\2\2H\u0165\3\2\2\2J\u0167\3\2\2\2L\u0169\3\2"+
		"\2\2N\u016b\3\2\2\2P\u016d\3\2\2\2R\u016f\3\2\2\2T\u0171\3\2\2\2V\u0173"+
		"\3\2\2\2X\u0175\3\2\2\2Z\u0177\3\2\2\2\\\u0179\3\2\2\2^\u017b\3\2\2\2"+
		"`\u017d\3\2\2\2b\u017f\3\2\2\2d\u0181\3\2\2\2f\u0183\3\2\2\2h\u0185\3"+
		"\2\2\2j\u0187\3\2\2\2l\u0189\3\2\2\2n\u018b\3\2\2\2p\u018d\3\2\2\2r\u018f"+
		"\3\2\2\2t\u0191\3\2\2\2v\u0193\3\2\2\2x\u0195\3\2\2\2z{\5l\66\2{|\5N\'"+
		"\2|}\5^/\2}~\5d\62\2~\177\5\\.\2\177\u0080\5F#\2\u0080\u0081\5l\66\2\u0081"+
		"\u0082\5N\'\2\u0082\u0083\7<\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\2\2"+
		"\2\u0085\5\3\2\2\2\u0086\u0087\5l\66\2\u0087\u0088\5h\64\2\u0088\u0089"+
		"\5V+\2\u0089\u008a\5R)\2\u008a\u008b\5R)\2\u008b\u008c\5N\'\2\u008c\u008d"+
		"\5h\64\2\u008d\u0097\3\2\2\2\u008e\u008f\5l\66\2\u008f\u0090\5h\64\2\u0090"+
		"\u0091\5V+\2\u0091\u0092\5R)\2\u0092\u0097\3\2\2\2\u0093\u0094\5l\66\2"+
		"\u0094\u0095\5h\64\2\u0095\u0097\3\2\2\2\u0096\u0086\3\2\2\2\u0096\u008e"+
		"\3\2\2\2\u0096\u0093\3\2\2\2\u0097\7\3\2\2\2\u0098\u0099\5h\64\2\u0099"+
		"\u009a\5N\'\2\u009a\u009b\5f\63\2\u009b\u009c\5n\67\2\u009c\u009d\5V+"+
		"\2\u009d\u009e\5h\64\2\u009e\u009f\5N\'\2\u009f\u00a0\5L&\2\u00a0\u00a6"+
		"\3\2\2\2\u00a1\u00a2\5h\64\2\u00a2\u00a3\5N\'\2\u00a3\u00a4\5f\63\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5\u0098\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a6\t\3\2\2\2"+
		"\u00a7\u00a8\5Z-\2\u00a8\u00a9\5N\'\2\u00a9\u00aa\5v;\2\u00aa\u00ab\5"+
		"r9\2\u00ab\u00ac\5b\61\2\u00ac\u00ad\5h\64\2\u00ad\u00ae\5L&\2\u00ae\u00b7"+
		"\3\2\2\2\u00af\u00b0\5Z-\2\u00b0\u00b1\5N\'\2\u00b1\u00b2\5v;\2\u00b2"+
		"\u00b7\3\2\2\2\u00b3\u00b4\5Z-\2\u00b4\u00b5\5r9\2\u00b5\u00b7\3\2\2\2"+
		"\u00b6\u00a7\3\2\2\2\u00b6\u00af\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b7\13"+
		"\3\2\2\2\u00b8\u00b9\5h\64\2\u00b9\u00ba\5F#\2\u00ba\u00bb\5r9\2\u00bb"+
		"\r\3\2\2\2\u00bc\u00bd\5H$\2\u00bd\u00be\5b\61\2\u00be\u00bf\5L&\2\u00bf"+
		"\u00c0\5v;\2\u00c0\u00c5\3\2\2\2\u00c1\u00c2\5H$\2\u00c2\u00c3\5L&\2\u00c3"+
		"\u00c5\3\2\2\2\u00c4\u00bc\3\2\2\2\u00c4\u00c1\3\2\2\2\u00c5\17\3\2\2"+
		"\2\u00c6\u00c7\5^/\2\u00c7\u00c8\5n\67\2\u00c8\u00c9\5\\.\2\u00c9\u00ca"+
		"\5l\66\2\u00ca\u00cb\5V+\2\u00cb\u00cc\5p8\2\u00cc\u00cd\5F#\2\u00cd\u00ce"+
		"\5\\.\2\u00ce\u00cf\5n\67\2\u00cf\u00d0\5N\'\2\u00d0\u00e5\3\2\2\2\u00d1"+
		"\u00d2\5^/\2\u00d2\u00d3\5n\67\2\u00d3\u00d4\5\\.\2\u00d4\u00d5\5l\66"+
		"\2\u00d5\u00d6\5V+\2\u00d6\u00d7\5p8\2\u00d7\u00d8\5F#\2\u00d8\u00d9\5"+
		"\\.\2\u00d9\u00e5\3\2\2\2\u00da\u00db\5^/\2\u00db\u00dc\5n\67\2\u00dc"+
		"\u00dd\5\\.\2\u00dd\u00de\5l\66\2\u00de\u00df\5V+\2\u00df\u00e5\3\2\2"+
		"\2\u00e0\u00e1\5^/\2\u00e1\u00e2\5n\67\2\u00e2\u00e3\5\\.\2\u00e3\u00e5"+
		"\3\2\2\2\u00e4\u00c6\3\2\2\2\u00e4\u00d1\3\2\2\2\u00e4\u00da\3\2\2\2\u00e4"+
		"\u00e0\3\2\2\2\u00e5\21\3\2\2\2\u00e6\u00e7\5`\60\2\u00e7\u00e8\5b\61"+
		"\2\u00e8\u00e9\5L&\2\u00e9\u00ea\5n\67\2\u00ea\u00eb\5d\62\2\u00eb\u00ec"+
		"\5\\.\2\u00ec\u00ed\5V+\2\u00ed\u00ee\5J%\2\u00ee\u00ef\5F#\2\u00ef\u00f0"+
		"\5l\66\2\u00f0\u00f1\5N\'\2\u00f1\u00f2\5j\65\2\u00f2\u00fa\3\2\2\2\u00f3"+
		"\u00f4\5`\60\2\u00f4\u00f5\5b\61\2\u00f5\u00f6\5L&\2\u00f6\u00f7\5n\67"+
		"\2\u00f7\u00f8\5d\62\2\u00f8\u00fa\3\2\2\2\u00f9\u00e6\3\2\2\2\u00f9\u00f3"+
		"\3\2\2\2\u00fa\23\3\2\2\2\u00fb\u00fc\7\61\2\2\u00fc\u00fd\7\61\2\2\u00fd"+
		"\u0101\3\2\2\2\u00fe\u0100\n\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3\2"+
		"\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0104\u0105\b\n\3\2\u0105\25\3\2\2\2\u0106\u010c\7$\2\2"+
		"\u0107\u010b\n\3\2\2\u0108\u0109\7^\2\2\u0109\u010b\n\4\2\2\u010a\u0107"+
		"\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010d\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\7$"+
		"\2\2\u0110\27\3\2\2\2\u0111\u0112\7A\2\2\u0112\31\3\2\2\2\u0113\u0114"+
		"\7*\2\2\u0114\33\3\2\2\2\u0115\u0116\7+\2\2\u0116\35\3\2\2\2\u0117\u0118"+
		"\7,\2\2\u0118\37\3\2\2\2\u0119\u011a\7&\2\2\u011a!\3\2\2\2\u011b\u011c"+
		"\7\60\2\2\u011c#\3\2\2\2\u011d\u011e\7`\2\2\u011e%\3\2\2\2\u011f\u0120"+
		"\7\u0080\2\2\u0120\'\3\2\2\2\u0121\u0122\7#\2\2\u0122)\3\2\2\2\u0123\u0124"+
		"\7?\2\2\u0124+\3\2\2\2\u0125\u0126\7.\2\2\u0126-\3\2\2\2\u0127\u0128\7"+
		"<\2\2\u0128/\3\2\2\2\u0129\u012a\7]\2\2\u012a\61\3\2\2\2\u012b\u012c\7"+
		"_\2\2\u012c\63\3\2\2\2\u012d\u012e\7F\2\2\u012e\u012f\7Q\2\2\u012f\u0130"+
		"\7E\2\2\u0130\65\3\2\2\2\u0131\u0132\7T\2\2\u0132\u0133\7G\2\2\u0133\u0134"+
		"\7H\2\2\u0134\67\3\2\2\2\u0135\u0136\7J\2\2\u0136\u0137\7G\2\2\u0137\u0138"+
		"\7T\2\2\u0138\u0139\7G\2\2\u01399\3\2\2\2\u013a\u013b\7N\2\2\u013b\u013c"+
		"\7K\2\2\u013c\u013d\7D\2\2\u013d;\3\2\2\2\u013e\u0142\t\5\2\2\u013f\u0141"+
		"\t\6\2\2\u0140\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143=\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u014e\7\62\2\2"+
		"\u0146\u014a\t\7\2\2\u0147\u0149\t\b\2\2\u0148\u0147\3\2\2\2\u0149\u014c"+
		"\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014e\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014d\u0145\3\2\2\2\u014d\u0146\3\2\2\2\u014e?\3\2\2\2"+
		"\u014f\u0151\t\t\2\2\u0150\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150"+
		"\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\b \3\2\u0155"+
		"A\3\2\2\2\u0156\u0157\7\f\2\2\u0157\u0158\5P(\2\u0158\u0159\5V+\2\u0159"+
		"\u015a\5N\'\2\u015a\u015b\5\\.\2\u015b\u015c\5L&\2\u015c\u015d\5j\65\2"+
		"\u015d\u015e\7<\2\2\u015e\u015f\3\2\2\2\u015f\u0160\b!\4\2\u0160C\3\2"+
		"\2\2\u0161\u0162\13\2\2\2\u0162E\3\2\2\2\u0163\u0164\t\n\2\2\u0164G\3"+
		"\2\2\2\u0165\u0166\t\13\2\2\u0166I\3\2\2\2\u0167\u0168\t\f\2\2\u0168K"+
		"\3\2\2\2\u0169\u016a\t\r\2\2\u016aM\3\2\2\2\u016b\u016c\t\16\2\2\u016c"+
		"O\3\2\2\2\u016d\u016e\t\17\2\2\u016eQ\3\2\2\2\u016f\u0170\t\20\2\2\u0170"+
		"S\3\2\2\2\u0171\u0172\t\21\2\2\u0172U\3\2\2\2\u0173\u0174\t\22\2\2\u0174"+
		"W\3\2\2\2\u0175\u0176\t\23\2\2\u0176Y\3\2\2\2\u0177\u0178\t\24\2\2\u0178"+
		"[\3\2\2\2\u0179\u017a\t\25\2\2\u017a]\3\2\2\2\u017b\u017c\t\26\2\2\u017c"+
		"_\3\2\2\2\u017d\u017e\t\27\2\2\u017ea\3\2\2\2\u017f\u0180\t\30\2\2\u0180"+
		"c\3\2\2\2\u0181\u0182\t\31\2\2\u0182e\3\2\2\2\u0183\u0184\t\32\2\2\u0184"+
		"g\3\2\2\2\u0185\u0186\t\33\2\2\u0186i\3\2\2\2\u0187\u0188\t\34\2\2\u0188"+
		"k\3\2\2\2\u0189\u018a\t\35\2\2\u018am\3\2\2\2\u018b\u018c\t\36\2\2\u018c"+
		"o\3\2\2\2\u018d\u018e\t\37\2\2\u018eq\3\2\2\2\u018f\u0190\t \2\2\u0190"+
		"s\3\2\2\2\u0191\u0192\t!\2\2\u0192u\3\2\2\2\u0193\u0194\t\"\2\2\u0194"+
		"w\3\2\2\2\u0195\u0196\t#\2\2\u0196y\3\2\2\2\21\2\3\u0096\u00a5\u00b6\u00c4"+
		"\u00e4\u00f9\u0101\u010a\u010c\u0142\u014a\u014d\u0152\5\7\3\2\b\2\2\6"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}