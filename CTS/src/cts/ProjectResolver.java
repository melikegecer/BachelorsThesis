package cts;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.io.FileNotFoundException;

public class ProjectResolver {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("\\path\\to\\file\\Aircraft.java");
        CompilationUnit x = JavaParser.parse(file);
        System.out.println("");
    }
}
