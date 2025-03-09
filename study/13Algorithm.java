문제 설명
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

 

 

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
 

입출력 예시

participant	completionreturn	result
["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
 

 

풀이 코드
참가하는 이름을 Key, 동명이인을 고려한 사람 수를 Value 로 하고, 완주하는 사람을 확인하며 Value 값을 하나 씩 빼주면서 0 이 아닌 Value 에 해당하는 Key 를 찾아내면 되는 문제이다.

동명이인이 있을 수도 있기에 HashMap Colletions 에서 제공하는 getOrDefault() 메서드를 사용할 수 있다면 간단하게 풀 수 있는데,

Map 에 참가인원만큼 Key, Value 값을 넣을 때, 동명이인이 있더라도 Value 값만 증가하도록 해주기 때문이다.

 

Map 이 셋팅되었다면 완주인원을 Key 값에 대입해보면서 완주했다면 Value 값에서 -1 만큼을 빼준다.

마지막으로 Map 의 key 를 하나씩 확인하면서 해당 Key 와 연결되어 있는 Value 가 0이 아닌 Key 를 찾아내 return 하면 끝 !

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> pMap = new HashMap<>();
        
        for(String p : participant) {
            pMap.put(p, pMap.getOrDefault(p, 0) + 1);
        }

        for(String c : completion) {
            if(pMap.containsKey(c)) {
                pMap.put(c, pMap.getOrDefault(c, 0) - 1);
            }
        }

        for(String key : pMap.keySet()) {
            if(pMap.get(key) != 0) {
                answer = key;
            }
        }
    
        return answer;
    }
}

