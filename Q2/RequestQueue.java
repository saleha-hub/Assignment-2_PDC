package Q2;
public class RequestQueue {
    private char[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    public RequestQueue(int capacity) {
        this.capacity = capacity;
        queue = new char[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }
    public void enqueue(char value) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full"); }
        queue[rear] = value;
        rear = (rear + 1) % capacity;
        size++; }
    public char dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty"); }
        char value = queue[front];
        front = (front + 1) % capacity;
        size--;
        return value;}
    public boolean isEmpty() {
        return size == 0; }
}

