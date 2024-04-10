package cosc201.lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

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
      in = new Scanner(new File("myfile2.txt"));
    } catch (FileNotFoundException e) {
      System.out.println(e);
      System.exit(1);
    }

    String line = in.nextLine();
    int n = Integer.parseInt(line);
    uf.make(n);
    System.out.println("Size " + n);
    while (in.hasNextLine()) {
      process(in.nextLine());
    }


  }

  // Skeleton code below - the first block is complete, fill in the remainder similarly.
  private static void process(String line) {
    String[] tokens = line.split("\s");
    String command = tokens[0];

    if (command.equals("Union")) {
      uf.union(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
      System.out.println(line);
      return;
    }

    if (command.equals("Find")) {
      System.out.println(line + ": " + uf.find(Integer.parseInt(tokens[1])));
      return;
    }
    
    if (command.equals("All")) {
      int ref = uf.find(Integer.parseInt(tokens[1]));
      String s = "";
      for(int i = 0; i < uf.size(); i++){
        if(uf.find(i) == ref){
          s += i + " ";
        }
      }
      System.out.println(line + ": " + s);
      return;
    }
    
    if (command.equals("Summary")) {
      System.out.println(line);
      ArrayList<Integer> seen = new ArrayList<Integer>();
      String s = "";
      for(int i = 0; i < uf.size(); i++){
        int ref = uf.find(i);
        if(!seen.contains(ref)){
          seen.add(ref);
          s = "";
          for(int j = 0; j < uf.size(); j++){
            if(uf.find(j) == ref){
              s += j + " ";
            }
          }
          System.out.println(s);
        }

      }
      return;
    }
  }

}