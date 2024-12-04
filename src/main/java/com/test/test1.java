package com.test;

import java.util.regex.*;

public class test1 {

    // 定义正则表达式来提取日志中的各个部分
    private static final String LOG_PATTERN =
            "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(.*?)\\] \"(.*?)\" (\\d+) (\\d+|-)";

    public static void main(String[] args) {
        // 直接把日志信息存放在字符串中
        String logData = "110.75.173.48 - - [30/May/2013:23:59:58 +0800] \"GET /thread-36410-1-9.html HTTP/1.1\" 200 68629\n"
                + "220.181.89.186 - - [30/May/2013:23:59:59 +0800] \"GET /forum.php?mod=attachment&aid=Mjg3fDgyN2E0M2UzfDEzNTA2Mjc3MzF8MHwxNTU5 HTTP/1.1\" 200 -\n"
                + "112.122.34.89 - - [30/May/2013:23:59:59 +0800] \"GET /forum.php?mod=ajax&action=forumchecknew&fid=91&time=1369929501&inajax=yes HTTP/1.1\" 200 66";

        // 分割字符串为多行日志
        String[] logLines = logData.split("\n");

        // 逐行解析日志
        for (String line : logLines) {
            parseLog(line);
        }
    }

    // 解析每行日志
    public static void parseLog(String logLine) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);
        Matcher matcher = pattern.matcher(logLine);


        if (matcher.matches()) {
            // 提取各个字段
            String ip = matcher.group(1); // 访问IP
            String timestamp = matcher.group(2); // 访问时间
            String resource = matcher.group(3); // 访问资源
            String status = matcher.group(4); // 请求状态
            String responseSize = matcher.group(5); // 响应大小

            // 处理响应大小为 "-" 的情况
            if ("-".equals(responseSize)) {
                responseSize = "0"; // 或者用 "未知"
            }

            // 打印解析结果
            System.out.println("访问 IP: " + ip);
            System.out.println("访问时间: " + timestamp);
            System.out.println("访问资源: " + resource);
            System.out.println("响应状态: " + status);
            System.out.println("响应大小: " + responseSize + " bytes");
            System.out.println("---------------");
        } else {
            // 如果日志不匹配，打印原始日志行
            System.out.println("日志格式不匹配: " + logLine);
        }
    }
}
