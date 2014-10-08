package com.zigui.utils.enums;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-27
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */
public enum ResourceFolder {
    IMAGE("images"),
    VIDEO("videos");

    private String folder;

    ResourceFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }
}
