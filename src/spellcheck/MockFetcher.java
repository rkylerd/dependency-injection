package spellcheck;

import java.io.IOException;

public class MockFetcher implements DocumentFetcher {

    public String fetch(Object o) throws IOException {
        return "funtion tere going varable";
    }

}