package com.dwarfeng.familyhelper.finance.impl.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FTP 路径解析器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
@Component
public class FtpPathResolver {

    public static final char ROOT_PATH_STRING_SEPARATOR = '/';
    public static final String ROOT_PATH_STRING_SEPARATOR_STRING = String.valueOf(ROOT_PATH_STRING_SEPARATOR);

    /**
     * 笔记文件的相对路径。
     */
    public static final String[] RELATIVE_PATH_BILL_FILE = new String[]{"bill-file"};

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpPathResolver.class);

    @Value("${ftp.root_path}")
    private String rootPathString;

    private final Lock lock = new ReentrantLock();

    private String[] rootPath = null;

    /**
     * 解析路径。
     *
     * <p>
     * 该方法会在传入的相对路径前方拼接根路径，然后返回拼接后的路径。
     *
     * <p>
     * 对于 <code>relativePath</code> 参数，调用人员应确保只使用如下合法值:
     * <ul>
     *     <li>{@link #RELATIVE_PATH_BILL_FILE}</li>
     * </ul>
     *
     * @param relativePath 相对路径。
     * @return 解析后的路径。
     */
    public String[] resolvePath(@Nonnull String[] relativePath) {
        String[] rootPath = rootPath();
        String[] path = new String[rootPath.length + relativePath.length];
        System.arraycopy(rootPath, 0, path, 0, rootPath.length);
        System.arraycopy(relativePath, 0, path, rootPath.length, relativePath.length);
        return path;
    }

    private String[] rootPath() {
        if (Objects.nonNull(rootPath)) {
            return rootPath;
        }
        // 继续线程安全的懒加载根路径。
        lock.lock();
        try {
            if (Objects.nonNull(rootPath)) {
                return rootPath;
            }
            initRootPath();
            return rootPath;
        } finally {
            lock.unlock();
        }
    }

    private void initRootPath() {
        // 定义临时变量。
        String rootPathStringDejaVu = rootPathString;
        // 去除前后的空格以及分隔符。
        rootPathStringDejaVu = StringUtils.trim(rootPathStringDejaVu);
        // 特殊值处理。
        if (StringUtils.isEmpty(rootPathStringDejaVu)) {
            rootPath = new String[0];
            return;
        }
        // 值校验：如果以分隔符开头或结尾，则进行警告，并置位空路径。
        if (StringUtils.startsWith(rootPathString, ROOT_PATH_STRING_SEPARATOR_STRING)) {
            LOGGER.warn("根路径 {} 非法, 将使用默认的根路径代替", rootPathString);
            rootPath = new String[0];
            return;
        }
        if (StringUtils.endsWith(rootPathString, ROOT_PATH_STRING_SEPARATOR_STRING)) {
            LOGGER.warn("根路径 {} 非法, 将使用默认的根路径代替", rootPathString);
            rootPath = new String[0];
            return;
        }
        // 分割字符串并置位。
        rootPath = StringUtils.split(rootPathStringDejaVu, ROOT_PATH_STRING_SEPARATOR);
    }
}
