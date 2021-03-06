package com.interviewpractice.gateway.services;


import com.mmm.readers.ErrorCode;
import com.mmm.readers.ErrorHandler;
import com.mmm.readers.FullPage.DataHandler;
import com.mmm.readers.FullPage.DataType;
import com.mmm.readers.FullPage.EventHandler;
import com.mmm.readers.FullPage.Reader;
import com.mmm.readers.modules.rfid.CertificateHandler;
import org.springframework.stereotype.Service;

import net.idscan.dlparser.DLParser;
import net.idscan.dlparser.DLParser.DLParserException;
import net.idscan.dlparser.DLParser.DLResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * <p>Created By Mayank Chauhan on 16/8/21 - 5:12 PM</p>
 */
@Service
public class IDScannerServiceImpl implements IDScannerService, ErrorHandler {

    private static final String _KEY = "hPtwrFL58DPrNy3tY9Il8egvI862DPFbwYTS751Dg45DmxRCI2y30e14pRFGaOoznFyufTFq08fDiMpXwHRyPGi7BEbjhn7agv6Rp2zl0UEsAMxFmrUD6Vl1YsB1DFssWYnSXJYvWKgAxQic06qTuHhKif9jZj8HqLbllFmpdvo=";

    public Reader initialiseScanner() {
        try {
            Reader reader = new Reader();
            reader.EnableLogging(true, 1, -1, "IDScanner.log");
            ErrorCode errorCode = reader.Initialise((DataHandler)null, (EventHandler)null, this, (CertificateHandler)null, true, false, 0);
            if (errorCode != ErrorCode.NO_ERROR_OCCURRED) {
                System.out.println("Initialise error: " + errorCode.toString());
            } else {
                System.out.println("Initialise successful");
            }
            return reader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readDocument(Reader reader) {
        ErrorCode errorCode = reader.ReadDocument();
        String var6 = null;
        if (errorCode == ErrorCode.NO_ERROR_OCCURRED) {
            int var3 = 3000000;
            byte[] var4 = new byte[200];
            int[] var5 = new int[]{200};
            if (reader.GetData(DataType.CD_CODELINE, var4, var5) == ErrorCode.NO_ERROR_OCCURRED) {
                var6 = new String(var4, 0, var5[0] - 1);
                if (var6.startsWith("P")) {
                    String var10000 = var6.substring(0, 44);
                    var6 = var10000 + "\r\n" + var6.substring(44, var6.length());
                }

                System.out.println(var6);
                parseReaderString(var6);
            }

            byte[] var8 = new byte[var3];
            int[] var7 = new int[]{var3};
            if (reader.GetData(DataType.CD_BARCODE_PDF417, var8, var7) == ErrorCode.NO_ERROR_OCCURRED) {
                System.out.println(Arrays.toString(var7));
//                System.out.println(Arrays.toString(var8));
            }
        }
        reader.Shutdown();
        return var6;
    }

    private String parseReaderString(String readerString) {
        DLParser parser = new DLParser();
        try {
            System.out.println("Parser Version: " + parser.getVersion());
            parser.setup(_KEY);
            readerString = "@\n" +
                    "\u001E\n" +
                    "ANSI 636099030001DL00310377DLDCAB   \n" +
                    "DCBNONE      \n" +
                    "DCDNONE \n" +
                    "DBA08202009\n" +
                    "DCSMURPHY                                  \n" +
                    "DCTBRENNA VICTORIA C                                                               \n" +
                    "DBD08202004\n" +
                    "DBB01011970\n" +
                    "DBC1\n" +
                    "DAYBRO\n" +
                    "DAU 64 in\n" +
                    "DAG111 DMV STREET                     \n" +
                    "DAICITY                \n" +
                    "DAJVA\n" +
                    "DAK12345000000\n" +
                    "DAQ000000900                \n" +
                    "DCFASDE44325665300536       \n" +
                    "DCGUSA\n" +
                    "DCHNONE\n" +
                    "\n";
            // KV License
            readerString = "@\n" +
                    "\u001E\n" +
                    "ANSI 636000030001DL00310447DLDCADM  \n" +
                    "DCB9         \n" +
                    "DCDNONE \n" +
                    "DBA11152020\n" +
                    "DCSVAGHELA                                 \n" +
                    "DCTKUNALSINH,LAXMANSINH                                                            \n" +
                    "DBD11262019\n" +
                    "DBB01031980\n" +
                    "DBC1\n" +
                    "DAYBLK\n" +
                    "DAU070 in\n" +
                    "DAG13685 AIR AND SPACE MUSEUM PKWY    \n" +
                    "DAIHERNDON             \n" +
                    "DAJVA\n" +
                    "DAK201714117  \n" +
                    "DAQA60895001                \n" +
                    "DCF085122717                \n" +
                    "DCGUSA\n" +
                    "DCHD   \n" +
                    "DDC00000000\n" +
                    "DDB12102008\n" +
                    "DDDY\n" +
                    "DDAF\n" +
                    "DCK0060101432396300         \n" +
                    "DCADM  \n";
            DLParser.DLResult res = parser.parse(readerString.getBytes("UTF8"));
            //print result.
            System.out.println("Full name: " + res.fullName);
            System.out.println("First name: " + res.firstName);
            System.out.println("Middle name: " + res.middleName);
            System.out.println("Last name: " + res.lastName);
            System.out.println("Name suffix: " + res.nameSuffix);
            System.out.println("Name prefix: " + res.namePrefix);
            System.out.println("Document type: " + res.documentType);
            System.out.println("Country code: " + res.countryCode);
            System.out.println("Country: " + res.country);
            System.out.println("Jurisdiction code: " + res.jurisdictionCode);
            System.out.println("IIN: " + res.iin);
            System.out.println("Address1: " + res.address1);
            System.out.println("Address2: " + res.address2);
            System.out.println("City: " + res.city);
            System.out.println("License number: " + res.licenseNumber);
            System.out.println("Classification code: " + res.classificationCode);
            System.out.println("Restriction code: " + res.restrictionCode);
            System.out.println("Restriction code description: " + res.restrictionCodeDescription);
            System.out.println("Endorsements code: " + res.endorsementsCode);
            System.out.println("Endorsement code description: " + res.endorsementCodeDescription);
            System.out.println("Expiration date: " + res.expirationDate);
            System.out.println("HAZMATExpDate: " + res.HAZMATExpDate);
            System.out.println("Birthdate: " + res.birthdate);
            System.out.println("Card revision date: " + res.cardRevisionDate);
            System.out.println("Gender: " + res.gender);
            System.out.println("Issue date: " + res.issueDate);
            System.out.println("Issue by: " + res.issuedBy);
            System.out.println("Postal code: " + res.postalCode);
            System.out.println("Eye color: " + res.eyeColor);
            System.out.println("Race: " + res.race);
            System.out.println("Hair color: " + res.hairColor);
            System.out.println("Height: " + res.height);
            System.out.println("WeightKG: " + res.weightKG);
            System.out.println("WeightLBS: " + res.weightLBS);
            System.out.println("Compliance type: " + res.complianceType);

            if(res.isLimitedDurationDocument == DLParser.DLResult.FLAG_TRUE)
                System.out.println("Limited duration document: " + "yes");
            else if(res.isLimitedDurationDocument == DLParser.DLResult.FLAG_FALSE)
                System.out.println("Limited duration document: " + "no");
            else
                System.out.println("Limited duration document: " + "undefined");

            if(res.isOrganDonor == DLParser.DLResult.FLAG_TRUE)
                System.out.println("Organ donor: " + "yes");
            else if(res.isOrganDonor == DLParser.DLResult.FLAG_FALSE)
                System.out.println("Organ donor: " + "no");
            else
                System.out.println("Organ donor: " + "undefined");

            if(res.isVeteran == DLParser.DLResult.FLAG_TRUE)
                System.out.println("Veteran: " + "yes");
            else if(res.isVeteran == DLParser.DLResult.FLAG_FALSE)
                System.out.println("Veteran: " + "no");
            else
                System.out.println("Veteran: " + "undefined");

            System.out.println("Vehicle class code: " + res.vehicleClassCode);
            System.out.println("Vehicle class code description: " + res.vehicleClassCodeDescription);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void OnMMMReaderError(ErrorCode errorCode, String s) {
        System.out.println("Error in IDScanner: " + errorCode.toString() + ", " + s);
    }
}
