package cts;

import java.util.ArrayList;

/**
 *
 * @author Melike Geçer
 */
public class CTSMethod {

    private String name;
    private String comment;
    private boolean overriden;
    private ArrayList<String> parameters;

    public CTSMethod(String name, String comment, boolean overriden, ArrayList<String> parameters) {
        this.name = name;

        if (comment.equals("Optional.empty")) {
            this.comment = "No comment for " + name + " method";
        } else {
            this.comment = comment;
        }
        this.overriden = overriden;
        this.parameters = parameters;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public boolean isOverriden() {
        return overriden;
    }

    @Override
    public String toString() {
        return "NAME: " + name + "\nCOMMENT: " + comment + "\nIS OVERRIDEN: " + overriden + "\nPARAMETERS: " + parameters.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CTSMethod) {
            CTSMethod ctsMethod = (CTSMethod) obj;
            if (this.getName().equals(ctsMethod.getName()) && this.getParameters().size() == ctsMethod.getParameters().size()) {
                for (int i = 0; i < this.getParameters().size(); i++) {
                    if (!this.getParameters().get(i).equals(ctsMethod.getParameters().get(i))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
