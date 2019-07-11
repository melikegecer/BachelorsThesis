package cse490trials;

public interface InterfaceParent extends InterfaceGrandParent {

//    private SomeClass privateOfInterfaceParent = null; -PRIVATE IS INVALID IN INTERFACE-
//    protected SomeClass protectedOfInterfaceParent = null; -PROTECTED IS INVALID IN INTERFACE-
    public SomeClass publicOfInterfaceParent = null;
    SomeClass friendlyOfInterfaceParent = null;
}
