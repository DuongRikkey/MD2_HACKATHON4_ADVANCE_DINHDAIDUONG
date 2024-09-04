package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class Exam2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số ISBN (10 chữ số)");
        String numberIsbn = scanner.nextLine();

        // Kiểm tra xem số ISBN có đủ 10 chữ số hay không
        if (numberIsbn.length() != 10) {
            System.out.println("Số ISBN phải có chính xác 10 chữ số");
            return;
        }

        Stack<Integer> digits = new Stack<>();
        Stack<Integer> multipliers = new Stack<>();
        int sum = 0;

        try {
            // Đẩy 10 chữ số và các hệ số tương ứng vào các stack
            for (int i = 0; i < 10; i++) {
                int digit = Character.getNumericValue(numberIsbn.charAt(i));
                digits.push(digit);
                multipliers.push(10 - i); // Hệ số giảm dần từ 10 xuống 1
            }

            // Tính tổng bằng cách pop từ cả hai stack
            while (!digits.isEmpty() && !multipliers.isEmpty()) {
                int digit = digits.pop();
                int multiplier = multipliers.pop();
                sum += digit * multiplier;
            }

            // Kiểm tra xem tổng có chia hết cho 11 không
            if (sum % 11 == 0) {
                System.out.println("Số ISBN hợp lệ");
            } else {
                System.out.println("Số ISBN không hợp lệ");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chuỗi nhập vào không phải là số");
        }
    }

}
