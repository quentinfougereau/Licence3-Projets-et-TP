package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = null;
        String pathName = "/home/quentin/Documents/L3 Luminy/Cours/Algorithmique/TP1/formula1.txt";
        //String pathName = "/home/quentin/Documents/L3 Luminy/Cours/Algorithmique/TP1/formula-test.txt";

        int size = 10;
        Graph<String> graph = new Graph<String> (size);
        Graph<String> graphT = new Graph<String>(size);

        try {
            scanner = new Scanner(new File(pathName));

            int varNumbers = scanner.nextInt();
            scanner.nextLine();

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] integerStrings = line.split(" ");

                System.out.println("Ligne : " + line);

                int[] literals = new int[integerStrings.length];
                for (int i = 0; i < literals.length; i++) {
                    literals[i] = Integer.parseInt(integerStrings[i]);
                }

                int[] implicationForm = new int[literals.length*2];

                for (int i = 0; i < literals.length; i++) {
                    if (i == 0)
                        implicationForm[i] = graph.code(- literals[i], varNumbers);
                    else
                        implicationForm[i] = graph.code(literals[i], varNumbers);
                }

                for (int i = literals.length-1; i >= 0; i--) {
                    if (i == 1)
                        implicationForm[i+1] = graph.code(- literals[i], varNumbers);
                    else
                        implicationForm[i+3] = graph.code(literals[i], varNumbers);
                }


                for (int i = 0; i < implicationForm.length; i += 2) {
                    //Affichage test
                    System.out.println("Résultat : " + implicationForm[i] + " => " + implicationForm[i+1]);
                    graph.addArc(implicationForm[i], implicationForm[i+1], "false");
                }


                //Construire le graphe transposé (même graphe avec arêtes inversée)
                for (int i = implicationForm.length-1; i >= 0; i -= 2) {
                    //Affichage test
                    //System.out.println("Résultat Deux : " + implicationForm[i] + " => " + implicationForm[i-1]);
                    graphT.addArc(implicationForm[i], implicationForm[i-1], "false");
                }


            }

            Stack<Integer> stack = new Stack<Integer>();

            graph.pileByEndDate(stack);


            System.out.println("Size : " + stack.size());
            for (int i = 0; i < stack.size(); i++) {
                //System.out.println(stack.get(i));
            }

            System.out.println("CFCs : ------");
            boolean[] visited = new boolean[varNumbers*2];

            for (int i = 0; i < visited.length; i++)
                visited[i] = false;

            while (!stack.empty()) {
                graphT.depthFirstSearch(stack.pop(), visited);
                System.out.println("");
            }






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
