class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("C"))
                sk.pop();

            else if (operations[i].equals("D"))
                sk.push(sk.peek() * 2);

            else if (operations[i].equals("+")) {
                int size = sk.size();
                sk.push(sk.get(size - 1) + sk.get(size - 2));
            } else
                sk.push(Integer.parseInt(operations[i]));
        }

        int sum = 0;
        while (!sk.empty())
            sum += sk.pop();
        return sum;
    }
}