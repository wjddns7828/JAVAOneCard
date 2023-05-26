package com.min.edu.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Player {
	Util scan = new Util();
	
	/**
	 * 0 또는 1 값이 출력되는 메소드이며 첫 턴을 정하는 메소드
	 * @throws InterruptedException 
	 */
	public int first() throws InterruptedException {
		System.out.println("제작사 : 원정 도박단");
		System.out.println("\n<<<< 원 카 드 >>>>");
		System.out.println("\n===================== 게임을 시작합니다 =====================\n");
		System.out.println("첫번째 턴은 동전던지기로 정해집니다");
		System.out.println("1.[앞면]  2.[뒷면] 번호를 선택해주세요");
		//
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		//
		String[] coin = {"앞면","뒷면"};
		int randomNum = (int)(Math.random()*2);
		System.out.println("["+coin[a-1] +"]을 선택하셨습니다");
		
		Thread.sleep(1000);
		
		System.out.println("동전 던지기의 결과는 [" + coin[randomNum]+"]입니다.");
		
		if(randomNum == (a-1)) { // 랜덤 숫자 0이면 플레이어 1이면 컴퓨터 시작
			System.out.println("\n==========================================================================");
			System.out.println("\n첫번째 턴은 : [ 플레이어 ] 입니다\n");
		} else {
			System.out.println("\n==========================================================================");
			System.out.println("\n첫번째 턴은 : [ 컴퓨터 ] 입니다\n");
		}
		System.out.println("==========================================================================");
		System.out.println("\"[딜러] : 카드를 각 플레이어에게 분배합니다\"\n");
		Thread.sleep(2000);
		System.out.println("\"[딜러] : 게임을 시작하겠습니다. \"");
		System.out.println("==========================================================================");
		return randomNum;
	}
	
	/**
	 * 플레이어의 손패를 출력하는 메소드
	 */
	public void printCard(List<String> player) {
		System.out.printf("\n플레이어의 손패 :");
		for (String s : player) {
			System.out.printf("[ %s ]",s);
		}
		System.out.println();
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
	 * 낼 수 있는 카드 요약울 출력하는 메소드
	 */
	public void listForValPrint(List<String> save) {
		System.out.printf("\n낼 수 있는 카드 \n");
		for (int i = 0; i < save.size(); i++) {
			System.out.printf("%d. [ %s ]  ",i+1,save.get(i));
		}
	}

	/**
	 * 낼 수 있는 카드에서 카드를 입력후 낸 카드 출력하는 메소드
	 */
	public String putCard(List<String> hands) {
		while(true) {
			listForValPrint(hands);
			int x = scan.scan(hands);
			String putcard = hands.get(x-1);
			if(chkAgain(putcard)==1) {
				System.out.printf("\n[ %s ]를 제출 하셨습니다.",putcard);
				return putcard;
			}else {
				System.out.println("다시 선택해 주세요");
			}
		}
	}
	
	//낸 카드가 맞는지 확인하는 메소드
	public int chkAgain(String putCard) {
		System.out.printf("[ %s ]로 확정 하시겠습니까? \n1. [ 제출 ] 2. [다시 선택]",putCard);
		int num = scan.chknum();
		return num;
	}
	
	/**
	 * 낸 카드가 있을 때 실행되는 로직 메소드
	 */
	public void delCard(List<String> deck, List<String> player, List<String> filed, String putcard) {
		filed.add(putcard);
		player.remove(putcard);
		deck.add(filed.get(0));
		filed.remove(0);
		Collections.shuffle(deck);
		putcard="";
	}
	
	/**
	 * 1장남았을 때 원카드(숫자) 외치는 수행 메소드
	 */
	public void oneCard(List<String> deck, List<String> player, List<String> AI) throws InterruptedException {
		
		System.out.println("[SYSTEM] 1~9999999까지의 수가 랜덤으로 출력됩니다. 그 값을 입력하세요!");
		System.out.println("\n[SYSTEM] 3초뒤에 시작합니다.");
		Thread.sleep(1000);
		System.out.println("[SYSTEM] 2초뒤에 시작합니다.");
		Thread.sleep(1000);
		System.out.println("[SYSTEM] 1초뒤에 시작합니다.");
		Thread.sleep(1000);
		System.out.println("시작");
		int a = (int) (Math.random()*9999999);
		long beforeTime = System.currentTimeMillis();
		System.out.println(a);
		while(true) {
			int b = scan.oneCard();
			if(a == b) {
				break;
			}
		}
		long afterTime = System.currentTimeMillis();
		long result = (afterTime - beforeTime)/1000;
		System.out.printf("걸린 시간 : [%d초]\n",result);
		int com = (int) (Math.random()*5)+1 ;
		if(result <= com) {//플레이어 승
			System.out.println("\"[딜러] : 플레이어가 원카드를 외쳤습니다\"");
			AI.add(deck.get(0));
			deck.remove(0);
			System.out.println("\"[딜러] : 컴퓨터에게 패널티 한장을 부여합니다\"");
		}else {//컴퓨터 승
			System.out.println("\"[딜러] : 컴퓨터가 이미 원카드를 외쳤습니다\"");
			player.add(deck.get(0));
			deck.remove(0);
			System.out.println("\"[딜러] : 플레이어에게 패널티 한장을 부여합니다\"");
		}
		Collections.shuffle(deck);
	}
}

	


