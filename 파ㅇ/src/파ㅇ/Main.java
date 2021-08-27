package 파ㅇ;

import javax.swing.*;
import java.util.Scanner;

public class Main implements Protocol {

	// 보드 배열
    public int[][] boards = new int[ROWS][COLS]; //점수판, 지뢰 -1
    public int[][] boardss = new int[ROWS][COLS]; //지뢰 확인용, 지뢰 -1
    public int[][] boardsss = new int[ROWS][COLS];//+, 사람 지나가면-2
    
    //지뢰 밟음 확인용
    public int kkkkk=0; //밟으면 1, 끝까지 가면 2
    
    public int x=0,y=0;//현재 위치
    
    public void getMinePosition(){ //지뢰 위치 설정
    	int n=0;

    	for(int i=0;i<MINE;i++){
    		int xx= (int)(Math.random()*ROWS);
        	int yy= (int)(Math.random()*COLS);
        	if(boards[xx][yy]==-1)i--; //만약 겹치면 이 실행 제외 한번더 실행한다.
        	else {
        		boards[xx][yy]=-1;
        		boardss[xx][yy]=-1;
        	}
    	}
    } 
    public void settingBoard(){// 맵에 점수 설정
        for(int x=0 ; x<ROWS ; x++){
            for(int y=0 ; y<COLS ; y++){                 
                 for(int i=0 ; i<ROWS ; i++){
                     for(int j=0 ; j<COLS ; j++){
                    	 if(boardss[x][y]==-1)break;
                    	 else if(boardss[i][j]==-1)
                    	 boards[x][y]+=(int)10/((x-i)*(x-i)+(y-j)*(y-j)); //피타고라스 정리를 이용한 거리 (정수형으로 바꾸어 오차 발생함)
                     }
                  }
            }
         }
    }

    public void start(){//콘송에 띄우기 위함
        for(int i=0 ; i<ROWS ; ++i){
            for(int j=0 ; j<COLS ; ++j){
            	boardsss[i][j]='+';
            }
        }
    }

    public void eplement() { //실행 (스펠링응 보지 마세요!)
    	
    	boardsss[0][0]=-2;
    	if(9==x&&9==y)kkkkk=2;
    	if(boardss[x][y]==-1)kkkkk++;
    	boardsss[x][y]=-2;// 인식 (x,y)를 지나감;/10,1,0의 값 //10확인하는 배열임
    	 
        for(int i=0 ; i<ROWS ; i++){
            for(int j=0 ; j<COLS ; j++){
            	
            	if(x==i&&y==j&&kkkkk==1) {
            		System.out.printf("     B");
            	}
            	else if(boardsss[i][j]==-2) {
            		System.out.printf("%6d",boards[i][j]); //사람
            	}
            	else {
            		System.out.printf("     %c",boardsss[i][j]);
            	}
            	
            }
            System.out.println();
            
        }
        
        System.out.println();
        
        for(int i=0 ; i<ROWS; i++){
            for(int j=0 ; j<COLS ; j++){
            	if(i==x&&j==y) {
            		System.out.printf("     H"); //현재 위치
            	}
            	else if(boardsss[i][j]==-2){
            		System.out.printf("     L");//지나갔던 위치
            	}
            	else {
            		System.out.printf("     %c",boardsss[i][j]);//+, 아직 안 가본 곳
            	}
            }
            System.out.println();
        }
    }
    
    public int score; 
    public void score(){
    	score=0; //점수(금속탐지기의 수치)
    	
        for(int i=0 ; i<ROWS; i++){
            for(int j=0 ; j<COLS ; j++){
            	if(boardsss[i][j]==-2)score+=boards[i][j];
            }
        }
        System.out.printf("\n점수 : %d\n",score);  //+++++끝까지가면 보너스 드으드으등 지뢰 밟으면 - 등등등
    }
    
    public Main(){
    	
    }
}
