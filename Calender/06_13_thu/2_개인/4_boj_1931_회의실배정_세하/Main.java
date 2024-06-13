import java.util.*;

class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting other) {
        // 끝나는 시간이 빠른 순서로 정렬
        if (this.end == other.end) {
            return this.start - other.start; // 끝나는 시간이 같다면 시작 시간이 빠른 순서로 정렬
        }
        return this.end - other.end;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            meetings.add(new Meeting(start, end));
        }

        // 정렬
        Collections.sort(meetings);

        int count = 0;
        int endTime = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= endTime) { // 이전 회의의 끝나는 시간 이후에 시작하는 회의인 경우
                endTime = meeting.end; // 해당 회의의 끝나는 시간으로 endTime 갱신
                count++;
            }
        }

        System.out.println(count);
    }
}