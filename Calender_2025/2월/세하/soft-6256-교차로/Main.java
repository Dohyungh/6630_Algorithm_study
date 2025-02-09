import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] times = new int[N];
        String[] positions = new String[N];
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
            positions[i] = sc.next();
        }
        
        Map<String, Queue<Integer>> queues = new HashMap<>();
        queues.put("A", new LinkedList<>());
        queues.put("B", new LinkedList<>());
        queues.put("C", new LinkedList<>());
        queues.put("D", new LinkedList<>());

        Arrays.fill(result, -1);
        
        int currentTime = 0;
        int processedCars = 0;
        int nextCarIndex = 0;

        while (processedCars < N) {
            while (nextCarIndex < N && times[nextCarIndex] == currentTime) {
                queues.get(positions[nextCarIndex]).add(nextCarIndex);
                nextCarIndex++;
            }

            boolean[] processed = new boolean[4];
            String[] posOrder = {"A", "B", "C", "D"};
            
            for (int i = 0; i < 4; i++) {
                String pos = posOrder[i];
                String rightPos = getRightPosition(pos);
                
                if (!queues.get(pos).isEmpty() && queues.get(rightPos).isEmpty()) {
                    processed[i] = true;
                }
            }
            
            for (int i = 0; i < 4; i++) {
                if (processed[i] && !queues.get(posOrder[i]).isEmpty()) {
                    int carIndex = queues.get(posOrder[i]).poll();
                    result[carIndex] = currentTime;
                    processedCars++;
                }
            }
            
            if (isDeadlock(queues) && nextCarIndex == N) {
                break;
            }
            
            currentTime++;
        }
        
        for (int i = 0; i < N; i++) {
            System.out.print(result[i] + (i == N-1 ? "\n" : " "));
        }
    }

    private static String getRightPosition(String pos) {
        switch (pos) {
            case "A": return "D";
            case "B": return "A";
            case "C": return "B";
            case "D": return "C";
            default: return "";
        }
    }

    private static boolean isDeadlock(Map<String, Queue<Integer>> queues) {
        return !queues.get("A").isEmpty() && 
               !queues.get("B").isEmpty() && 
               !queues.get("C").isEmpty() && 
               !queues.get("D").isEmpty();
    }
}