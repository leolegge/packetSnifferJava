package org.sniffer.backend;

import java.util.ArrayList;

/**
 * This is the query class that constructs the current filters on the packets
 *
 */
public class Query {

    private ArrayList<SubQuery> subQueries = new ArrayList();

    //general methods
    public void addSubQuery(SubQuery subQuery) {
        boolean exists = false;
        for(SubQuery sq : subQueries) {
            if (sq.getQueryOption().equals(subQuery.getQueryOption())) {
                exists = true;
                break;
            }
        }
        if(!exists) {
            subQueries.add(subQuery);
        }
    }

    //getters
    public ArrayList<SubQuery> getSubQueries() {
        return subQueries;
    }


}
