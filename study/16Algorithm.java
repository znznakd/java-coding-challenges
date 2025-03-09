문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

 

 

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
 

 

입출력 예시
geners	plays	return
["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
 

 

풀이 코드
Level 3 에 걸맞게 푸는데 굉장히 오래걸린 문제 ㅠ HashMap, 정렬을 사용해서 풀이했다.

먼저 getOrDefault() 를 사용해 key 로 장르, Value 로 각 장르의 재생 횟수를 모두 더한 값을 넣는다.

장르 내 가장 많이 재생된 순서로 수록하기 위해 keySet() 에서 추출한 Key 를 가진 List 를 만들어준다.

그리고 해당 List 를 Value 에 따른 내림차순 정렬을 해주는데, Value 값을 추출하기 위해 map.get() 메서드를 사용해 Comparator 을

내림차순으로 정렬해줬다. 여기까지 진행했다면 Map 내부 값은 Value 값에 따라 내림차순 정렬이 되어있을 것이다.

 

이제 장르 내 가장 많이 재생된 순서를 찾아 담아보자.

return 이 정수형 배열이기 때문에 정수형 List 를 선언하고 아까 정렬한 Key List 를 통해 반복문을 정의한다.

그럼 classic, pop 과 같은 장르가 Key 가 될 것이고, 바로 아래에 장르 배열을 탐색하는 for 문을 하나 더 정의한다.

이 for 문은 장르 내 가장 많이 재생된 장르 배열 내 인덱스를 알아내기 위한 for 문이고, 최대 재생 횟수와 인덱스 (고유번호) 를 알아내기

위해 int max, first 변수를 정의해준다.

그러고 분기문으로 현재 Key 가 장르와 동일한지 ? 장르와 동일하면서 재생횟수에 해당하는 고유번호의 재생횟수가 최대 재생횟수를 

능가하는지를 판단하고, 두 조건 모두 만족하면 해당 곡이 그 장르의 최대 재생횟수를 가진 곡이기 때문에 max 과 first 를 갱신한다.

 

또한 현재 Key 와 동일한 장르를 가지고 두 번째로 많이 재생된 곡의 고유번호를 찾기 위해 다시 for 문을 정의한다.

이 for 문에는 조건문이 하나 더 있는데, 여기서 고유번호가 전에 갱신된 first 가 아닌 경우에 즉, first 를 제외한 가장 많은 재생횟수를 가진

고유번호를 찾는 조건문을 하나 더 추가해 준 것이다.

 

여기까지 진행했다면, 장르 내 가장 많이 재생된 고유번호, 두 번째로 많이 재생된 고유번호 순서로 넣어줘야 하기 때문에 미리 선언한

정수형 List 에 순서대로 추가하되 두 번째로 많이 재생된 고유번호가 없을 수도 있으므로 두 번째 고유번호가 갱신되지 않았다면 추가하지

않도록 조건문을 추가해준다.

 

그리고 List<Integer> -> int answer[] 배열에 하나 씩 추가 후 return 하면 끝 !

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> keys = new ArrayList<>(map.keySet());
        keys.sort((o1, o2) -> map.get(o2) - map.get(o1));

        // 1. 장르 내 가장 많이 재생된 순서로 수록
        List<Integer> result = new ArrayList<>();
        for (String key : keys) {
            int max = 0;
            int first = 0;

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(key) && plays[i] > max) {
                    max = plays[i];
                    first = i;
                }
            }

            int secondMax = 0;
            int second = -1;

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(key) && plays[i] > secondMax) {
                    if (i != first) {
                        secondMax = plays[i];
                        second = i;
                    }
                }
            }

            // 2-1. 장르 내 곡이 하나라면 해당 곡만 수록
            // 2-2 장르 내 재생횟수가 같다면 고유번호 순으로 수록
            result.add(first);

            if (second != -1) {
                result.add(second);
            }
        }

        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
