import java.lang.reflect.Array;
import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 * @author Yiran Juan
 * @version 02/23/2017
 */
public class MyGraph implements Graph {
    private final Map<Vertex, HashSet<Edge>> graphMap;
    private final Set<Edge> edges;

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	if(v.equals(null) || e.equals(null) || v.size() == 0 || e.size() == 0) {
    		throw new IllegalArgumentException("illegal argument(s) exception");
    	}else {
    		this.graphMap = new HashMap<>();
    		this.edges = new HashSet<>();
    		
    		//add vertex in map so it can be used to find value set later
    		for(Vertex vertex: v) {
    			if(!graphMap.containsKey(vertex)){
    				graphMap.put(new Vertex(vertex.getLabel()), new HashSet<Edge>());
    			}
    		}
    		
    		//add edge in map
    		for(Edge edge: e){
    			//edge weight should not be negative
    			if(edge.getWeight() < 0) {
    				throw new IllegalArgumentException("illegal edge " + edge.toString() + " weight exception");
    			}else {
    				// if the edge has a vertex that doesn't exist in the collection of vertices
    				if(!v.contains(edge.getSource()) && !v.contains(edge.getDestination())) {
    					throw new IllegalArgumentException("edge " + edge.toString() + " doesn't contain vertex");
    				}else {
    					HashSet<Edge> checkedEdge = graphMap.get(edge.getSource());
    					boolean isFound = false;
    					
						for(Edge eachEdge: checkedEdge) {
							// if the edge has same destination but different weight
    						if(edge.getWeight() != eachEdge.getWeight() && edge.getDestination().equals(eachEdge.getDestination())) {
								throw new IllegalArgumentException("existed weight");
							}else {
								if(edge.getWeight() == eachEdge.getWeight() && edge.getDestination().equals(eachEdge.getDestination())){
									isFound = true;
								}
							}
						}
						//when it's not visited in the graph
						if(isFound == false) {
							//add edges to the global variable
							edges.add(new Edge(edge.getSource(), edge.getDestination(), edge.getWeight()));
							graphMap.get(edge.getSource()).add(new Edge(edge.getSource(), edge.getDestination(), edge.getWeight()));
						}
    				}
    			}
    		}
    	}
    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	Collection<Vertex> allVertices = new ArrayList<>();
    	//copy out the vertex
    	for(Vertex eachVertex: graphMap.keySet()){
    		allVertices.add(new Vertex(eachVertex.getLabel()));
    	}
		return allVertices;
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	Collection<Edge> allEdge = new HashSet<>();
    	//copy out the edges
    	for(Edge eachEdge: edges){
    		allEdge.add(new Edge(eachEdge.getSource(), eachEdge.getDestination(), eachEdge.getWeight()));
    	}
		return allEdge;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
		if(!graphMap.containsKey(v)) {
			throw new IllegalArgumentException("invalid vertex argument exception");
		}else {
			List<Vertex> allVertex = new ArrayList<>();
			for(Edge edge: graphMap.get(v)){
				//copy out the edges info
				allVertex.add(new Edge(edge.getSource(), edge.getDestination(), edge.getWeight()).getDestination());
			}
			return allVertex;
		}
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
		if(a.equals(null) || b.equals(null)) {
			throw new IllegalArgumentException("a or b do not exist");
		}else {
			//get the value set in the map 
			Set<Edge> allEdge = graphMap.get(a);
			
			for (Edge edge : allEdge) {
				if (edge.getDestination().equals(b)) {
					return edge.getWeight();
				}
			}
			//if no directed edge from a to b in the graph
			return -1;
		}
    }

}