import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyClient {
	public static MyGraph graphMap;

	   public static void main(String[] args) {
	      testMyVerts();
	      testMyEdges(args[1]);
	   }
	   
	   public static MyGraph readGraph(String s1, String s2) {
			Scanner s = null;
			try {
				s = new Scanner(new File(s1));
			} catch (FileNotFoundException e1) {
				System.err.println("FILE NOT FOUND: "+ s1);
				System.exit(2);
			}

			Collection<Vertex> v = new ArrayList<Vertex>();
			while (s.hasNext()) {
				v.add(new Vertex(s.next()));
	      }
	      
			try {
				s = new Scanner(new File(s2));
			} catch(FileNotFoundException e1) {
				System.err.println("FILE NOT FOUND: "+ s2);
				System.exit(2);
			}

			Collection<Edge> e = new ArrayList<Edge>();
			while(s.hasNext()) {
				try {
					Vertex a = new Vertex(s.next());
					Vertex b = new Vertex(s.next());
					int w = s.nextInt();
					e.add(new Edge(a,b,w));
				} catch (NoSuchElementException e2) {
					System.err.println("EDGE FILE FORMAT INCORRECT");
					System.exit(3);
				}
			}
			return new MyGraph(v,e);
		}
	   
	   
	   public static void testMyVerts() {
	      String[] correctVertsArray = {"ATL", "ORD", "DEN", "IAH", "IAD", "MKC", "LAX", "JFK", 
	                                    "SFO", "SEA", "IND"};
	      Set<Vertex> testVertsSet = new HashSet<Vertex>();
	      
	      System.out.println("Testing Verts: ");
	      System.out.println("Correct Verts Array is " + correctVertsArray.toString());
	      
	      for (String correctVert : correctVertsArray) {
	         Vertex temp = new Vertex(correctVert);
	         System.out.println("Adding Vert: " + temp);
	         testVertsSet.add(temp);
	         System.out.println("Test Verts Hash Set is now " + testVertsSet.toString());
	      }
	      System.out.println("Test Verts Hash Set FINAL : " + testVertsSet.toString());
	      
	      if (testVertsSet.equals(graphMap.vertices())) {
	         System.out.println(".equals check PASSED. ");
	      } else {
	         System.out.println(".equals check FAILED. Test Verts Hash Set != MyGraph Vertices. ");
	      }
	      System.out.println();
	   }
	   
	   
	   public static void testMyEdges(String f2) {
	      Scanner s = null;
	      try {
	         s = new Scanner(new File(f2));
	      } catch(FileNotFoundException e1) {
				System.err.println("FILE NOT FOUND: "+f2);
				System.exit(2);
			}
	      
	      System.out.println("Testing Edges: ");
	      Collection<Edge> e = new ArrayList<Edge>();
	      while(s.hasNext()) {
				try {
					Vertex a = new Vertex(s.next());
					Vertex b = new Vertex(s.next());
					int w = s.nextInt();
	            System.out.println("Adding edge -- from: " + a + " to: " + b + " weight: " + w);
					e.add(new Edge(a,b,w));
	            System.out.println("Edge list is now " + e.toString());
				} catch (NoSuchElementException e2) {
					System.err.println("EDGE FILE FORMAT INCORRECT");
					System.exit(3);
				}
			}
	      
			if (e.equals(graphMap.edges())) {
				System.out.println(".equals check PASSED. ");
			} else {
				System.out.println(".equals check FAILED. ");
			}
	      System.out.println();
	   }
}