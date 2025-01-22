package com.idontknow.business.core.utilities;

public class NamingConventionConverter {

    /**
     * 将下划线命名风格转换为大驼峰命名风格.
     *
     * @param input 下划线命名风格的字符串
     * @return 转换后的大驼峰命名风格的字符串
     */
    public static String toPascalCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = true; // 第一个字符总是大写

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '_') {
                nextUpperCase = true; // 遇到下划线，下一个字符大写
            } else if (nextUpperCase) {
                // 如果下一个字符是字母，则将其转换为大写
                result.append(Character.toUpperCase(c));
                nextUpperCase = false;
            } else {
                // 其余字符保持小写
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
    public static String toPascalCaseEntity(String input) {
        return toPascalCase(input) + "Entity";
    }
    public static String toPascalCaseResponse(String input) {
        return toPascalCase(input) + "Response";
    }public static String toPascalCaseCreateRequest(String input) {
        return "Create" +toPascalCase(input) + "Request";
    }
    public static String toPascalCaseUpdateRequest(String input) {
        return "Update" +toPascalCase(input) + "Request";
    }
    public static String toPascalCaseSearchFilter(String input) {
        return "Search" +toPascalCase(input) + "Filter";
    }
    public static String toPascalCaseSearchRequest(String input) {
        return "Search" +toPascalCase(input) + "Request";
    }
    public static void main(String[] args) {
        // 测试用例
        System.out.println(toPascalCaseEntity("hello_world")); // 输出: HelloWorld
        System.out.println(toPascalCaseEntity("user_name"));   // 输出: UserName
        System.out.println(toPascalCaseEntity("__leading_underscores")); // 输出: LeadingUnderscores
        System.out.println(toPascalCaseEntity("trailing_underscores__")); // 输出: TrailingUnderscores
        System.out.println(toPascalCaseEntity("singleword")); // 输出: Singleword
    }
}
