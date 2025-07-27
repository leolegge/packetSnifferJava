package org.sniffer.backend;

public class InvalidQueryOption extends RuntimeException {
    public InvalidQueryOption(String message) {
        super(message);
    }
}
