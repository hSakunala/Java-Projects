/*
 * Find out which actor had the most roles.
 */

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;


public class BusyActor {

  public static void main(String[] args) throws Exception {
		//String fname = "../resource/asnlib/public/ActorRoles";
		String fname = "ActorRoles";
		//String fname = "ShortActorRoles";
    BufferedReader in;
		String content;
		//String[] tkn;
		long starting = System.currentTimeMillis();
		long start = System.currentTimeMillis();

    in = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF-8"));


		int maxRoles = 0;
    String busyActor = "";
    while ((content = in.readLine()) != null) {
	//System.out.println(content);
      String[] tkn = content.split("\\t");
	//System.out.println(tkn[0]+ " " + tkn.length);
      int roleCount = (tkn.length-1);
      if(roleCount > maxRoles) {
          maxRoles = roleCount;
          busyActor = tkn[0];
      }
    }
    System.out.println("Busiest actor: "+busyActor+" with "+maxRoles+" roles.");
    in.close();
    }
  }
  
