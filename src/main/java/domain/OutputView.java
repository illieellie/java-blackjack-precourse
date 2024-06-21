package domain;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Symbol.*;
import domain.card.Type;
import domain.card.Type.*;
import domain.user.Gamer;

import java.util.List;

import static domain.card.Type.*;


public class OutputView {

    private OutputView(){}

    public static void printMent1(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    public static void printMent2(List<String> gamers){
        System.out.print("딜러와 ");

        printList(gamers);
        System.out.println("에게 2장의 카드를 나누었습니다.");
    }


    public static void printMent3(String name){
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printMent4(String name){
        System.out.println(name + "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }


    public static void printMent5(String name, List<Card>cards){
        System.out.print(name + "카드: ");
        printCard(cards);
        System.out.println();
    }

    public static void printMent6(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultDetail(String name, List<Card> cards, int result){
        System.out.print(name + "카드: ");
        printCard(cards);
        System.out.println(" - 결과: " + result);
    }

    public static void printResultSummary(List<Gamer> gamers, int []result){
        System.out.println("## 최종 승패");
        for(int i = 0; i<gamers.size(); i++){
            String name = gamers.get(i).getName();
            System.out.print(name + ": ");
            if(i==0) {
                // 딜러
                System.out.print(result[i]+"승 ");
                System.out.println(gamers.size()-1-result[i]+"패");
            } else if (i!=0) {
                // 플레이어
                System.out.println(resultDiv(result[i]));

            }
        }
    }

    private static String resultDiv(int d){
        if(d==0){
            return "패";
        }
        return "승";
    }

    private static void printCard(List<Card> obj){
        int size = obj.size()-1;
        for(int i = 0; i<=size; i++){
            Symbol s = obj.get(i).getSymbol(); // 값 // A, K, Q, J 변환
            Type t = obj.get(i).getType(); // 한국말로 변환
            if(containSpecial(s)){
                System.out.print(s.name().substring(0,1)); // 앞자리 자른다음 출력
            } else if (!containSpecial(s)) {
                System.out.print(s.getScore());
            }
            System.out.print(convertLanguage(t));
            if(i!=size) {System.out.print(", ");}
        }
    }

    private static String convertLanguage(Type t){
        String s = "스페이드";
        if(t.equals(DIAMOND)) {
            s = "다이아몬드";
        }else if(t.equals(HEART)){
            s = "하트";
        }else if(t.equals(CLUB)){
            s = "클로버";
        }
        return s;
    }
    private static boolean containSpecial(Symbol s){
        if(s.equals(Symbol.ACE)||s.equals(Symbol.JACK)||s.equals(Symbol.KING)||s.equals(Symbol.QUEEN)){
            return true;
        }
        return false;
    }

    private static void printList(List<String> obj) {
        int size = obj.size()-1;
        for(int i = 0; i<=size; i++){
            System.out.print(obj.get(i));
            if(i!=size) {System.out.print(", ");}
        }
    }

}
