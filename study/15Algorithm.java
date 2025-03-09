문제 설명
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

 


 

Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

 

 

제한사항
갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
 

 

입출력 예시
brown	yellow	return
10	2	[4, 3]
8	1	[3, 3]
24	24	[8, 6]
 

 

풀이 코드
완전탐색을 할 때 어떻게 해야 쓸데없는 연산을 줄일 수 있을까 고민을 많이 한 문제이다.

brown, yellow 를 합한 값의 약수를 구해야 할 것 같은데 .. 제한사항 등을 읽어보며 곰곰히 생각한 결과,

1. 가로가 세로보다 길거나 같을 때만 약수 객체를 생성하도록 i >= (sum / i) 조건문을 추가했다.

2. brown 은 최소 8 이상, yellow 는 최소 1 이상 입력되므로 가로와 세로 길이는 최소 3 이상일 때만 약수 객체를 생성하도록 했다.

 

List<약수> 형에 위의 조건문을 통과한 약수들을 집어넣고, 만약 하나만 들어있다면 바로 정답이므로 answer 에 약수 객체에 저장된

가로, 세로 길이를 지정해준 뒤 반환했다.

 

만약 2개 이상의 약수 객체가 저장되었다면, 어떤 가로, 세로 길이를 가져야 brown, yellow 갯수를 맞출 수 있나 확인해야하는데,

내 로직같은 경우 먼저 yellow 갯수를 구해준다. yellow 는 brown 과 다르게 타일이 모두 붙어있는 경우라 가로, 세로 곱셈으로

모든 갯수를 구해낼 수 있고 가로, 세로 길이는 구해낸 약수의 길이 즉, 전체 가로 길이 -2, 전체 세로 길이 - 2 이기 때문에 yellow 의

갯수를 구해낼 수 있다.

 

이렇게 구해낸 yellow 갯수를 전체 타일 갯수에서 빼주면 brown 갯수가 나오고 입력 값과 동일한지 확인한 뒤 아니면 다른 약수 객체로

다시 비교, 맞다면 answer 에 값을 넣어주고 반환하면 된다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 약수 {
    int a;
    int b;

    public 약수(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        List<약수> list = new ArrayList<>();
        int sum = brown + yellow;

        // sum 의 약수를 모두 구한다.
        // 약수 객체 생성은 나누는 값 (가로) 가 몫 (세로) 보다 클 떄만 생성하도록 한다.
        // 가로와 세로 길이는 최소 3 이상일 떄만 약수 객체를 생성하도록 한다.
        // i 는 가로 길이, (sum / i) 는 세로 길이이다.
        for (int i = 1; i < sum; i++) {
            if (sum % i == 0 && i >= (sum / i) && i >= 3 && (sum / i) >= 3) {
                list.add(new 약수(i, sum / i));
            }
        }

        // 약수 객체가 하나만 생성되었다면 바로 정답처리
        if (list.size() == 1) {
            answer[0] = list.get(0).a;
            answer[1] = list.get(0).b;

        } else {
            for (int i = 0; i < list.size(); i++) {
                int insideCount = (list.get(i).a - 2) * (list.get(i).b - 2);
                int outsideCount = sum - insideCount;

                if (insideCount == yellow && outsideCount == brown) {
                    answer[0] = list.get(i).a;
                    answer[1] = list.get(i).b;
                    return answer;
                }
            }
        }

        return answer;
    }
}
