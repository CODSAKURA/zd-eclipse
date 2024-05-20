import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()) {
        	
        	// 读取前两个整数（4和5）
	        int a = in.nextInt();
	        int b = in.nextInt();
	
	        // 消耗掉行尾的换行符，将扫描器移动到下一行
	        in.nextLine();	
	        // 读取下一行作为字符串
	        String line = in.nextLine();
	
	        // 将String变成String数组，并将String数组转换成Integer数组
	        String[] numbersString = line.split(" ");        
	        int[] numbersInt = new int[numbersString.length];
	        for(int i = 0; i < numbersInt.length; i++) {
	        	numbersInt[i] = Integer.parseInt(numbersString[i]);
	        }
	        
	        // 打印a和b的值以及Integer数组的长
	        System.out.println(a);
	        System.out.println(b);
	        System.out.println(numbersInt.length);
        }
    }
}
