package org.sniffer.backend;

import java.util.ArrayList;

public class Query {

    private ArrayList<SubQuery> subQueries = new ArrayList();


    //getters
    public ArrayList<SubQuery> getSubQueries() {
        return subQueries;
    }

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


}
