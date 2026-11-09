package cn.edu.sicnu.chenhaoran.s2024110203;

import java.io.Serializable;

public final class ChenHaoran2024110203Student implements Serializable {
    private static final long serialVersionUID = 1L;
    public final String number;
    public final String name;
    public final boolean female;
    public final String className;

    public ChenHaoran2024110203Student(String number, String name, boolean female, String className) {
        this.number = number;
        this.name = name;
        this.female = female;
        this.className = className;
    }
}
