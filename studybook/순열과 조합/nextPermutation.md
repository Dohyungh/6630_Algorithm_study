# next_Permutation

순열처럼 r 을 지정할 수는 없지만,

중복 무시하고 다음 순열을 뽑아주는 것.

알고리즘이 단순해서 외워서 쓰기 좋음.

등호가 있고 없고 차이가 커서 주의해야함.

```java
public static int[] nextPermutation (int[] arr) {
		
		int index = arr.length-2;
		
		while (index >=0) {
			if (arr[index+1] > arr[index]) break;   // 여기만 등호 안들어감!
			index--;
		}
		if (index ==-1) return null;
		
		int big = arr.length-1;
		while (arr[big] <= arr[index]) {
			big--;
		}
		
		int temp = arr[big];
		arr[big] = arr[index];
		arr[index] = temp;
		
        //+1에 주의
		Arrays.sort(arr,index+1,arr.length);
        // 어디 부터 어디"전" 까지만 정렬하는 법.
		
		return arr;
	}
```