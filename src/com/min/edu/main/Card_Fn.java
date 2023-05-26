package com.min.edu.main;

import java.util.ArrayList;
import java.util.List;

public class Card_Fn {	
	
	
	/**
	 * 덱에서 카드를 가져와서 추가해주는 메소드
	 */
	public void divideCards(List<String> deck, List<String> settingCard, int x){
		for (int i = 0; i < x; i++) {
			settingCard.add(deck.get(0));
			deck.remove(0);
		}
	}
	
	/**
	 * 필드 카드를 출력하는 메소드
	 */
	public void printField(List<String> filed) {
		System.out.printf("현재 필드 값 : [ %s ]\n",filed.get(1));
	}
	
	/**
	 * 플레이어의 보유카드를 판단하고 승/패/원카드를 수행할지 결정하는 메소드
	 */
	public String handsCheck(int cnt) {
		String str = "";
		if(cnt == 0) {
			str = "승리";
		}else if(cnt == 1){
			str = "원카드";
		}else if(cnt >15){
			str = "패배";
		}
		return str;
	}
}
