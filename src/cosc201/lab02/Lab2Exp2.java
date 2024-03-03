package cosc201.lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cosc201.unionfind.UF1;
import cosc201.unionfind.UnionFind;

/**
 * Lab 2, Experiment 2
 * 
 * Write a shell that can read union-find instructions and execute them.
 * This allows you to compare the behaviour of the various UF algorithms.
 * 
 * @author Michael Albert
 */
public class Lab2Exp2 {

  static Scanner in;
  static UnionFind uf = new UF1();

  public static void main(String[] args) {
    try{
      in = new Scanner(new File(args[0]));
    } catch (FileNotFoundException e) {
      System.out.println(e);
      System.exit(1);
    }

    String line = in.nextLine();
    int n = Integer.parseInt(line);
    uf.make(n);
    while (in.hasNextLine()) {
      process(in.nextLine());
    }


  }

  // Skeleton code below - the first block is complete, fill in the remainder similarly.
  private static void process(String line) {
    String[] tokens = line.split("\\s");
    String command = tokens[0];

    if (command.equals("Union")) {
      System.out.println(line);
      uf.union(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
      return;
    }

    if (command.equals("Find")) {
      return;
    }
    
    if (command.equals("All")) {
      return;
    }
    
    if (command.equals("Summary")) {
      return;
    }
    
    //
  }

}