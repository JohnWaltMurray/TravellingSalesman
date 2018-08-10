
public class EDGEheap1
{
  int MAX = 100; //maximum number of heap entries

  private int n; //size of heap
  public EDGE[] heap; //array to hold the heap data

  //default constructor
  public EDGEheap1()
  {
    n=0;
    heap =  new EDGE[MAX]; //reserve an array of size MAX
                                  //for type T objects
    for(int i=0;i<MAX;i++) {
    	heap[i] = new EDGE(0,0,0);
    }
  }

  public void reheap(int j, int m)
  {
    //j is the root index of the subtree
    //m is the final index of the subtree

    int swap = 0; //signal for a swap
    EDGE min; //the smaller of the data values of the children of the current node
    int place; //the array location of the smaller of the children
    do
    {
      if(2*j <= m) //if current node is not a leaf
      {
        if(2*j+1 > m) //there is no right child
        {
          min = heap[2*j]; //the left child
          place = 2*j;
        }
        else
        {
          if(heap[2*j].compareTo(heap[2*j+1]) <= 0) //compare the children
          {
            min = heap[2*j];
            place = 2*j;
          }
          else
          {
            min = heap[2*j+1];
            place = 2*j+1;
          }
        }   
        if(min.compareTo(heap[j]) < 0)  //compare smaller child to parent
        {
          //if smaller child comes before parent, swap
          EDGE temp;
          temp = heap[j];
          heap[j]= heap[place];
          heap[place] = temp;
          swap = 1;
          j = place; //redefine the current parent node for the next pass
        }
        else
        {
          //no swap needed; tree is already a heap; prepare to exit
          swap = 0;
        }
      }
    }while((2*j <= m) && (swap ==1)); //exit when tree is a heap
  }

  public void create_heap()
  {
    for(int i = n/2; i >= 1; i = i-1)
    {
      reheap(i,n); //reheap the subtree beginning at node i
    }
    System.out.println("heap array:");
    for(int i = 1; i<= n; i++)
    {
      System.out.println(heap[i] + "   ");
    }
  }

  public void heapsort()
  {
    System.out.println("  ");
    System.out.println("Heap data in sort order:");
    
    
    for(int i = 1; i <= n; i++)
    {
      System.out.println( heap[1]);
      heap[1] = heap[n-i+1];
      reheap(1, n-i);
    }
    System.out.println("  ");
    n=0;
  }

  public int getSize()
  {
    return n;
  }

  public EDGE dequeue()
  {
     EDGE save = heap[1];
     heap[1] = heap[n];
     n = n-1;
     reheap(1,n);
     return save;
  }

  public void enqueue(EDGE y)
  {
    n = n + 1;
    heap[n] = y;
    create_heap();
  }

  public EDGE getFirst()
  {
    return  heap[1];
  }

}
