package cts;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindScope {

    private static ArrayList<String> lines = new ArrayList<>();
    private static String path = "\\path\\to\\file\\Aircraft.java";
    private static CompilationUnit compilationUnit;
    private static String instanceType = null;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(path);
        Scanner scn = new Scanner(file);

        while (scn.hasNextLine()) {
            lines.add(scn.nextLine());
        }

        try {
            compilationUnit = JavaParser.parse(file);
            new ClassOrInterfaceDeclarationVisitor().visit(compilationUnit, null);
            String className = ClassOrInterfaceDeclarationVisitor.getClassOrInterfaceDeclarationList().get(0).getNameAsString();
            System.out.println(className);
        } catch (ParseProblemException ex) {
            System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
        }
    }

    private static class ClassOrInterfaceDeclarationVisitor extends VoidVisitorAdapter {

        private static ArrayList<ClassOrInterfaceDeclaration> classOrInterfaceDeclarationList = new ArrayList<>();

        @Override
        public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Object arg) {
            classOrInterfaceDeclarationList.add(classOrInterfaceDeclaration);
        }

        public static ArrayList<ClassOrInterfaceDeclaration> getClassOrInterfaceDeclarationList() {
            return classOrInterfaceDeclarationList;
        }

    }

    // DO NOT CHECK INITIALIZER. IT MIGHT NOT BE A CONSTRUCTOR CALL.
    // TODO: WHEN INSTANCE TYPE IS FOUND CHECK THAT CLASS ( ? AND ITS CHILDS ? )
    private static void findObjectType(String instanceName, int lineIndex) {
        // START FROM 1 LINE ABOVE WHERE METHOD IS CALLED
        int currentLineIndex = lineIndex - 1;

        // IF OBJECT CREATION IS IN METHOD, IT HAS TO BE IN THE LINES ABOVE THE CURRENT LINE
        //<editor-fold defaultstate="collapsed" desc="TRAVEL UNLESS A METHOD DECLARATION OR A CONSTRUCTOR DECLARATION.">
        while (0 <= currentLineIndex) {
            String dummyCode;
            CompilationUnit cu;

            //<editor-fold defaultstate="collapsed" desc="CHECK IF CURRENT LINE HAS THE INSTANCE'S NAME.">
            String regex = ".*\\s" + instanceName + "\\s.*";
            if (lines.get(currentLineIndex).matches(regex)) {
                dummyCode = "public class DumDum { public void dumDumDum() { " + lines.get(currentLineIndex) + " } }";
                try {
                    cu = JavaParser.parse(dummyCode);
                    new VariableDeclaratorVisitor().visit(cu, null);
                    //<editor-fold defaultstate="collapsed" desc="IF INSTANCE'S NAME EXISTS THEN CHECK IF THERE IS AN OBJECT CREATION. IF SO GET INSTANCE TYPE AND INITIALIZER. BREAK FREE!">
                    if (!VariableDeclaratorVisitor.getVariableDeclaratorList().isEmpty()) {
                        VariableDeclarator variableDeclarator = VariableDeclaratorVisitor.getVariableDeclaratorList().get(0);
                        instanceType = variableDeclarator.getType().toString();
                        break;
                    }
                } catch (ParseProblemException ex) {
                    System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
                }
                //</editor-fold>
            } else {
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="IF CURRENT LINE DO NOT HAVE INSTANCE NAME THEN CHECK IF IT REACHED METHOD DECLARATION OR CONSTRUCTOR DECLARATION.">
                // TODO: kıvırcık parantez açma kapama kontrol et ve ona göre line ı değiştir GEÇİCİ
                dummyCode = "public class DumDum { " + lines.get(currentLineIndex) + "} }";
                try {
                    cu = JavaParser.parse(dummyCode);
                    new MethodDeclarationVisitor().visit(cu, null);

                    //<editor-fold defaultstate="collapsed" desc="IF THERE IS METHOD DECLARATION, CHECK IT'S PARAMETERS. IF METHOD DECLARATION HAS INSTANCE IN ITS PARAMETERS GET INSTANCE'S TYPE. BREAK FREE!">
                    if (!MethodDeclarationVisitor.getMethodDeclarationList().isEmpty()) {
                        MethodDeclaration methodDeclaration = MethodDeclarationVisitor.getMethodDeclarationList().get(0);
                        NodeList<Parameter> parameterList = methodDeclaration.getParameters();
                        for (Parameter parameter : parameterList) {
                            if (parameter.getNameAsString().equals(instanceName)) {
                                instanceType = parameter.getType().toString();
                                break;
                            }
                        }
                        break;
                    }
                } catch (ParseProblemException ex) {
                    System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="IF THERE IS CONSTRUCTOR DECLARATION, CHECK IT'S PARAMETERS. IF CONSTRUCTOR DECLARATION HAS INSTANCE IN ITS PARAMETERS GET INSTANCE'S TYPE. BREAK FREE!">
                // TODO: kıvırcık parantez açma kapama kontrol et ve ona göre line ı değiştir GEÇİCİ
                dummyCode = "public class DumDum { " + lines.get(currentLineIndex) + "} }";
                try {
                    cu = JavaParser.parse(dummyCode);
                    new ConstructorDeclaratorVisitor().visit(cu, null);
                    if (!ConstructorDeclaratorVisitor.getConstructorDeclarationList().isEmpty()) {
                        ConstructorDeclaration constructorDeclaration = ConstructorDeclaratorVisitor.getConstructorDeclarationList().get(0);
                        NodeList<Parameter> parameterList = constructorDeclaration.getParameters();
                        for (Parameter parameter : parameterList) {
                            if (parameter.getNameAsString().equals(instanceName)) {
                                instanceType = parameter.getType().toString();
                                break;
                            }
                        }
                        break;
                    }
                } catch (ParseProblemException ex) {
                    System.out.println("ParseProblemException: " + ex.getLocalizedMessage());
                }
                //</editor-fold>
                //</editor-fold>
            }
            currentLineIndex--;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="IF INSTANCE TYPE IS NULL, CHECK CLASS VARIABLES. (NO NEED TO CHECK INITIALIZER, BECAUSE OF POLYMORPHISM)">
        if (instanceType == null) {
            checkClassFields(instanceName);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="THE LAST THINGS TO CHECK ARE PARENT CLASS AND INTERFACE(S) OF THE CURRENT CLASS.">
        //</editor-fold>
    }

    private static void checkClassFields(String instanceName) {
        // IN ORDER TO CHECK THE CURRENT CLASS' FIELDS, WE SHOULD GET ITS COMPILATION UNIT TREE
        new FieldDeclaratorVisitor().visit(compilationUnit, null);
        ArrayList<FieldDeclaration> fieldDeclarationList = FieldDeclaratorVisitor.getFieldDeclarationList();
        for (FieldDeclaration fieldDeclaration : fieldDeclarationList) {
            if (!fieldDeclaration.getVariables().isEmpty()) {
                if (fieldDeclaration.getVariable(0).toString().equals(instanceName)) {
                    String dummyCode = "public class DumDum { " + fieldDeclaration.toString() + " }";
                    new VariableDeclaratorVisitor().visit(JavaParser.parse(dummyCode), null);
                    if (!VariableDeclaratorVisitor.getVariableDeclaratorList().isEmpty()) {
                        instanceType = VariableDeclaratorVisitor.getVariableDeclaratorList().get(0).getType().toString();
                        break;
                    }
                }
            }
        }
    }

    // FIND LINE NUMBER USING CURRENT INDEXS
    private static int findLineIndex(int currentIndex) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            // line ın uzunluğu sıfır olsa bile \n karakteri var o yüzden +1 yapmalı
            if (lines.get(i).length() == 0) {
                count++;
            } else {
                count += lines.get(i).length();
            }
            // current index i bulunca return et
            if (count >= currentIndex) {
                return i;
            }
        }
        return -1;
    }

    // USED TO FIND CLASS FIELDS
    // private int x = 0;
    // private Item item;
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

    // USED TO FIND A CONSTRUCTOR DECLARATION
    // public SomeClass() {...}
    private static class ConstructorDeclaratorVisitor extends VoidVisitorAdapter {

        private static ArrayList<ConstructorDeclaration> constructorDeclarationList = new ArrayList<>();

        @Override
        public void visit(ConstructorDeclaration constructorDeclaration, Object arg) {
            constructorDeclarationList.add(constructorDeclaration);
        }

        public static ArrayList<ConstructorDeclaration> getConstructorDeclarationList() {
            return constructorDeclarationList;
        }

    }

    // USED TO FIND VARIABLE DECLARATION
    // x = 0;
    // sc = new SomeClass();
    private static class VariableDeclaratorVisitor extends VoidVisitorAdapter {

        private static ArrayList<VariableDeclarator> variableDeclaratorList = new ArrayList<>();

        @Override
        public void visit(VariableDeclarator variableDeclarator, Object args) {
            variableDeclaratorList.add(variableDeclarator);
        }

        public static ArrayList<VariableDeclarator> getVariableDeclaratorList() {
            return variableDeclaratorList;
        }

    }

    // USED TO FIND METHOD DECLARATION
    // public void doSomething() {...}
    private static class MethodDeclarationVisitor extends VoidVisitorAdapter {

        private static ArrayList<MethodDeclaration> methodDeclarationList = new ArrayList<>();

        @Override
        public void visit(MethodDeclaration methodDeclaration, Object arg) {
            methodDeclarationList.add(methodDeclaration);
        }

        public static ArrayList<MethodDeclaration> getMethodDeclarationList() {
            return methodDeclarationList;
        }

    }

    private static class MethodCallVisitor extends VoidVisitorAdapter {

        private static ArrayList<MethodCallExpr> methodDeclarationList = new ArrayList<>();

        @Override
        public void visit(MethodCallExpr methodDeclaration, Object arg) {
            NodeList<Expression> typeList = methodDeclaration.getArguments();
            for (Expression expression : typeList) {
                System.out.println(expression.toString());
            }
        }

        public static ArrayList<MethodCallExpr> getMethodDeclarationList() {
            return methodDeclarationList;
        }

    }
}
