package domain;

import java.util.*;

public class InputView {
    private InputView(){}
    // 게임 참가자 입력
    public static List<String> getGamers(){
        List<String> gamers = new ArrayList<>();
        // 입력
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        //input.close();
        // 쉼표 분리, gamers에 addAll
        Collections.addAll(gamers, s.split(","));
        return gamers;
    }
    // 카드를 받을지 y or n
    public static String getCard(){
        // 입력
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        //input.close();

        return s;
    }

}
