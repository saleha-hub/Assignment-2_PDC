package Q2;

import java.util.Scanner;
public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter  string: ");
        String packet = input.nextLine();
        TransactionStack stack = new TransactionStack(packet.length());
        RequestQueue queue = new RequestQueue(packet.length());
        for (int i = 0; i < packet.length(); i++) {
            char ch = packet.charAt(i);
            stack.push(ch);
            queue.enqueue(ch);
        }
        boolean palindrome = true;
        while (!stack.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                palindrome = false;
                break;
            }
        }
        if (palindrome) {
            System.out.println("Packet is symmetrical (Palindrome).");
        } else {
            System.out.println("Packet is not symmetrical.");
        }
        input.close();
    }
}


