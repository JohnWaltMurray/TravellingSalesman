public class MFS
{
    private int n; //size
    private int comp; //number of components
    private int[][] points = new int[100][3];
    private int[][] components = new int[100][4];

    //default constructor
    public MFS()
    {
        n = 0;
        comp = 0;
    }

    //parameterized constructor
    public MFS(int nn)
    {
        int i;
        n = nn;
        comp = nn;
        for (i = 1; i <= n; i++)
        {
            points[i][1] = i;
            points[i][2] = 0;
            components[i][1] = i;
            components[i][2] = 1;
            components[i][3] = i;
        }
    }

    public final void merge(int small_comp, int large_comp)
    {
        int save=0;
        int place = components[small_comp][3];
        components[large_comp][2] = components[large_comp][2] + components[small_comp][2];

        while (place != 0)
        {
            points[place][1] = large_comp;
            save = place;
            place = points[place][2];
        }
        points[save][2] = components[large_comp][3];

        components[large_comp][3] = components[small_comp][3];

        components[small_comp][1] = 0;
        components[small_comp][2] = 0;
        components[small_comp][3] = 0;
        comp = comp - 1;
    }

    public final int find(int point)
    {
        return points[point][1];
    }

    public final int get_size()
    {
        return n;
    }

    public final int get_num_comp()
    {
        return comp;
    }
    public final int get_comp_size(int j)
    {
        return components[j][2];
    }

    public final void get_points()
    {   
        System.out.println('\n'+ "Points Table"); 
        System.out.println('\n'+ "Point " + "comp. " + "Link" + '\n');  
        int i;
        for (i = 1; i <= n; i++)
        {
            System.out.println(i + "      " + points[i][1] + "     " + points[i][2]);
        }
        System.out.println();
        System.out.println();
    }

    public final void get_components()
    {
        System.out.println('\n'+ "Components Table"); 
        System.out.println('\n'+ "Comp. " + "size " + "start" + '\n');  
        int i;
        for (i = 1; i <= n; i++)
        {
            System.out.println(components[i][1] + "        " + components[i][2] + "   " +             components[i][3]);
        }
        System.out.println();
        System.out.println();
    }
}