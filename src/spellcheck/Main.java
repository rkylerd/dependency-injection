
package spellcheck;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.*;
import java.net.*;
import java.util.*;


public class Main {

	public static void main(String[] args) {
	
		try {
			URL url = new URL(args[0]);

//			DocumentFetcher fetcher  = new URLFetcher();
//			WordExtractorInterface extractor = new WordExtractor();
//			DictionaryInterface dictionary = new Dictionary("dict.txt");

//			SpellingChecker checker = new SpellingChecker(fetcher, extractor, dictionary);
            Injector injector = Guice.createInjector(new SpellingModule());
            SpellingChecker checker = injector.getInstance(SpellingChecker.class);

			SortedMap<String, Integer> mistakes = checker.check(url);
			System.out.println(mistakes);
		}
		catch (IOException e) {
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}

