package 函数类的使用;
public class Run {
    public int add(int a, int b) {
        return a + b;
    }
} 


class Test {
    
    public static void main(String[] args) {
        Run run = new Run();
        int result = run.add(1, 2);
        System.out.println(result);
    }
}
