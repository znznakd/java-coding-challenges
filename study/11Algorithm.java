문제 설명
전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

구조대 : 119
박준영 : 97 674 223
지영석 : 11 9552 4421
전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

 

 

제한사항
phone_book의 길이는 1 이상 1,000,000 이하입니다.
각 전화번호의 길이는 1 이상 20 이하입니다.
같은 전화번호가 중복해서 들어있지 않습니다.
 

 

입출력 예시
phone_book	return
["119", "97674223", "1195524421"]	false
["123","456","789"]	true
["12","123","1235","567","88"]	false
 

 

풀이 코드
Map 과 이중 for 문을 활용해 풀이했다. phone_book 의 크기가 1,000,000 이하에 안쪽 for 문은 전화번호의 최대 길이 20 이니까 이중 for 문을 활용해도 충분히 풀 수 있을 것이라고 생각했다.

Map 에 Key, Value 값을 넣어줘야하는데, 이 문제에서 Value 값은 별로 중요하지 않다. 아무렇게나 넣어도 상관없을 듯.

먼저 전화번호 목록만큼 반복하도록 외부 for 문을 설정하고 접두어를 구해내기 위해 substring() 메소드를 사용한다.

substring() 을 활용해 잘라낸 prefix 가 Map 에 넣어둔 Key 값에 있다면, 지금 i 번째 전화번호는 다른 어떤 번호의 접두어이므로 false 를 return 하면 끝 !

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<phone_book.length; i++) {
            map.put(phone_book[i], i);
        }
        
        for(String phoneNum : phone_book) {
            for(int j=0; j<phoneNum.length(); j++) {
                String prefix = phoneNum.substring(0, j);
                
                if(map.containsKey(prefix)) {
                    return false;
                }
            }
        }
        
        return answer;
    }
}

