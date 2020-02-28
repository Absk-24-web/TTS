package Tts;

import jdk.nashorn.internal.parser.TokenStream;
import opennlp.tools.stemmer.PorterStemmer;

import java.io.IOException;
import java.io.StringReader;


public class Normalization {
    PorterStemmer porterStemmer = new PorterStemmer();


public String norm (String input){

    input = input.toLowerCase();
    input = input.replaceAll("\\\\", "").trim();
    input = input.replaceAll("\\s/\\s", "").trim();
    input = input.replaceAll("^/\\s", "").trim();
    input = porterStemmer.stem(input);
    return input;
}



//    public  String stem(String string) throws IOException {
//        TokenStream tokenizer = new StandardTokenizer(Version.LUCENE_47, new StringReader(string));
//        tokenizer = new StandardFilter(Version.LUCENE_47, tokenizer);
//        tokenizer = new LowerCaseFilter(Version.LUCENE_47, tokenizer);
//        tokenizer = new PorterStemFilter(tokenizer);
//
//        CharTermAttribute token = tokenizer.getAttribute(CharTermAttribute.class);
//
//        tokenizer.reset();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        while(tokenizer.incrementToken()) {
//            if(stringBuilder.length() > 0 ) {
//                stringBuilder.append(" ");
//            }
//
//            stringBuilder.append(token.toString());
//        }
//
//        tokenizer.end();
//        tokenizer.close();
//
//        return stringBuilder.toString();
//    }

//    public static String toSlug(String input) {
//
//        String transliterated = transliterator.transform(input);
//        String noWhitespace = WHITESPACE.matcher(transliterated).replaceAll("-");
//        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
//        String slug = NONLATIN.matcher(normalized).replaceAll("");
//        slug = EDGESDHASHES.matcher(slug).replaceAll("");
//
//        return slug.toLowerCase(Locale.ENGLISH);
//    }



//    public  String removeDiacritics(String s) {
//        String n = Normalizer.normalize(s, Normalizer.Form.NFD);
//        StringBuilder sb = null;
//        for (int i = 0; i < n.length(); ++i) {
//            char c = n.charAt(i);
//            Character.UnicodeBlock b = Character.UnicodeBlock.of(c);
//            if (Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS.equals(b) || Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_SUPPLEMENT.equals(b)) {
//                if (sb == null) {
//                    sb = new StringBuilder(n.length());
//                    sb.append(n.substring(0, i));
//                }
//                continue;
//            }
//            if (sb != null)
//                sb.append(c);
//        }
//        if (sb == null)
//            return n;
//        return sb.toString();
//    }
}
