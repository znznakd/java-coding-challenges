사진들을 보며 추억에 젖어 있던 루는 사진별로 추억 점수를 매길려고 합니다. 사진 속에 나오는 인물의 그리움 점수를 모두 합산한 값이 해당 사진의 추억 점수가 됩니다. 예를 들어 사진 속 인물의 이름이 ["may", "kein", "kain"]이고 각 인물의 그리움 점수가 [5점, 10점, 1점]일 때 해당 사진의 추억 점수는 16(5 + 10 + 1)점이 됩니다. 다른 사진 속 인물의 이름이 ["kali", "mari", "don", "tony"]이고 ["kali", "mari", "don"]의 그리움 점수가 각각 [11점, 1점, 55점]]이고, "tony"는 그리움 점수가 없을 때, 이 사진의 추억 점수는 3명의 그리움 점수를 합한 67(11 + 1 + 55)점입니다.

그리워하는 사람의 이름을 담은 문자열 배열 name, 각 사람별 그리움 점수를 담은 정수 배열 yearning, 각 사진에 찍힌 인물의 이름을 담은 이차원 문자열 배열 photo가 매개변수로 주어질 때, 사진들의 추억 점수를 photo에 주어진 순서대로 배열에 담아 return하는 solution 함수를 완성해주세요.


import java.util.ArrayList;
import java.util.Collections;

public class Practice {
    public static void main(String[] args) {
        String[] name = {"kali", "mari", "don"};
        int[] yearning = {11, 1, 55};
        String[][] photo = {{"kali", "mari", "don"}, {"pony", "tom", "teddy"}, {"con", "mona", "don"}};

        //결과를 넣을 리스트 만들기
        ArrayList<Integer> result = new ArrayList<>();

        //각 사진들에 있는 사람이름의 리스트 만들기
        for (String[] eachphotogroup : photo) {
            ArrayList<String> namelist = new ArrayList<>();
            for (int i = 0; i < eachphotogroup.length; i++) {
                namelist.add(eachphotogroup[i]);

            }
            // 각 사진별 특정 인물의 숫자 세고 각자의 총 포인트에 더하기
            int point = 0; // 총 포인트
            for (int i = 0; i < name.length; i++) {
                // 특정 인물의 수
                int count = Collections.frequency(namelist, name[i]);
                if (count != -1) {
                    point += count * yearning[i]; // 총 포인트에 더하기
                }

            }
            // 모든 포인트를 결과 집합에 정의
            result.add(point);
        }
        //result를 int[] 배열로 바꾸기
        int[] result2 = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result2[i] = result.get(i);
        }

        System.out.println(result2);
    }
}
