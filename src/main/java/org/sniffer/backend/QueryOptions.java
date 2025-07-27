package org.sniffer.backend;

public enum QueryOptions {

    TCP,
    ARP,
    UDP,
    DNS,
    HTTP,
    UNKNOWN;



    public static QueryOptions translateToQueryOption(String querySubString) throws InvalidQueryOption {
        for (QueryOptions queryOption : QueryOptions.values()) {
            if(querySubString.toUpperCase().equals(queryOption.toString())) {
                return queryOption;
            }
        }
        throw new InvalidQueryOption("Invalid entry");
    }



}


