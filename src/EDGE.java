import java.util.Scanner;

public class EDGE implements Comparable<EDGE>
{
    int v1 = 0;
    int v2 = 0;
    int cost = 0;

    public EDGE()
    {
        v1 = 0;
        v2 = 0;
        cost = 0;
    }

    public EDGE(int xx)
    {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("input: vertex v1 = ");
        v1 = in.nextInt();
        System.out.println(v1);
        System.out.println("input: vertex v2 = ");
        v2 = in.nextInt();
        System.out.println(v2);
        System.out.println("input: cost = ");
        cost = in.nextInt();
        System.out.println(cost);
    }

    public EDGE(int x, int y, int z)
    {
        v1 = x;
        v2 = y;
        cost = z;
    }

    public int get_v1()
    {
        return v1;
    }

    public int get_v2()
    {
        return v2;
    }

    public int get_cost()
    {
        return cost;
    }

    public void set_v1(int xx)
    {
        v1 = xx;
    }

    public void set_v2(int yy)
    {
        v2 = yy;
    }

    public void set_cost(int zz)
    {
        cost = zz;
    }

	public int compareTo(EDGE e) {
		// TODO Auto-generated method stub
		return this.cost-e.get_cost();
	}
}