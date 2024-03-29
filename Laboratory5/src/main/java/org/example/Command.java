package org.example;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, InvalidDocumentName, RedundantObjectException, DocumentFormatException;
}
