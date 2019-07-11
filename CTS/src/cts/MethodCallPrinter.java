package cts;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodCallPrinter {

    public static void main(String[] args) throws Exception {
//        String line = "int x = (3+1)/2;";
//        String line = "int x = (int) 3.5;";
//        String line = "x = add(a,b);";
//        String line = "add(a,b);";
//        String line = "add(mult(a,b), c);";
//        String line = "FakeClass x = new FakeClass();" + "x.add(a,b);";
//        String line = "x.setX(x.getX()+1);";
// XXX bölye bişi olabilir mi?
//        String line = "x.getX().getY();"; 
//        String dummyCode = "public class DumDum { public void dummymethod() { " + line + " } }";

//        String line2 = "public void melo()  {}";
//        String line2 = "public DumDum() {}";
//        String dummyCode2 = "public class DumDum { " + line2 + "}";
        // BURDA Bİ SORUN VAR
//        String line3 = "@MimeRegistration()";
//        String dummyCode3 = "public class DumDum " + line3 + " implements CompletionProvider {}";
        // BURDA Bİ SORUN VAR
//        String line4 = " // add();";
//        String dummyCode4 = "public class DumDum { " + line4 + " public void add(){} }";
//        String dummyCode4 = "public class DumDum { public void add(){ " + line4 +  " } }";
        String dummyCode = "public class DumDum { int xoxo = 5; public DumDum() {} public void doSomething() { AClassBClass.doSomesomething(); SomeClass sc = getSomeClass(); this.dodo(); SomeClass sscc = new SomeClass();} }";
        CompilationUnit cu = JavaParser.parse(dummyCode);
        new MethodCallVisitor().visit(cu, null);
    }

    private static class MethodCallVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodCallExpr methodCall, Object arg) {
            System.out.println(methodCall.getScope().toString().replace("Optional[", "").replace("]", ""));
        }
    }
}
