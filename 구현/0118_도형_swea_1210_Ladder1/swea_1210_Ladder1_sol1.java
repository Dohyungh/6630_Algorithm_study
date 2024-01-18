package swea_1210_Ladder1;
import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			test_case = sc.nextInt();
            int[][] arr = new int[100][100];
            for (int r=0;r<100;r++)
            {
                for (int c=0;c<100;c++)
                {
                    arr[r][c] = sc.nextInt();
                }
            }

            int found = 0;
            for (int start = 0; start<100;start++)
            {
//                System.out.println(start);
                if (arr[0][start] == 1 && move(0,start,arr) == 1) 
                {
                    found = start;
                }
            }
            System.out.printf("#%d %d%n",test_case,found);
		}
	}
        
        

    public static int move(int r, int c, int[][] map)
    {
        int dingdong = 0;
        while (r<99) {
//        	System.out.printf("%d %d //",r,c);
	        if(c>0 && map[r][c-1]==1) {
	        	while(c!=0 && map[r][c-1]==1) {
	        		c--;
	        	}
	        	r++;
	        	continue;
	        }
	        else if(c<99 && map[r][c+1]==1) {
	        	while(c!=99 && map[r][c+1]==1) {
	        		c++;
	        	}
	        	r++;
	        	continue;
	        } else {
	        	r++;
	        }
	        if (map[r][c]==2) {
	        	dingdong = 1;
	        	
	        }
	        

        }
        return dingdong;
    }
}
