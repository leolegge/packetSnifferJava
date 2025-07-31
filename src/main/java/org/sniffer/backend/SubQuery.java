package org.sniffer.backend;

/**
 * This is the subQuery class that holds the queryOption entered into the query bar
 *
 */
public class SubQuery {

    private final QueryOptions queryOption;

    /**
     * constructor for SubQuery
     *
     * @param queryOption is the enum to be filtered for
     */
    public SubQuery(QueryOptions queryOption) {
        this.queryOption = queryOption;
    }

    //getters
    public QueryOptions getQueryOption() {
        return queryOption;
    }


}
