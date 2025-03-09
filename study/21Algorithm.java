1) 쇠막대기

문제를 처음 봤을 땐 길기도 하고 복잡하게 보여서 꽤나 긴장했었는데, 차근차근 그림을 그려가며 보니 Stack 을 사용한다면 꽤나 간단히

풀 수 있는 문제였다. 열린 괄호를 계속해서 넣어주다가 닫힌 괄호가 등장했을 때 레이저가 만들어지는지 막대기가 나오는지 구별하면 된다.

처음 시도에서는 stack.peek() 을 활용해 풀이했는데 틀려서 디버깅 후 겨우 원인을 찾을 수 있었는데 ,,

 

1-1) peek() 을 활용한 첫번째 풀이 (오답)

닫힌 괄호가 등장했을 때, 레이저인지 쇠막대기인지 구별해야 한다고 했는데, 등장한 닫힌 괄호 앞이 열린 괄호라면 레이저, 닫힌 괄호라면

쇠막대기가 된다. 이렇게 풀이하면 오답인 이유가 Stack 에 들어있는 값은 무조건 열린 괄호이고, 따라서 맨 아래의 else 구문을 절대로

타지 않게 된다. 즉, 쇠 막대기인 경우를 구해낼 수가 없다 ,, ㅎㅎ

private static int solution(String input) {
    int count = 0;
    Stack<Character> stack = new Stack<>();

    for (char x : input.toCharArray()) {
        // 열린 괄호일 때,
        if (x == '(') {
            stack.push(x);

        // 닫힌 괄호일 때, peek() 이 열린 괄호인지 아닌지 판단.
        } else {
            if (stack.peek() == '(') {
                // 닫힌 괄호 바로 앞이 열린 괄호이므로 레이저.
                // Stack 에 들어있는 열린 괄호 갯수만큼 쇠 막대기가 존재하기 때문에 레이저를 만나면 해당 열린 괄호 갯수만큼 더해야 한다.
                // 즉, 쇠 막대기가 일자로 곂쳐져있고 레이저로 한 번 쭉 잘렸으니까 그 만큼 조각을 더해주면 된다는 뜻.
                // 더하기 전에 레이저의 열린 괄호는 Stack 에서 지워주자.
                stack.pop();
                count += stack.size();

            } else {
                // 닫힌 괄호 바로 앞이 열린 괄호가 아니므로 레이저가 이닌 쇠 막대기.
                stack.pop();
                count ++;
            }
        }
    }

    return count;
}
 

 

 

1-2) charAt() 을 활용한 두번째 풀이 (정답)

한참 고민하다 디버깅을 하고 오답인 이유를 알게되었다. 

Stack 내부의 값은 무조건 열린 괄호이므로 닫힌 괄호를 판단하기 위해선 String input 을 탐색하면 된다고 생각했다 !

private static int solution(String input) {
    int count = 0;
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) == '(') {
            stack.push(input.charAt(i));
        } else {
            // 레이저라면 ?
            if (input.charAt(i - 1) == '(') {
                stack.pop();
                count += stack.size();
            // 쇠 막대기라면 ?
            } else {
                stack.pop();
                count ++;
            }
        }
    }

    return count;
}

