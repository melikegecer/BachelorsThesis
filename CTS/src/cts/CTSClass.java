package cts;

import java.util.ArrayList;

/**
 *
 * @author Melike Geçer
 */
public class CTSClass {

    private String name;
    private String parent;
    private ArrayList<String> interfaces;
    private ArrayList<CTSMethod> methods;

    public CTSClass() {
    }

    public ArrayList<String> getInterfaces() {
        return interfaces;
    }

    public ArrayList<CTSMethod> getMethods() {
        return methods;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public void setInterfaces(ArrayList<String> interfaces) {
        this.interfaces = interfaces;
    }

    public void setMethods(ArrayList<CTSMethod> methods) {
        this.methods = methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "NAME: " + name + "\nPARENT: " + parent + "\nINTERFACES: " + interfaces.toString() + "\nMETHODS: " + methods.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CTSClass) {
            CTSClass ctsClass = (CTSClass) obj;
            if (this.getName() != null && ctsClass.getName() != null) {
                if (this.getName().equals(ctsClass.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
