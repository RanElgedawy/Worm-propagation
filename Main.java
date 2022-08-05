/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormpropagation1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author ranel
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    private static final int simulN = 3;
    private static final int simulTime = 15000;
    private static final int IPSpace = 100000;

    public enum nodeType {
        Immune,
        Vulnerable,
        Infected
    }

    public static class node {

        public node() {
        }
        public nodeType nodestatus;
        public nodeType nodepriorstatus;
        public int nodestarttime;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //nodeType[] NodeStatus = new nodeType[IPSpace + 1];
        //nodeType[] PriorNodeStatus = new nodeType[IPSpace + 1];
        node[] nodes = new node[IPSpace + 1];
        Integer[][] It = new Integer[simulN+1][simulTime+1];
        //int numvulnerable = 1000;
        Random rand = new Random(); 
        int randomip = 0;  
        int infectedNum=0;
         FileWriter fw = new FileWriter("out.txt");
        //while (true)
        //{
        //initializing all nodes
        for (int i = 1; i <= IPSpace; i++) {
            nodes[i] = new node();
        }
        for (int n = 1; n <= 3; n++) {
            
            for (int i = 1; i <= IPSpace; i++) {
                    nodes[i].nodestatus = nodeType.Immune;
                    nodes[i].nodepriorstatus = nodeType.Immune;
                }
                for (int j = 0; j <= 9; j++) {
                    for (int i = 1; i <= 100; i++) {
                        nodes[i + j * 10000].nodestatus = nodeType.Vulnerable;
                        nodes[i + j * 10000].nodepriorstatus = nodeType.Vulnerable;
                    }
                }
           infectedNum = 1; //number of infected nodes
            int t = 0; //time tick
            nodes[10050].nodestatus = nodeType.Infected;
            nodes[10050].nodepriorstatus = nodeType.Infected; //t=0
            nodes[10050].nodestarttime = 1;
            It[n][t]=1;     
       
        
            while (infectedNum < 1000) {
                t++; 
                It[n][t] = It[n][t-1];
                for (int i = 1; i <= IPSpace; i++) {
                nodes[i].nodepriorstatus = nodes[i].nodestatus;}
                
                for (int i = 1; i <= IPSpace; i++){
                if (nodes[i].nodepriorstatus.equals(nodeType.Infected) && nodes[i].nodestarttime<=t)
                {
                    
                     for (int k=0; k<5;k++)
                {
                    
                    randomip = rand.nextInt(IPSpace) + 1;
                    //System.out.println(randomip);
                    if (nodes[randomip].nodepriorstatus.equals(nodeType.Vulnerable) && nodes[randomip].nodestatus != nodeType.Infected)
                    {
                        nodes[randomip].nodestatus = nodeType.Infected;
                        nodes[randomip].nodestarttime = t+10;
                        infectedNum++;
                        System.out.print("randomip = " + randomip);
                        System.out.print("node status" + nodes[randomip].nodestatus);
                        System.out.println("infected number = " + infectedNum);
                        It[n][t]++;
                    }
                    
                }
                }
                }
                
                System.out.println("Value of simulation (n)" + n + "value of time tick" +t +"Number of infected devices "+  It[n][t]);
                 String s = "n = " + n + " It = " + It[n][t].toString() + "\n";
		fw.write(s);
            }
            
            System.out.println(infectedNum);
        }
        
       
 fw.close();
	
       
        //}
    }

}
