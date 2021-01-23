
package spellcheck;

import com.google.inject.Inject;

import java.util.*;
import java.net.*;
import java.io.*;

public class SpellingChecker implements SpellChecker {

	private DocumentFetcher fetcher;
	private WordExtractorInterface extractor;
	private DictionaryInterface dictionary;

	@Inject
	public SpellingChecker(DocumentFetcher fetcher,
						   WordExtractorInterface extractor,
						   DictionaryInterface dictionary) throws Exception {
		if (fetcher == null) {
			throw new Exception("Must pass in a DocumentFetcher object");
		}

		if (extractor == null) {
			throw new Exception("Must pass in a WordExtractorInterface object");
		}

		if (dictionary == null) {
			throw new Exception("Must pass in a DictionaryInterface object");
		}

		this.fetcher = fetcher;
		this.extractor = extractor;
		this.dictionary = dictionary;

	}

	public SortedMap<String, Integer> check(Object o) throws IOException {
		URL url = (URL) o;
		// download the document content
		//
//		DocumentFetcher fetcher = new URLFetcher();
		String content = fetcher.fetch(url);

		// extract words from the content
		//
//		WordExtractorInterface extractor = new WordExtractor();
		List<String> words = extractor.extract(content);

		// find spelling mistakes
		//
//		DictionaryInterface dictionary = new Dictionary("dict.txt");
		SortedMap<String, Integer> mistakes = new TreeMap<String, Integer>();
		
		Iterator<String> it = words.iterator();
		while (it.hasNext()) {
			String word = it.next();
			if (!dictionary.isValidWord(word)) {
				if (mistakes.containsKey(word)) {
					int oldCount = mistakes.get(word);
					mistakes.put(word, (Integer)(oldCount + 1));
				}
				else {
					mistakes.put(word, (Integer)1);
				}
			}
		}

		return mistakes;
	}

}

