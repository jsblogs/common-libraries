package com.jsblogs.ratelimiter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.jsblogs.ratelimiter.utils.Constants.EMPTY_STRING;

public final class ApplicationUtils {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationUtils.class);
    private static final AntPathMatcher urlPathMatcher = new AntPathMatcher();

    private ApplicationUtils() {
    }

    public static String getErrorResponse(String error) {
        String errorFormat = getFileContent("limit-exceeds-error.json");
        return String.format(errorFormat, error);
    }

    public static String getFileContent(String fileName) {
        try {
            URL fileUrl = ApplicationUtils.class.getClassLoader()
                    .getResource(fileName);
            if (fileUrl != null) {
                byte[] bytes = Files.readAllBytes(Paths.get(fileUrl.toURI()));
                if (bytes.length > 0) {
                    return new String(bytes);
                }
            }
        } catch (Exception e) {
            logger.error("Error during reading file content. " + e.getMessage(), e);
        }
        return EMPTY_STRING;
    }

    public static boolean urlMatch(String pattern, String url) {
        return urlPathMatcher.match(pattern, url);
    }
}
