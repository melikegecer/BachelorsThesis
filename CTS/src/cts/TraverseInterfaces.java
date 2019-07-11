package cts;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TraverseInterfaces {

    private static ArrayList<CTSClass> classList = new ArrayList<>();
    private static ArrayList<CompilationUnit> cuList = new ArrayList<>();

    public static void main(String[] args) {
        String grandParentPath = "\\path\\to\\file\\GrandParent.java";
        String interfaceGrandParentPath = "\\path\\to\\file\\InterfaceGrandParent.java";
        String parentPath = "\\path\\to\\file\\Parent.java";
        String interfaceParentPath = "\\path\\to\\file\\InterfaceParent.java";
        String childPath = "\\path\\to\\file\\Child.java";
        String interfaceChildPath = "\\path\\to\\file\\InterfaceChild.java";
        String grandChildPath = "\\path\\to\\file\\GrandChild.java";
        String interfaceGrandChildPath = "\\path\\to\\file\\InterfaceGrandChild.java";

        File grandParentFile = new File(grandParentPath);
        File interfaceGrandParentFile = new File(interfaceGrandParentPath);
        File parentFile = new File(parentPath);
        File interfaceParentFile = new File(interfaceParentPath);
        File childFile = new File(childPath);
        File interfaceChildFile = new File(interfaceChildPath);
        File grandChildFile = new File(grandChildPath);
        File interfaceGrandChildFile = new File(interfaceGrandChildPath);

        try {
            CompilationUnit grandParentCU = JavaParser.parse(grandParentFile);
            cuList.add(grandParentCU);
            new ClassVisitor().visit(grandParentCU, null);
            CTSClass grandParentClass = ClassVisitor.getCtsClass();

            CompilationUnit parentCU = JavaParser.parse(parentFile);
            new ClassVisitor().visit(parentCU, null);
            CTSClass parentClass = ClassVisitor.getCtsClass();
            cuList.add(parentCU);

            CompilationUnit childCU = JavaParser.parse(childFile);
            new ClassVisitor().visit(childCU, null);
            CTSClass childClass = ClassVisitor.getCtsClass();
            cuList.add(childCU);

            CompilationUnit grandChildCU = JavaParser.parse(grandChildFile);
            new ClassVisitor().visit(grandChildCU, null);
            CTSClass grandChildClass = ClassVisitor.getCtsClass();
            cuList.add(grandChildCU);

            CompilationUnit interfaceGrandParentCU = JavaParser.parse(interfaceGrandParentFile);
            new ClassVisitor().visit(interfaceGrandParentCU, null);
            CTSClass interfaceGrandParentClass = ClassVisitor.getCtsClass();
            cuList.add(interfaceGrandParentCU);

            CompilationUnit interfaceParentCU = JavaParser.parse(interfaceParentFile);
            new ClassVisitor().visit(interfaceParentCU, null);
            CTSClass interfaceParentClass = ClassVisitor.getCtsClass();
            cuList.add(interfaceParentCU);

            CompilationUnit interfaceChildCU = JavaParser.parse(interfaceChildFile);
            new ClassVisitor().visit(interfaceChildCU, null);
            CTSClass interfaceChildClass = ClassVisitor.getCtsClass();
            cuList.add(interfaceChildCU);

            CompilationUnit interfaceGrandChildCU = JavaParser.parse(interfaceGrandChildFile);
            new ClassVisitor().visit(interfaceGrandChildCU, null);
            CTSClass interfaceGrandChildClass = ClassVisitor.getCtsClass();
            cuList.add(interfaceGrandChildCU);

//            for (int i = 0; i < classList.size(); i++) {
//                System.out.println(classList.get(i).toString());  
//                System.out.println("");
//                System.out.println("");
//                System.out.println("");
//            }
//            for (int i = 0; i < cuList.size(); i++) {
//                System.out.println(cuList.get(i).toString());                
//                System.out.println("");
//                System.out.println("");
//                System.out.println("");
//            }
//            System.out.println(checkInterfaces("privateClassOfInterfaceGrandParent", "GrandChild"));
//            System.out.println(checkInterfaces("protectedClassOfInterfaceGrandParent", "GrandChild"));
//            System.out.println(checkInterfaces("publicClassOfInterfaceGrandParent", "GrandChild"));
//            System.out.println(checkInterfaces("friendlyClassOfInterfaceGrandParent", "GrandChild"));
//
//            System.out.println(checkInterfaces("privateClassOfInterfaceParent", "GrandChild"));
//            System.out.println(checkInterfaces("protectedClassOfInterfaceParent", "GrandChild"));
//            System.out.println(checkInterfaces("publicClassOfInterfaceParent", "GrandChild"));
//            System.out.println(checkInterfaces("friendlyClassOfInterfaceParent", "GrandChild"));
//
//            System.out.println(checkInterfaces("privateClassOfInterfaceChild", "GrandChild"));
//            System.out.println(checkInterfaces("protectedClassOfInterfaceChild", "GrandChild"));
//            System.out.println(checkInterfaces("publicClassOfInterfaceChild", "GrandChild"));
//            System.out.println(checkInterfaces("friendlyClassOfInterfaceChild", "GrandChild"));
//
//            System.out.println(checkInterfaces("privateClassOfInterfaceGrandChild", "GrandChild"));
//            System.out.println(checkInterfaces("protectedClassOfInterfaceGrandChild", "GrandChild"));
//            System.out.println(checkInterfaces("publicClassOfInterfaceGrandChild", "GrandChild"));
//            System.out.println(checkInterfaces("friendlyClassOfInterfaceGrandChild", "GrandChild"));

        } catch (ParseProblemException ex) {
            System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getLocalizedMessage());
        }
    }

    public static String checkInterfaces(String instanceName, String className) {
        // FIND RELATED CTS CLASS
        CTSClass ctsClass = findCTSClass(className);
        // IF THAT CLASS HAS INTERFACE(S)
        if (ctsClass != null) {
            if (!ctsClass.getInterfaces().isEmpty()) {
                for (String interfaceName : ctsClass.getInterfaces()) {
                    // FIND INTERFACE'S CTSCLASS 
                    CTSClass interfaceClass = findCTSClass(interfaceName);
                    if (interfaceClass != null) {
                        // FIND INTERFACE'S COMPILATION UNIT
                        CompilationUnit compilationUnit = findCompilationUnitTree(interfaceClass);
                        if (compilationUnit != null) {
                            // LIST INTERFACE'S FIELDS
                            new FieldDeclaratorVisitor().visit(compilationUnit, null);
                            for (FieldDeclaration fieldDeclaration : FieldDeclaratorVisitor.getFieldDeclarationList()) {
                                boolean isItPrivate = false;
                                String[] splitted = fieldDeclaration.toString().split(" ");
                                for (int i = 0; i < splitted.length; i++) {
                                    if (splitted[i].equals("private")) {
                                        isItPrivate = true;
                                        break;
                                    } else if (splitted[i].equals("public") || splitted[i].equals("protected")) {
                                        break;
                                    }
                                }
                                // IF METHOD IS PRIVATE, THEN IT CAN NOT BE CALLED FROM THAT CLASS' INTERFACE(S)
                                if (!isItPrivate) {
                                    if (!fieldDeclaration.getVariables().isEmpty()) {
                                        // IF INSTANCE NAME EXIST IN THAT FIELD LIST RETURN INTERFACE'S NAME
                                        if (fieldDeclaration.getVariable(0).toString().equals(instanceName)) {
                                            return interfaceName;
                                        }
                                    }
                                }
                            }
                            // IF INSTANCE NAME IS NOT IN THAT INTERFACE CHECK ITS PARENTS'
                            String parentInterface = checkParent(instanceName, interfaceName);
                            if (parentInterface != null) {
                                return parentInterface;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static CompilationUnit findCompilationUnitTree(CTSClass ctsClass) {
        for (CompilationUnit compilationUnit : cuList) {
            new ClassVisitor().visit(compilationUnit, null);
            if (ClassVisitor.getCtsClass().equals(ctsClass)) {
                return compilationUnit;
            }
        }
        return null;
    }

    private static String checkParent(String instanceName, String className) {
        // FIND THAT CLASS' PARENT
        CTSClass parentCtsClass = findParentCTSClass(className);
        if (parentCtsClass != null) {
            // IF CLASS HAVE PARENT, GET ITS COMPILATION UNIT
            CompilationUnit cu = findCompilationUnitTree(parentCtsClass);
            if (cu != null) {
                new FieldDeclaratorVisitor().visit(cu, null);
                // GET FIELDS OF THAT CLASS
                for (FieldDeclaration fieldDeclaration : FieldDeclaratorVisitor.getFieldDeclarationList()) {
                    boolean isItPrivate = false;
                    String[] splitted = fieldDeclaration.toString().split(" ");
                    for (int i = 0; i < splitted.length; i++) {
                        if (splitted[i].equals("private")) {
                            isItPrivate = true;
                            break;
                        } else if (splitted[i].equals("public") || splitted[i].equals("protected")) {
                            break;
                        }
                    }
                    // IF METHOD IS PRIVATE, THEN IT CAN NOT BE CALLED FROM THAT CLASS' CHILDS
                    if (!isItPrivate) {
                        if (!fieldDeclaration.getVariables().isEmpty()) {
                            // IF INSTANCE NAME EXIST IN THAT FIELD LIST RETURN CLASS NAME
                            if (fieldDeclaration.getVariable(0).toString().equals(instanceName)) {
                                return parentCtsClass.getName();
                            }
                        }
                    }
                }
                // IF IT IS NOT IN THIS CLASS GO TO PARENT'S PARENT
                return checkParent(instanceName, parentCtsClass.getName());
            }
            return null;
        } else {
            return null;
        }
    }

    private static class FieldDeclaratorVisitor extends VoidVisitorAdapter {

        private static ArrayList<FieldDeclaration> fieldDeclarationList = new ArrayList<>();

        @Override
        public void visit(FieldDeclaration fieldDeclaration, Object arg) {
            fieldDeclarationList.add(fieldDeclaration);
        }

        public static ArrayList<FieldDeclaration> getFieldDeclarationList() {
            return fieldDeclarationList;
        }

    }

    private static CTSClass findCTSClass(String className) {
        for (CTSClass ctsClass : classList) {
            if (ctsClass.getName().equals(className)) {
                return ctsClass;
            }
        }
        // IF CLASS DOES NOT EXIST RETURN NULL
        return null;
    }

    private static class ClassVisitor extends VoidVisitorAdapter {

        private static CTSClass ctsClass;

        @Override
        public void visit(ClassOrInterfaceDeclaration classExpr, Object args) {
            ctsClass = new CTSClass();

            ctsClass.setName(classExpr.getNameAsString());

            NodeList<ClassOrInterfaceType> parentList = classExpr.getExtendedTypes();
            if (!parentList.isEmpty()) {
                ctsClass.setParent(parentList.get(0).getNameAsString());
            }

            ArrayList<String> interfaceList = new ArrayList<>();
            NodeList<ClassOrInterfaceType> interfaces = classExpr.getImplementedTypes();
            for (ClassOrInterfaceType aInterface : interfaces) {
                interfaceList.add(aInterface.getNameAsString());
            }
            ctsClass.setInterfaces(interfaceList);

            List<MethodDeclaration> methods = classExpr.getMethods();
            ArrayList<CTSMethod> methodList = new ArrayList<>();
            for (MethodDeclaration method : methods) {
                String name = method.getNameAsString();

                ArrayList<String> parameterList = new ArrayList<>();
                NodeList<Parameter> parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    parameterList.add(parameter.getType().toString());
                }

                boolean overriden = false;
                NodeList<AnnotationExpr> annotations = method.getAnnotations();
                for (AnnotationExpr annotation : annotations) {
                    if (annotation.getNameAsString().equals("Override")) {
                        overriden = true;
                        break;
                    }
                }

                String comment = method.getComment().toString();

                methodList.add(new CTSMethod(name, comment, overriden, parameterList));
            }

            ctsClass.setMethods(methodList);

            classList.add(ctsClass);
        }

        public static CTSClass getCtsClass() {
            return ctsClass;
        }

    }

    private static CTSClass findParentCTSClass(String className) {
        for (CTSClass ctsClass : classList) {
            if (ctsClass.getName().equals(className)) {
                if (ctsClass.getParent() != null) {
                    String parentClassName = ctsClass.getParent();
                    for (CTSClass parentCtsClass : classList) {
                        if (parentCtsClass.getName().equals(parentClassName)) {
                            return parentCtsClass;
                        }
                    }
                }
            }
        }
        // IF CLASS DOES NOT EXIST OR IF CLASS DOES NOT HAVE A PARENT RETURN NULL
        return null;
    }

}
