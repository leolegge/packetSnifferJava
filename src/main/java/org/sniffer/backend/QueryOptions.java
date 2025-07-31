package org.sniffer.backend;

public enum QueryOptions {

    TCP,
    ARP,
    UDP,
    DNS,
    HTTP,
    UNKNOWN;

    /**
     * Translates String into enum for use by the queryParser
     *
     * @param querySubString is the String being translated
     * @return the translated enum
     * @throws InvalidQueryOption when an invalid query is typed
     */
    public static QueryOptions translateToQueryOption(String querySubString) throws InvalidQueryOption {
        for (QueryOptions queryOption : QueryOptions.values()) {
            if(querySubString.toUpperCase().equals(queryOption.toString())) {
                return queryOption;
            }
        }
        throw new InvalidQueryOption("Invalid entry");
    }

    /**
     * Checks if a string is able to be translated
     *
     * @param querySubString is the string being checked for translation
     * @return a boolean value signifying if querySubString can be translated into a QueryOptions enum
     */
    public static boolean validateQueryOption(String querySubString) {
        for (QueryOptions queryOption : QueryOptions.values()) {
            if(querySubString.toUpperCase().equals(queryOption.toString())) {
                return true;
            }
        }
        return false;
    }
}


