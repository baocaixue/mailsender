package com.isaac.mailsender.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Paths;

public class FileUtil {
    private final static String classpathPattern = "(?i)classpath:/*(.*)";
    private final static String basepathPattern = "(?i)basepath:/*(.*)";

    private FileUtil() {}

    public static File getFileFromPathString(String path) {
        return getFileFromPathString(path, null);
    }

    private static File getFileFromPathString(String path, String fileName) {
        var concatPath = StringUtils.isEmpty(fileName) ? path : path + "/" + fileName;
        var finalPath = concatPath.replace("\\", "/").replace("//", "/");
        boolean isClassPath = finalPath.matches(classpathPattern);
        boolean isBasepath = finalPath.matches(basepathPattern);
        if (isClassPath) {
            return new File(FileUtil.class.getResource(finalPath.replace("classpath:", "")).getPath());
        } else if (isBasepath) {
            return Paths.get(finalPath.replace("basepath:", "")).toFile();
        } else {
            return new File(finalPath);
        }
    }
}
