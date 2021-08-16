package com.interviewpractice.gateway.services;


import com.mmm.readers.ErrorHandler;
import com.mmm.readers.FullPage.Reader;
import org.springframework.stereotype.Service;

/**
 * <p>Created By Mayank Chauhan on 16/8/21 - 5:12 PM</p>
 */
public interface IDScannerService {

    public Reader initialiseScanner();
    public String readDocument(Reader reader);
}
