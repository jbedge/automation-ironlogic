package com.ironlogic.data;

public enum RunTimeData {

    Drop_Down_Value("Organization"),
    ;

    private String value;
    private String key;

    RunTimeData(String value) {
        this.value=value;
    }

    RunTimeData(String key, String value) {
        this.key=key;
        this.value=value;
    }

    public String getValue(){
        return value;
    }

    public String getKey(){
        return key;
    }

    @Override
    public String toString() {
        return value;
    }

//    public static void main(String[] args) {
//        System.out.println(hamburgermenus.getValue());
//        System.out.println(hamburgermenu.getValue());
//        System.out.println(hamburgermenu.getKey());
//    }
}
