package org.sniffer.backend;

public enum QueryOptions {

    TCP,
    ARP,
    UDP,
    DNS,
    UNKNOWN;



    public QueryOptions translateToQueryOption(String querySubString) {
        for (QueryOptions queryOption : QueryOptions.values()) {
            if(querySubString.toUpperCase().equals(queryOption.toString())) {
                return queryOption;
            }
        }
        return null;
    }
}


