import java.util.Scanner;

public class Graph {
    private int[][] matrix = new int[100][100];//edge-adjacency matrix
    private int n;//number of vertices
    private int e;//number of edges
    private EDGE[] xx = new EDGE[100]; //array to hold up to 100 edges

    public Graph() {
      //interactively input values for the edge-adjacency matrix

        int i;
        int j;
        e = 0;
        int edge_cost;
        Scanner in = new Scanner(System.in);
        System.out.print("\n");
        System.out.print("input number of vertices: n = ");
        n = in.nextInt();
        System.out.println();
        System.out.println("input costs of edges; cost is 0 for all non-edges");
        for (i = 1; i <= n; i++) {
            for (j = i; j <= n; j++) {
                System.out.println();
                System.out.println("cost for edge (" + i + "," + j + ") = ");
                edge_cost = in.nextInt();
//              System.out.println(edge_cost);
                //*** store the cost of the edge between vertices
                //*** i and j in the edge-adjacency matrix (in 2 places) 
                matrix[i][j] = edge_cost;
                matrix[j][i] = edge_cost;
             
         //if there is an edge between i and j, enter the edge into array xx
                if (edge_cost > 0) {
                    e = e + 1;//update the edge count
                //*** create the edge from i to j as an EDGE object 
                    EDGE tempEdge = new EDGE(i, j, edge_cost);
                //*** and store it in xx[e]                
                    xx[e] = tempEdge;

                }
            }
        }
    }
    public void tour() {
    	
    }
    
    public void travellingSalesman() {
    	double numColumns = Math.pow(2, n-1);
    	EDGEheap1 salesHeap = new EDGEheap1();
    	SalesmanTableEntry[][] table = new SalesmanTableEntry[(int)numColumns][n];
    	int[] M = new int[n];
    	int[] size = new int[n];
    	int[] address = new int[n];
    	//int[][] set = new int[(int) numColumns][n];
    	int[][] set = { {},{1},{2},{3},{4},{1,2},{1,3},{2,3},{1,4},{2,4},{3,4},{1,2,3},{1,2,4},{1,3,4},{2,3,4},{1,2,3,4} };
    	int place = 0;
    	for(int p = 1; p <= e; p++){

            //*** set vertex 1 for A.heap[p]
          	salesHeap.heap[p].set_v1(xx[p].get_v1());
            //*** set vertex 2
          	salesHeap.heap[p].set_v2(xx[p].get_v2());
            //*** set the cost
          	salesHeap.heap[p].set_cost(xx[p].get_cost());

        }
    	
    	for(int a = 0; a <= n-2; a++) {
    		M[a] = (int) Math.pow(2, a);
    	}
    	int card = 0;
    	for(int i = 0; i < Math.pow(2, n-1); i++) {
    		card = 0;
    		for(int j = 0; j <= n-2; i++) {
    			if(i != 0 && M[j]!= 0) {
    				card++;
    			}
    		}
    		size[i] = card;
    	}
    	
    	for(int b = 0; b <= n-1; b++) {
    		for(int c = 0; c < numColumns; c++) {
    			if(size[c] == b) {
    				address[c] = place;
    				place++;
    			}
    		}
    	}
    	int j = (int) numColumns;
    	int min;
    	int save = 0;
    	int cost;
    	int r;
    	int val;
    	int dist;
    	boolean containsV = false;
    	for(int p = 0; p < n; p++) {
    		table[p+1][0] = new SalesmanTableEntry(matrix[p][0], 0);
    	}
    	for(int k = 1; k < numColumns; k++) {
    		for(int L = 0; L < n; L++) {
    			table[k][L] = new SalesmanTableEntry(0, 0);
    		}
    	}
    	for(int L = 0; L <= n-1; L++) {
	    	for(int k = 1; k < numColumns; k++) {
	    		min = 99;
	    		for(int I = 0; I < n; I++) {
	    			for(int b = 0; b < set[k].length; b++) {
	    				if(set[k][b] == L){
	    					containsV = true;
	    				}
	    			}
	    			if(!containsV) {
	    				cost = matrix[L][I+1];
	    				r = (int) (j - Math.pow(2, I));
	    				place = address[r];
	    				val = table[I+1][place].getValue();
	    				dist = cost + val;
	    				if(dist < min) {
	    					min = dist;
	    					save = I;
	    				}
	    				
	    			}
	    			
	    			table[L+1][k].setValue(min);
	        		table[L+1][k].setVertex(save);
	    		}
	    		
	    	}
    	}
    	
    	System.out.println("Printing table..."); 
    	for(int i = 0; i <= table.length; i++) {
    		System.out.print(i+"    ");
    	}
    	for(int i = 0; i <= table.length; i++) {
    		for(int g = 0; g <= table[0].length; g++) {
    			System.out.print(table[i][g].getValue()+","+table[i][g].getVertex()+"   ");
    		}
    		System.out.println();
    	}
    	
    	
    }
    public final void kruskal() {

        //*** declare an EDGEheap called A  
        //*** set the number of edge nodes in the heap to equal e
    	EDGEheap1 A = new EDGEheap1();
    	

        //initialize the edge nodes for the heap, A.heap[1], ..., A.heap[e],
        //from the edges xx[1], ..., xx[e]
        for(int p = 1; p <= e; p++){

          //*** set vertex 1 for A.heap[p]
        	A.heap[p].set_v1(xx[p].get_v1());
          //*** set vertex 2
        	A.heap[p].set_v2(xx[p].get_v2());
          //*** set the cost
        	A.heap[p].set_cost(xx[p].get_cost());

          }

        MFS s = new MFS(n); //create the MFS structure 
        System.out.println("\n" + "number vertices =  " + s.get_size());

        //*** display the points table
        System.out.println(matrix);
        //*** display the components table
        System.out.println(xx);

        int i;
        int w1;
        int w2;
        int c1;
        int c2;
        System.out.println("initial heap of edges:");

        //*** turn A.heap, an array of EDGE objects, into a heap
        A.create_heap();
        //main loop of Kruskal's algorithm
        System.out.println("\n" + "edges of MCST:");
        do {
            EDGE curr = A.dequeue();//remove the low-cost edge

            //*** let w1 be vertex 1 of curr
            w1 = curr.get_v1();
            //*** let w2 be vertex 2 of curr
            w2 = curr.get_v2();
            //*** let c1 be the component of w1
            c1 = s.find(w1);
            //*** let c2 be the component of w2
            c2 = s.find(w2);
            //*** if the vertices are from different components, 
            if(c1 > c2) {
            	s.merge(c2, c1);
            } else if (c1 < c2) {
            	s.merge(c1, c2);
            }
            //*** merge the smaller component into the larger

            //  and print the new edge for the MCST
                System.out.println("\n");
                System.out.println(curr.get_v1() + "   " + curr.get_v2()+"   " +curr.get_cost());
            
            
           } while (s.get_num_comp() != 1);// loop ends when there is 1 component, i.e. the
                                        // subgraph is now a MCST
    }

   
}