
class Solution {

    public int[] solution(int brown, int yellow) {
        // 노란색으로 칠해진 칸의 가로 길이 + 세로 길이 
        int sum = (brown - 4) / 2;

        int width = sum - 1;
        int length = sum - width;

        while (width >= length) {
            if (width * length == yellow) {
                break;
            }

            width--;
            length++;
        }

        int[] answer = {width + 2, length + 2};
        return answer;
    }
}
