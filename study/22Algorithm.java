1) 올바른 괄호

Stack 자료구조를 활용하면 빠르게 풀어낼 수 있다.

예전에는 Input 받은 문자열을 split() 메서드를 통해 배열로 만들었는데, toCharArray() 메서드를 활용하면 효율성 테스트 또한 통과할

수 있다고는 하는데 .. 그런 제약 없으면 아무 방법이나 사용해도 무방할 듯 하다.

 

answer 를 YES 값으로 셋팅하고, 분기문을 따라 내려가면서 NO 가 되는 상황이 한번도 없다면 YES 값을 지닌 answer 를 그대로

출력하는 방식으로 로직을 구성했다. Input 의 맨 처음 괄호가 닫힌 괄호일 수도 있을 것 같아서 이 경우에는 바로 NO 를 리턴하도록 했다. 

 

for 반복문을 돌리면서 열린괄호를 Stack 에 쭉 넣어주다가 x 가 닫힌괄호라면 괄호의 한 쌍() 이 성립되므로 pop() 을 통해 Stack 에서

열린괄호를 지운다. 이걸 모든 문자열의 길이만큼 반복하다 Stack 의 size() 를 확인한다.

 

Stack size() 가 0이라면 모든 괄호가 제대로 쌍을 이뤄 없어진 경우기 때문에 그대로 YES 가 리턴될 것이고, size() 가 0이 아니라면

쌍을 찾지 못한 괄호가 남아있다는 것이므로 NO 가 리턴된다 !

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String answer = solution(line);
        System.out.println(answer);
    }

    private static String solution(String line) {
        String answer = "YES";
        Stack<Character> stack = new Stack<>();

        // 맨 처음 괄호가 열린 괄호가 아니라면 바로 return "NO"
        char[] lineToCharArray = line.toCharArray();
        if (lineToCharArray[0] == ')') {
            return answer = "NO";
        }

        for (Character x : line.toCharArray()) {
            if (x == '(') {
                stack.push(x);

            // 닫힌 괄호를 만나면, stack 의 맨 위 열린 괄호를 pop() 해준다.
            // pop() 하기 전에, stack 길이를 확인해야 한다.
            // 모든 탐색이 끝나고 stack 길이를 확인해 size == 0 이라면 YES, 아니라면 NO 를 반환한다.
            } else {
                if (stack.size() != 0) {
                    stack.pop();
                } else {
                    return "NO";
                }
            }
        }

        // 올바른 조건이 아닌 괄호 분기문을 모두 통과하면 해당 괄호는 올바른 괄호이므로, 그대로 YES 반환
        return answer;
    }
}
 

 

2) 괄호문자 제거

올바른 괄호 문제를 풀었다면 역시 Stack 자료구조를 사용해 비슷하게 풀어낼 수 있다.

문자를 '+' 연산자로 더하는 것 보다 StringBuffer 를 사용해서 문자를 쭉 더해 반환하도록 했다.

 

이것도 toCharArray() 를 사용해 닫힌괄호가 나올 때 까지 쭉 Stack 에 문자를 넣어준다.

그리고 닫힌괄호를 만나면 괄호 한 쌍을 만들기 위해 Stack 에 저장된 값들을 pop() 해가면서 열린괄호까지 찾아내야 한다.

여기서 열린괄호를 찾아내 해당 열린괄호 또한 Stack 에서 없애버려야 하는데 어떻게 할 수 있을까 ? 라는 고민을 잠깐 했었는데,

pop() 을 사용하면 Stack 에서 값을 지우고 지운 값을 반환 값으로 받을 수 있다는 사실을 깜빡해서 조금 헤맸었다 😅

 

따라서 열린괄호가 아닐 때 까지 반복문을 돌리며 열린괄호를 만나면 반복을 멈추도록 코드를 짰다.

그리고 Stack 에 남은 문자를 append() 로 모두 합친 뒤 반환하면 끝 !

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputLine = br.readLine();
        String answer = solution(inputLine);

        System.out.println(answer);
    }

    private static String solution(String inputLine) {
        StringBuffer answer = new StringBuffer();
        Stack<Character> stack = new Stack<>();

        for (char x : inputLine.toCharArray()) {
            // 닫힌 괄호를 제외한 값 모든 stack 에 삽입
            if (x != ')') {
                stack.push(x);

            // 닫힌 괄호라면 열린괄호까지 전부 pop()
            } else {
                while (stack.pop() != '(');
            }
        }

        for (int i = 0; i < stack.size(); i++) {
            answer.append(stack.get(i));
        }

        return answer.toString();
    }
}
