package cse490trials;

public interface InterfaceChild extends InterfaceParent {

//    private SomeClass privateOfInterfaceChild = null; -PRIVATE IS INVALID IN INTERFACE-
//    protected SomeClass protectedOfInterfaceChild = null; -PROTECTED IS INVALID IN INTERFACE-
    public SomeClass publicOfInterfaceChild = null;
    SomeClass friendlyOfInterfaceChild = null;
}
