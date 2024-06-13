public class BinarySearch {

    static int[] arr1 = {1, 3, 4, 5, 6, 8, 9};
    //                   0  1  2  3  4  5  6

    static int[] arr2 = {    0, 1, 1, 1,    3, 3,  4,  5, 5,  6,     8,  8,   9,  9,  9,  9,  9,  9,  10, 10};
    //                       0  1  2  3     4  5   6   7  8   9     10  11   12  13  14  15  16  17   18  19

    // 타겟이 배열의 원소에 대해 해당 사항이 없으면 배열 밖 인덱스를 리턴하게 하면 다양하게 응용 가능
    // 경계조건이 까다로워서 잘 따져봐야 함
    //     - 파라미터 범위
    //     - 부등호 모양
    //     - 반복 조건, 종료 조건
    //
    // 아래 메서드 예외 있을 수도 있으니까 알아서 공부하삼
    public static void main(String[] args) {

        int start = -2;
        int end = 12;
        int[] ans1 = new int[end -start + 1];
        int[] ans2 = new int[end -start + 1];
        int idx;

        System.out.println("중복 값이 없는 배열에서 이분 탐색(반복문 버전)");
        System.out.print("타깃     : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", i);
        }
        System.out.println();

        System.out.print("타겟 찾기 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearch(arr1, 0, arr1.length-1, i));
        }
        System.out.println();

        System.out.print("타켓 이상 : ");
        idx = 0;
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchLowerBound(arr1, 0, arr1.length, i));
            ans1[idx++] -= binarySearchLowerBound(arr1, 0, arr1.length, i);
        }
        System.out.println();

        System.out.print("타겟 초과 : ");
        idx = 0;
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchUpperBound(arr1, 0, arr1.length, i));
            ans1[idx++] += binarySearchUpperBound(arr1, 0, arr1.length, i);
        }
        System.out.println();

        System.out.print("타겟 이하 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchUpperBound(arr1, 0, arr1.length, i) - 1);
        }
        System.out.println();

        System.out.print("타켓 미만 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchLowerBound(arr1, 0, arr1.length, i) - 1);
        }
        System.out.println();

        System.out.print("원소 갯수 : ");
        for(int i=0 ; i<ans1.length ; i++){
            System.out.printf("%4d", ans1[i]);
        }
        System.out.println();



        System.out.println();



        System.out.println("중복 값이 있는 배열에서 이분 탐색(반복문 버전)");
        System.out.print("타깃     : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", i);
        }
        System.out.println();

        System.out.print("타켓 찾기 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearch(arr2, 0, arr2.length-1, i));
        }
        System.out.println();

        System.out.print("타켓 이상 : ");
        idx = 0;
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchLowerBound(arr2, 0, arr2.length, i));
            ans2[idx++] -= binarySearchLowerBound(arr2, 0, arr2.length, i);
        }
        System.out.println();

        System.out.print("타겟 초과 : ");
        idx = 0;
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchUpperBound(arr2, 0, arr2.length, i));
            ans2[idx++] += binarySearchUpperBound(arr2, 0, arr2.length, i);
        }
        System.out.println();

        System.out.print("타겟 이하 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchUpperBound(arr2, 0, arr2.length, i) - 1);
        }
        System.out.println();

        System.out.print("타켓 미만 : ");
        for(int i=start ; i<=end ; i++){
            System.out.printf("%4d", binarySearchLowerBound(arr2, 0, arr2.length, i) - 1);
        }
        System.out.println();

        System.out.print("원소 갯수 : ");
        for(int i=0 ; i<ans2.length ; i++){
            System.out.printf("%4d", ans2[i]);
        }
        System.out.println();
    }

    private static int binarySearch(int[] arr, int left, int right, int target){
        while(left <= right){
            int mid = (left + right) / 2;

            if(arr[mid] < target){
                left = mid + 1;
            }
            else if(arr[mid] > target){
                right = mid - 1;
            }
            else{
                return mid;
            }
        }

        return -1;
    }

    private static int binarySearchLowerBound(int[] arr, int left, int right, int target){
        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return right;
    }

    private static int binarySearchUpperBound(int[] arr, int left, int right, int target){
        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] <= target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return right;
    }

}
