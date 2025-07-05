package topic.dsa.array_string;

public class BasicMath{



    public void multiplicationTable(int num) {
        int i = 1;
        System.out.println("The table for num: " + num + " is");
        while (i <= 10) {
            System.out.println(num + " * " + i + " = " + num * i);
            i++;
        }
    }

    public void sumOfAllOddNumber(int limit) {
        int i = 1;
        int sum = 0;

        while (i <= limit) {
            if ((i & 1) == 1) {
                sum += i;
            }
            i++;
        }
        System.out.println("The sum of all odd number till " + limit + " is: " + sum);
    }

    public void factorial(int num){
        if(num == 0 || num == 1){
            System.out.println("The factorial of "+num+ "is: 1");
            return;
        }
        int i = 1;
        int ans = 1;
        while( i <= num){
            ans *=  i;
            i++;
        }
        System.out.println("The factorial of number: "+num+" is: "+ans);
    }

    public void sumOfTheDigit(int num){
        int ans = 0;
        while (num > 0){
            int rem = num % 10;
            ans += rem;
            num /= 10;
        }
        System.out.println("The sum of digit for: "+num+ " is: "+ans);
    }

    public void lcm(int num1, int num2){

    }



}