package com.pollchihuy.util;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFile {
    private static StringBuilder sbuilds = new StringBuilder();

//    private static Logger logger = LoggerFactory.getLogger(LoggingFile.class);//logback
    private static Logger logger = LogManager.getLogger(LoggingFile.class);

    public static void exceptionStringz(String strClass,
                                        String strMethod,
                                        Exception e, String flag) {
        if(flag.equals("y"))
        {

//            debug, trace, info , warn,error , fatal
            sbuilds.setLength(0);
            logger.warn(sbuilds.append(System.getProperty("line.separator")).
                    append("ERROR IN CLASS =>").append(strClass).append(System.getProperty("line.separator")).
                    append("METHOD   =>").append(strMethod).append(System.getProperty("line.separator")).
                    append("ERROR IS       =>").append(e.getMessage()).
                    append(System.getProperty("line.separator")).toString());
            sbuilds.setLength(0);
        }
    }

    public static void exceptionStringz(String[] datax,
                                        Exception e, String flag) {
        if(flag.equals("y"))
        {
            sbuilds.setLength(0);
            logger.warn(sbuilds.append(System.getProperty("line.separator")).
                    append("ERROR IN CLASS =>").append(datax[0]).append(System.getProperty("line.separator")).
                    append("METHOD   =>").append(datax[1]).append(System.getProperty("line.separator")).
                    append("ERROR IS       =>").append(e.getMessage()).
                    append(System.getProperty("line.separator")).toString());
            sbuilds.setLength(0);
        }
    }
}