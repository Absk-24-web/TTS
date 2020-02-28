package test;

import com.sun.speech.freetts.lexicon.Lexicon;

import java.io.IOException;
import java.util.List;

    public class Lookup implements Lexicon {

        @Override
        public String[] getPhones(String s, String s1) {
            return new String[0];
        }

        @Override
        public String[] getPhones(String s, String s1, boolean b) {
            return new String[0];
        }

        @Override
        public void addAddendum(String s, String s1, String[] strings) {

        }

        @Override
        public void removeAddendum(String s, String s1) {

        }

        @Override
        public boolean isSyllableBoundary(List list, String[] strings, int i) {
            return false;
        }

        @Override
        public void load() throws IOException {

        }

        @Override
        public boolean isLoaded() {
            return false;
        }
    }
