package org.sniffer.backend;

public class SubQuery {

    private final QueryOptions queryOption;


    public SubQuery(QueryOptions queryOption) {
        this.queryOption = queryOption;
    }

    //getters
    public QueryOptions getQueryOption() {
        return queryOption;
    }

}
