package com.junyi;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 出入口类型枚举
 * @date 2018/8/23 14:55
 */
public enum ChannelEnum {

    STAND_CHANGE(0,"标准入口"),
    STAND_EXPORT(1,"标准出口"),
    NESTED_IN(2,"小车场嵌套入口"),
    NESTED_OUT(3,"小车场嵌套出口"),
    SEMI_NESTED_IN(4,"半嵌套入口"),
    SEMI_NESTED_OUT(5,"半嵌套出口"),
    SINGLE_ACCESS_IN(6,"单通道入"),
    SINGLE_ACCESS_OUT(7,"单通道出");


    // 成员变量
    private int index;          //标记
    private String name;         //名称

    // 构造方法
    private ChannelEnum(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ChannelEnum c : ChannelEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
