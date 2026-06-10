import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {
        //ATIV1
        List<Integer> nums = Arrays.asList(9, 30, 4, 57, 0, 65, 9, 8, 3, 7);
        List<Integer> numsPares = 
        nums.stream()
            .filter(n -> n % 2 ==0)
            .collect(Collectors.toList());
        
        System.out.println("ATV1: " + numsPares);
        System.out.println("\n");
        

    }
}
