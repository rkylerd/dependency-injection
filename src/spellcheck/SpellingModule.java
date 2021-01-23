package spellcheck;

import com.google.inject.AbstractModule;

public class SpellingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DocumentFetcher.class).to(MockFetcher.class);
        bind(WordExtractorInterface.class).to(WordExtractor.class);
        bind(DictionaryInterface.class).to(Dictionary.class);
    }
}
