package cts;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateCompilationUnitTrees {

    static ArrayList<CompilationUnit> compilationUnitTrees = new ArrayList<>();
    static ArrayList<CTSClass> classList = new ArrayList<>();

    public static void main(String[] args) {
        generateCompilationUnitTrees();
        for (CompilationUnit compilationUnitTree : compilationUnitTrees) {
            new ClassVisitor().visit(compilationUnitTree, null);
            CTSClass c = ClassVisitor.getCtsClass();
            System.out.println(c.getName());
            System.out.println("");
        }

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

    private static void generateCompilationUnitTrees() {
        // ŞU AN Kİ PROJE NİN BULUNDUĞU DIRECTORY 
//        String root = System.getProperty("user.dir");
//        File folder = new File(root + "/src");
        String root = "\\path\\to\\file\\CodeCompletion";
        File folder = new File(root + "\\src");
        listFilesForFolder(folder);
    }

    private static void listFilesForFolder(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                // CHECK FILE EXTENTION IF IT IS .java THEN GENERATE ITS COMPILATION UNIT TREE
                if (fileEntry.getName().endsWith(".java")) {
                    try {
                        CompilationUnit compilationUnit = JavaParser.parse(fileEntry);
                        compilationUnitTrees.add(compilationUnit);
                    } catch (ParseProblemException ex) {
                        System.out.println("ParseProblemException: " + ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println("IOException: " + ex.getMessage());
                    }
                }
            }
        }
    }

}
