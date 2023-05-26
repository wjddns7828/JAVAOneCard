package com.min.edu.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 컴퓨터가 카드를 판단하고 제출하는 클래스
 */
public class AI {
	public List<String> player1 = new ArrayList<String>();
	
	//값 넣어주기(공통)
	public List<String> getCard(String[] x) {
		for (int i = 0; i < x.length; i++) {
			player1.add(x[i]);
		}		
		Collections.sort(player1);
		return player1;
	}

	//필드에 두번쨰 값 가져오는 메소드
	public String getField(List<String> x){		
		return x.get(1); 
	}

	/**
	 * 낼 수 있는 카드를 정리해주는 메소드 (공통)
	 */
	public List<String> listForVal (List<String> player, String x) {
		List<String> save = new ArrayList<String>();
		for (int i = 0; i < player.size(); i++) {
			for (int j = 0; j < player.get(i).length(); j++) {
				if(player.get(i).charAt(j)==x.charAt(j)) {
					save.add(player.get(i));
				}
			}
		}
		Collections.sort(save);
		return save;
	}

	/**
	 * A카드일 경우 낼 수 있는 카드 정리하는 메소드
	 */
	public List<String> listForAVal (List<String> player) {
		List<String> save = new ArrayList<String>();
		for (int i = 0; i < player.size(); i++) {
			if(player.get(i).charAt(1)=='A' || player.get(i).charAt(1)=='K') {
				save.add(player.get(i));
			}
		}
		Collections.sort(save);
		return save;
	}
	
	/**
	 * 낼 수 있는 카드 요약된 곳에서 랜덤으로 가져오는 메소드
	 */
	public String putCard(List<String> hands) {
		int rnum = (int) (Math.random()*hands.size());
		String ran = hands.get(rnum);
		return ran;
	}

	/**
	 * 카드를 냈을 때 그 카드를 빼주는 메소드
	 */
	public List<String> delCard(List<String> player, String x) {
		player.remove(x);
		return player;
	}
	
//	public void printCard(List<String> player) {
//		System.out.printf("\n 컴퓨터의 손패 :");
//		for (String s : player) {
//			System.out.printf("[ %s ]",s);
//		}
//		System.out.println();
//	}

}
