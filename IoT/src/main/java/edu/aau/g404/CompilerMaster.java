package edu.aau.g404;

import edu.aau.g404.CodeGenerator.CodeGenerator;
import edu.aau.g404.ContextualAnalyzer.ContextualAnalyzer;
import edu.aau.g404.LexicalAnalyzer.LexiScanner;
import edu.aau.g404.LexicalAnalyzer.TreeConstructionWorker;

import java.util.ArrayList;

public class CompilerMaster {

    private static CompilerMaster instance = null;
    private String helloWorld = "Hello world";
    private LexiScanner lexiScanner;
    private TreeConstructionWorker tokenManager;
    private ContextualAnalyzer contextualAnalyzer;
    private CodeGenerator codeGenerator;

    private Token ast;


    private CompilerMaster(){
        lexiScanner = new LexiScanner("IoT/src/main/java/edu/aau/g404/TestProgram.txt");
        tokenManager = new TreeConstructionWorker();
        contextualAnalyzer = new ContextualAnalyzer();
        codeGenerator = new CodeGenerator();
    }

    public static CompilerMaster getInstance(){
        if(instance == null){
            instance = new CompilerMaster();
        }
        return instance;
    }

    public void printHelloWorld(){
        System.out.println(helloWorld);
    }



    public void runCompiler(){
        //ArrayList tokenList = lexiScanner.scanner(); //Scanner returning an arraylist of tokens
        //lexiScanner.printTokens();
        ast = tokenManager.astBuilder(lexiScanner.scanner()); //Parser returning the root token of the AST

        tokenManager.printTree(ast); // An attempt to print the AST in a readable form
        prettyPrintCodeReverse(ast);

        //contextualAnalyzer.depthFirstTraverser(root);

        contextualAnalyzer.checkForTypeErrors(ast);

        codeGenerator.execute(ast);
    }


    public void prettyPrintCodeReverse(Token node){
        if (node.getChildren()!=null){
            for (Token t: node.getChildren()) {
                prettyPrintCodeReverse(t);
            }
        } else {
            //work on the node is done here
            System.out.print(node.getValue());
            if (node.getValue().equals(";")){
                System.out.println("");
            } else {
                System.out.print(" ");
            }
        }
    }
}