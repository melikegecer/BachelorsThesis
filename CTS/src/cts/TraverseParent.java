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

public class TraverseParent {

    private static ArrayList<CTSClass> classList = new ArrayList<>();
    private static ArrayList<CompilationUnit> cuList = new ArrayList<>();

    public static void main(String[] args) {
        String grandParentPath = "path\\to\\file\\GrandParent.java";
        String parentPath = "path\\to\\file\\Parent.java";
        String childPath = "path\\to\\file\\Child.java";
        String grandChildPath = "path\\to\\file\\GrandChild.java";

        File grandParentFile = new File(grandParentPath);
        File parentFile = new File(parentPath);
        File childFile = new File(childPath);
        File grandChildFile = new File(grandChildPath);

        try {
            CompilationUnit grandParentCU = JavaParser.parse(grandParentFile);
            CompilationUnit parentCU = JavaParser.parse(parentFile);
            CompilationUnit childCU = JavaParser.parse(childFile);
            CompilationUnit grandChildCU = JavaParser.parse(grandChildFile);
            cuList.add(grandParentCU);
            cuList.add(parentCU);
            cuList.add(childCU);
            cuList.add(grandChildCU);

            new ClassVisitor().visit(grandParentCU, null);
            CTSClass grandParentClass = ClassVisitor.getCtsClass();
            classList.add(grandParentClass);

            new ClassVisitor().visit(parentCU, null);
            CTSClass parentClass = ClassVisitor.getCtsClass();
            classList.add(parentClass);

            new ClassVisitor().visit(childCU, null);
            CTSClass childClass = ClassVisitor.getCtsClass();
            classList.add(childClass);

            new ClassVisitor().visit(grandChildCU, null);
            CTSClass grandChildClass = ClassVisitor.getCtsClass();
            classList.add(grandChildClass);

//            System.out.println(checkParent("privateClassOfGrandParent", "GrandChild"));
//            System.out.println(checkParent("protectedClassOfGrandParent", "GrandChild"));
//            System.out.println(checkParent("publicClassOfGrandParent", "GrandChild"));
            System.out.println(checkParent("friendlyClassOfGrandParent", "GrandChild"));

//            System.out.println(checkParent("privateClassOfParent", "GrandChild"));
//            System.out.println(checkParent("protectedClassOfParent", "GrandChild"));
//            System.out.println(checkParent("publicClassOfParent", "GrandChild"));
//            System.out.println(checkParent("friendlyClassOfParent", "GrandChild"));

//            System.out.println(checkParent("privateClassOfChild", "GrandChild"));
//            System.out.println(checkParent("protectedClassOfChild", "GrandChild"));
//            System.out.println(checkParent("publicClassOfChild", "GrandChild"));
//            System.out.println(checkParent("friendlyClassOfChild", "GrandChild"));
        } catch (ParseProblemException ex) {
            System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getLocalizedMessage());
        }
    }

    public static String checkParent(String instanceName, String className) {
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

    private static CompilationUnit findCompilationUnitTree(CTSClass parentCtsClass) {
        for (CompilationUnit compilationUnit : cuList) {
            new ClassVisitor().visit(compilationUnit, null);
//            System.out.println(ClassVisitor.getCtsClass().getName() + " " + ClassVisitor.getCtsClass().getParent());
//            System.out.println(parentCtsClass.getName() + " " + parentCtsClass.getParent());
//            System.out.println("");
            if (ClassVisitor.getCtsClass().equals(parentCtsClass)) {
                return compilationUnit;
            }
        }
        return null;
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

}
