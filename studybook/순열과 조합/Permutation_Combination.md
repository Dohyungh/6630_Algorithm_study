# Permutation
순열, nPr = n 개중에 r개를 뽑아 순서를 세우는 경우의 수  

시간복잡도 : `O(n!)`

```java
public static void main(String[] args) {
    int arr[] = {1,1,2,3,3,3};
    int output[] = new int[arr.length];
    boolean visited[] = new boolean[arr.length];
    
    permutation(arr,output,visited,0,arr.length);
    
}

public static void permutation(int arr[], int output[], boolean visited[], int depth, int r) {
    if (depth == r) {
        for(int i = 0; i<r; i++) { // 출력을 r 까지만 함
            System.out.print(output[i] + " ");
        }
        System.out.println();
        return;
    }
    for(int i = 0; i<arr.length; i++) {
        if(!visited[i]) {
            visited[i] = true;
            output[depth] = arr[i]; // 순서를 나타내는 depth 에 원소를 삽입함.
            permutation(arr,output, visited, depth+1,r);
            visited[i] = false;
        }
    }
}
```

원래 주어진 배열과 같은 크기의 boolean **visited** 배열 생성  
원래 주어진 배열과 같은 크기의 int **output** 배열 생성  
> output 은 크게 만들어 놓고, r 까지만 출력할 것임.

*'depth'* 와 **'r'** 선언.

depth 는 0부터 시작해서 r 까지 점점 커짐.  
한개씩 원소를 순회하면서 방문하지 않은 원소라면,방문한 것으로 바꾸고 그 후,  

    1. visited 업데이트
    2. output의 depth 자리에 방문한 해당 원소 삽입
    3. depth + 1
    4. 업데이트 된 변수들을 가지고 다시 재귀 호출
   
만약 depth가 r 까지 왔다면,  

    1. 최종 output 배열에서 r 까지만 출력.


# Combination
조합, nCr = n 개중에 r개를 뽑는 경우의 수

시간복잡도 : `O(2^n)`

```java 

public static void main(String[] args) {
    int[] arr = {1,1,2,3,3,3};
    boolean visited[] = new boolean[arr.length];
    
    combination(arr, visited, 0,0,2);

}

public static void combination(int[] arr, boolean[] visited, int start, int depth, int r) {
    if (depth == r) {
        for(int i =0; i<arr.length; i++) { // 모든 원소에 대해 visited 를 검사함
            if(visited[i]) System.out.print(arr[i]+" ");
        }
        System.out.println();
        return;
    }
    for (int i = start; i<arr.length; i++) {
        if (!visited[i]) {
            visited[i] = true;
            combination(arr,visited, i+1, depth+1, r);
            visited[i] = false;
        }
    }

}
	
```

원래 주어진 배열과 같은 크기의 boolean **visited** 배열 생성  
> visited 가 true 인 값만 출력할 것임.


*'depth'* 와 **'r'** 선언.


depth 는 0부터 시작해서 r 까지 점점 커짐.  
한개씩 원소를 순회하면서 방문하지 않은 원소라면,방문한 것으로 바꾸고 그 후,  

    1. visited 업데이트
    2. depth + 1
    3. 업데이트 된 변수들을 가지고 다시 재귀 호출
   
만약 depth가 r 까지 왔다면,

    1. 최종 visited 배열을 보고 원래 배열에서 값 호출.