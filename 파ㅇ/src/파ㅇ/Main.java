package �Ĥ�;

import javax.swing.*;
import java.util.Scanner;

public class Main implements Protocol {

	// ���� �迭
    public int[][] boards = new int[ROWS][COLS]; //������, ���� -1
    public int[][] boardss = new int[ROWS][COLS]; //���� Ȯ�ο�, ���� -1
    public int[][] boardsss = new int[ROWS][COLS];//+, ��� ��������-2
    
    //���� ���� Ȯ�ο�
    public int kkkkk=0; //������ 1, ������ ���� 2
    
    public int x=0,y=0;//���� ��ġ
    
    public void getMinePosition(){ //���� ��ġ ����
    	int n=0;

    	for(int i=0;i<MINE;i++){
    		int xx= (int)(Math.random()*ROWS);
        	int yy= (int)(Math.random()*COLS);
        	if(boards[xx][yy]==-1)i--; //���� ��ġ�� �� ���� ���� �ѹ��� �����Ѵ�.
        	else {
        		boards[xx][yy]=-1;
        		boardss[xx][yy]=-1;
        	}
    	}
    } 
    public void settingBoard(){// �ʿ� ���� ����
        for(int x=0 ; x<ROWS ; x++){
            for(int y=0 ; y<COLS ; y++){                 
                 for(int i=0 ; i<ROWS ; i++){
                     for(int j=0 ; j<COLS ; j++){
                    	 if(boardss[x][y]==-1)break;
                    	 else if(boardss[i][j]==-1)
                    	 boards[x][y]+=(int)10/((x-i)*(x-i)+(y-j)*(y-j)); //��Ÿ��� ������ �̿��� �Ÿ� (���������� �ٲپ� ���� �߻���)
                     }
                  }
            }
         }
    }

    public void start(){//�ܼۿ� ���� ����
        for(int i=0 ; i<ROWS ; ++i){
            for(int j=0 ; j<COLS ; ++j){
            	boardsss[i][j]='+';
            }
        }
    }

    public void eplement() { //���� (���縵�� ���� ������!)
    	
    	boardsss[0][0]=-2;
    	if(9==x&&9==y)kkkkk=2;
    	if(boardss[x][y]==-1)kkkkk++;
    	boardsss[x][y]=-2;// �ν� (x,y)�� ������;/10,1,0�� �� //10Ȯ���ϴ� �迭��
    	 
        for(int i=0 ; i<ROWS ; i++){
            for(int j=0 ; j<COLS ; j++){
            	
            	if(x==i&&y==j&&kkkkk==1) {
            		System.out.printf("     B");
            	}
            	else if(boardsss[i][j]==-2) {
            		System.out.printf("%6d",boards[i][j]); //���
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
            		System.out.printf("     H"); //���� ��ġ
            	}
            	else if(boardsss[i][j]==-2){
            		System.out.printf("     L");//�������� ��ġ
            	}
            	else {
            		System.out.printf("     %c",boardsss[i][j]);//+, ���� �� ���� ��
            	}
            }
            System.out.println();
        }
    }
    
    public int score; 
    public void score(){
    	score=0; //����(�ݼ�Ž������ ��ġ)
    	
        for(int i=0 ; i<ROWS; i++){
            for(int j=0 ; j<COLS ; j++){
            	if(boardsss[i][j]==-2)score+=boards[i][j];
            }
        }
        System.out.printf("\n���� : %d\n",score);  //+++++���������� ���ʽ� ���������� ���� ������ - ����
    }
    
    public Main(){
    	
    }
}
