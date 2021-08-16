package com.interviewpractice.gateway.controllers;

import com.interviewpractice.gateway.services.IDScannerService;
import com.mmm.readers.FullPage.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 4:29 PM</p>
 */
@RestController
public class IDScannerController {

    @Autowired
    IDScannerService idScannerService;

    @GetMapping("/scan")
    public String scanImage() {
        Reader reader = idScannerService.initialiseScanner();
        String documentData = idScannerService.readDocument(reader);
        return documentData;
    }
}
