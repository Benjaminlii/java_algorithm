package main.java.com.benjamin.offer;

/**
 * @ClassName:Offer1
 * @Description:
 *               在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 *               数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *               例如，如果输入长度为7的数组[2,3,1,0,2,5,3]，那么对应的输出是2或者3。存在不合法的输入的话输出-1
 * @author:Benjamin
 * @date: 2022.11.07 20:22
 */
public class Offer1 {
    /**
     * @param numbers int整型一维数组
     * @return int整型
     */
    public int duplicate(int[] numbers) {
        // write code here
        int[] mark = new int[numbers];

        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(new Offer1().duplicate(new int[]{ 2, 3, 1, 0, 2, 5, 3}));
    }
}
