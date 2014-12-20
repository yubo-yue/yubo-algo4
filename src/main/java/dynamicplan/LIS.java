package dynamicplan;


public class LIS {

	public static int lis(int[] arr) {
		int[] lis = new int[arr.length];
		for (int i = 0; i < lis.length; i++)
		{
			lis[i] = 1;
			
			
		}
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && lis[i] < lis[j]  + 1)
					lis[i] = lis[j] + 1;
			}
		}
		
		int max = 0;
		for (int i = 0; i < lis.length; i ++)
		{
			if (lis[i] > max) max = lis[i];
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
		int lis = LIS.lis(arr);
		System.out.println("LIS of input is : " + lis );
	}

}
