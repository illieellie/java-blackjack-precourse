package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class OutputView {

    private OutputView(){}

    static void printMent1(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    static void printMent2(List<String> gamers){
        System.out.print("딜러와 ");

        printList(gamers);
        System.out.println("에게 2장의 카드를 나누었습니다.");
    }

    static void printMent3(String name){
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    static void printMent4(String name){
        System.out.println(name + "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }


    static void printMent5(String name, List<String>cards){
        System.out.print(name + "카드:");
        printList(cards);
        System.out.println();
    }

    static void printMent6(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    static void printResultDetail(String name, List<String> cards, int result){
        System.out.print(name + "카드:");
        printList(cards);
        System.out.println(" - 결과: " + result);
    }

    static void printResultSummary(String name, int win, int lose){
        // gamer를 구현하고 나서 마지막에 구현하기로 한다.
    }

    static void printList(List<String> obj){
        int size = obj.size()-1;
        for(int i = 0; i<=size; i++){
            System.out.print(obj.get(i));
            if(i!=size) {System.out.print(", ");}
        }
    }
}
