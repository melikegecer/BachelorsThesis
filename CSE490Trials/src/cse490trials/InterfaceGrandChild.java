package cse490trials;

public interface InterfaceGrandChild extends InterfaceChild {

//    private SomeClass privateOfInterfaceGrandChild = null; -PRIVATE IS INVALID IN INTERFACE-
//    protected SomeClass protectedOfInterfaceGrandChild = null; -PROTECTED IS INVALID IN INTERFACE-
    public SomeClass publicOfInterfaceGrandChild = null;
    SomeClass friendlyOfInterfaceGrandChild = null;
}
