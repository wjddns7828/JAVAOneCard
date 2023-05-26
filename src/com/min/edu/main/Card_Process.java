package com.min.edu.main;

import java.util.Collections;
import java.util.Scanner;

import com.min.edu.card.CardDeck;
import com.min.edu.data.dao.UsersDaoImpl;
import com.min.edu.player.AI;
import com.min.edu.player.Player;

/**
 * 원카드 게임의 프로세스가 진행되는 클래스
 */
public class Card_Process {

	Card_DTO DTO = new Card_DTO();
	Card_Fn cf = new Card_Fn();
	CardDeck allCard = new CardDeck();
	AI aiC = new AI();
	Player playerC = new Player();
	int userPen = 0;
	int AIPen = 0;
	UsersDaoImpl dao = new UsersDaoImpl();

	public void start() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		System.out.println("[SYSYEM]  [1]. 회원가입 / [2]. 로그인");
		int n = scan.nextInt();
		if(n==1) {
			register();
		}else {
			login();
		}
	}

	public void login()  {
		Scanner scan = new Scanner(System.in);
		System.out.println("[SYSYEM]  아이디를 입력하세요");
		DTO.id = scan.nextLine();
		System.out.println("[SYSYEM]  비밀번호를 입력하세요");
		DTO.pw = scan.nextLine();
		String a = dao.login(DTO.id);
		String b = "";

		if(DTO.pw.equals(a) == true) {
			System.out.println("[SYSYEM]  로그인 성공.");
			moneyChek();

		}else if ((a==b)==true){
			System.out.println("[SYSYEM]  계정이 없습니다.");
			System.out.println("[SYSYEM]  회원가입으로 이동합니다..");

			register();
		}else if (DTO.pw.equals(a) == false){
			System.out.println("[SYSYEM]  비밀번호가 틀립니다..");
			System.out.println("[SYSYEM]  관리자에게 물어보세요..");
			System.out.println("[관리자 임정환 : 010 - 5847 - 9809]  ");
		}		
	}

	public void register() {
		Scanner scan = new Scanner(System.in);
		System.out.println("[SYSYEM]  아이디를 입력하세요");
		String id = scan.next();
		System.out.println("[SYSYEM]  비밀번호를 입력하세요");
		String pw = scan.next();
		System.out.println("[SYSYEM]  이름을 입력하세요");
		String name = scan.next();
		int n = dao.createAcc(id, pw, name);
		if(n == 0) {
			System.out.println("[SYSYEM] 회원가입 성공 로그인 페이지로 이동합니다.");
			System.out.println("[SYSYEM] 프로그램을 다시 실행해 주세요.");
		}else {
			System.out.println("[SYSYEM]  회원가입에 실패하였습니다.");
			System.out.println("[SYSYEM]  실패 이유 : 아이디 중복 / 입력 범위 초과");
			System.out.println("[SYSYEM]  자세한 내용은 관리자에게 문의 하세요");
			System.out.println("[관리자 임정환 : 010 - 5847 - 9809]  ");
		}
	}
	public void moneyChek(){
		DTO.money = dao.getMoney(DTO.id);
		if(DTO.money !=0) {
			Scanner scan = new Scanner(System.in);
			System.out.printf("[SYSYEM]  현재 가지고 있는 돈은 [ %d 원] 입니다..\n",DTO.money);
			System.out.printf("[SYSYEM]  배팅하실 금액을 입력해 주세요 \n");
			DTO.betmoney = scan.nextInt();
			System.out.printf("[SYSYEM]  승리시 [%d]의 10배 [%d]원을 얻습니다. \n",DTO.betmoney,DTO.betmoney*10);
			System.out.printf("[SYSYEM]  패배시 관리자가 먹습니다.\n");
		}else {
			System.out.println("[SYSYEM]  돈을 입금해 주세요");
			System.out.println("[계좌번호]  1002-236-966351");
			try {
				win(process());		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void win(int playersize) {
		Scanner scan = new Scanner(System.in);
		if(playersize!=0) {
			System.out.printf("[SYSYEM]  77ㅓ억 \n");
			dao.chargeMoney(DTO.id, DTO.money-DTO.betmoney);
		}else {
			System.out.printf("[SYSYEM]  입금되었습니다.\n");
			dao.chargeMoney(DTO.id, DTO.money+(DTO.betmoney*10));
		}

		System.out.printf("[SYSYEM] [1]. 한판더 [2]. 종료\n");
		int a = scan.nextInt();
		if(a==1) {
			reset();
			moneyChek();
		}else {
			System.out.printf("[SYSYEM] 게임을 종료합니다.\n");
		}
	}
	public void reset() {
		for (String p : DTO.player) {
			DTO.player.remove(p);
		}
		for (String a : DTO.AI) {
			DTO.AI.remove(a);
		}
		for (String d : DTO.deck) {
			DTO.deck.remove(d);
		}
		for (String f : DTO.field) {
			DTO.field.remove(f);
		}
		DTO.putcard="";
	}

	public int process() throws InterruptedException {
		// 카드 덱 가져오기
		DTO.deck = allCard.CardOne();
		// 선후 정하기
		int turnCnt = playerC.first();
		// 초기 패 설정
		cf.divideCards(DTO.deck, DTO.player, 7);
		cf.divideCards(DTO.deck, DTO.AI, 7);

		// 초기 필드 값 설정
		cf.divideCards(DTO.deck, DTO.field, 2);// 초기 필드 값 설정
		//		cf.printFiled(cd.filed);

		while (true) {
			if (turnCnt % 2 == 0) { // 랜덤 숫자를 2로 나누었을 때 나머지 0이면 플레이어 턴
				Collections.sort(DTO.player);
				playerC.printCard(DTO.player);
				cf.printField(DTO.field);
				/*
				 * 패널티가 0인지 확인 해준다
				 */
				if (userPen != 0) {
					/*
					 * 패널티가 0이 아닐 시
					 */
					if ((playerC.listForAVal(DTO.player).size()) != 0 ) { // 상대가 A를 냈을 때 낼 수 있는 카드의 갯수를 가져와서 비교해준다
						DTO.putcard = playerC.putCard(playerC.listForAVal(DTO.player)); // A일 때 낼 수 있는 카드를 요약해서 출력하고 낸 카드 값을 반환해온다.
						// 낸 카드가 A인지 K인지 확인
						if (DTO.putcard.charAt(1) == 'A') {
							AIPen += userPen + 2; // AI 패널티 증가
							userPen = 0;
						} else if (DTO.putcard.charAt(1) == 'K') {
							AIPen = 0;
							userPen = 0; // K면 모든게 초기화
						}
					} else { // 낼 수 있는 카드가 없을 때
						Thread.sleep(2000);
						System.out.println("-----------------------------");
						System.out.println("[SYSYEM]  낼 카드가 없습니다.");
						System.out.println("-----------------------------");
						System.out.println("[SYSYEM]  손패에 패널티카드가 부여됩니다");
						System.out.println("\n================================== 턴 종료 ==================================");
						userPen = userPen+(userPen/2);
						DTO.putcard = "";
					}
				} else if (userPen == 0) {
					if ((playerC.listForVal(DTO.player, DTO.field.get(1)).size()) != 0) { // 내가 낼 수 있는 카드의 갯수를 가져와서 비교한다
						DTO.putcard = playerC.putCard(playerC.listForVal(DTO.player, DTO.field.get(1))); // 낼 수 있는 카드가 있을 때
						// 낸 카드가 A인지 확인
						if (DTO.putcard.charAt(1) == 'A') {
							AIPen += 2; // AI 패널티 증가
						}
						System.out.println("\n================================== 턴 종료 ==================================");
					} else { // 낼 수 있는 카드가 없을 때
						Thread.sleep(2000);
						System.out.println("-----------------------------");
						System.out.println("[SYSYEM] 낼 카드가 없습니다.");
						System.out.println("-----------------------------");
						System.out.println("[SYSYEM]  손패에 패널티카드가 부여됩니다");
						userPen += 1;
						DTO.putcard = "";
						System.out.println("\n================================== 턴 종료 ==================================");
					}
				}
				if (DTO.putcard.length() == 2) {// 조건문 : 플레이어가 낸 카드가 있을 때
					playerC.delCard(DTO.deck, DTO.player, DTO.field, DTO.putcard);
				} else {
				}
				// 조건문 : 패널티가 있을 때
				if (userPen != 0) {
					cf.divideCards(DTO.deck, DTO.player, userPen);
					userPen =0;
				}
				// 플레이어 손패 갯수 체크
				String handschk = cf.handsCheck(DTO.player.size());
				if (handschk.equals("승리")) {
					System.out.printf("[SYSYEM]  플레이어[%s] 는 [%s] 하였습니다.\n",DTO.name, handschk);
					break;
				} else if (handschk.equals("패배")) {
					System.out.printf("[SYSYEM]  플레이어는 [%s] 하였습니다.\n", handschk);
					break;
				} else if (handschk.equals("원카드")) {
					// 원카드 메소드로가는데 가서 값도 넣어줘야함
					playerC.oneCard(DTO.deck, DTO.player, DTO.AI);
				} else {
				}
				turnCnt++;
			}//USer 로직
			/*
			 * ==========================================================================================================================================
			 * ==========================================================================================================================================
			 * ==========================================================================================================================================
			 * ==========================================================================================================================================
			 */
			else { // 랜덤 숫자 2나누었을 때 나머지 1이면 컴퓨터 턴
				Thread.sleep(2000);
				//				a.printCard(cd.AI);// 컴퓨터 손패 출력 메소드
				/*
				 * 패널티가 0인지 확인 해준다
				 */
				if (AIPen != 0) {
					/*
					 * 패널티가 0이 아닐 시
					 */
					if ((aiC.listForAVal(DTO.AI).size()) != 0) { // 상대가 A를 냈을 때 낼 수 있는 카드의 갯수를 가져와서 비교해준다
						DTO.putcard = aiC.putCard(aiC.listForAVal(DTO.AI)); // A일 때 낼 수 있는 카드를 요약해서 출력하고 낸 카드 값을 반환해온다.
						// 낸 카드가 A인지 K인지 확인
						if (DTO.putcard.charAt(1) == 'A') {
							userPen += AIPen + 2; // AI 패널티 증가
							AIPen = 0;
						} else if (DTO.putcard.charAt(1) == 'K') {
							AIPen = 0;
							userPen = 0; // K면 모든게 초기화
						}
					} else { // 낼 수 있는 카드가 없을 때
						AIPen = AIPen + (AIPen / 2);
						DTO.putcard = "";
						Thread.sleep(2000);
					}

				} else if (AIPen == 0) {
					if ((aiC.listForVal(DTO.AI, DTO.field.get(1)).size()) != 0) { // 내가 낼 수 있는 카드의 갯수를 가져와서 비교한다
						DTO.putcard = aiC.putCard(aiC.listForVal(DTO.AI, DTO.field.get(1))); // 낼 수 있는 카드가 있을 때
						// 낸 카드가 A인지 확인
						if (DTO.putcard.charAt(1) == 'A') {
							userPen += 2; // AI 패널티 증가
						}
					} else { // 낼 수 있는 카드가 없을 때
						AIPen += 1;
						DTO.putcard = "";
						Thread.sleep(2000);
					}
				}

				if (DTO.putcard.length() == 2) {// 조건문 : 플레이어가 낸 카드가 있을 때
					System.out.printf("\n[SYSYEM]  AI가 낸 카드는 [ %s ]\n",DTO.putcard);
					playerC.delCard(DTO.deck, DTO.AI, DTO.field, DTO.putcard);					
				} else {
					System.out.println("\n-----------------------------");
					System.out.println("[SYSYEM]  AI가 낸 카드는 없습니다.");
					System.out.println("-----------------------------");
					System.out.println("[SYSYEM]  손패에 패널티카드가 부여됩니다");
				}
				// 조건문 : 패널티가 있을 때
				if (AIPen != 0) {
					cf.divideCards(DTO.deck, DTO.AI, AIPen);
					AIPen = 0;
				}
				// 플레이어 손패 갯수 체크
				String handschk = cf.handsCheck(DTO.AI.size());
				if (handschk.equals("승리")) {
					System.out.printf("[SYSYEM]  컴퓨터는 [%s] 하였습니다.\n", handschk);
					break;
				} else if (handschk.equals("패배")) {
					System.out.printf("[SYSYEM]  컴퓨터는 [%s] 하였습니다.\n", handschk);
					for (String s : DTO.player) {
						DTO.player.remove(s);
					}
					break;
				} else if (handschk.equals("원카드")) {
					// 원카드 메소드로가는데 가서 값도 넣어줘야함
					playerC.oneCard(DTO.deck, DTO.player, DTO.AI);
				} else {
				}
				System.out.println("[SYSYEM]  AI의 손패의 갯수 : ["+DTO.AI.size()+"]");
				//				a.printCard(cd.AI);
				turnCnt++;			
				System.out.println("\n================================== 턴 종료 ==================================");
				Thread.sleep(2000);
			}//AI 로직
		} // while
		return DTO.player.size();
	}
}
