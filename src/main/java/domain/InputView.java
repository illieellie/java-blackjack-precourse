package domain;

import java.util.*;

public class InputView {
    private InputView(){}
    // 게임 참가자 입력
    static List<String> getGamers(){
        List<String> gamers = new ArrayList<>();
        // 입력
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
        // 쉼표 분리, gamers에 addAll
        gamers.addAll(List.of(s.split(",")));
        return gamers;
    }
    // 카드를 받을지 y or n
    static String getCard(){
        // 입력
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();

        return s;
    }

}
