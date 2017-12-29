package com.trumpreader.beans;

import java.util.List;

public class Quote {

    private String value;
    private Embedded _embedded;

    public String getValue() {
        return value;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    class Embedded {
        private List<Source> source;

        public List<Source> getSource() {
            return source;
        }

        public void setSource(List<Source> source) {
            this.source = source;
        }
    }

    class Source {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
