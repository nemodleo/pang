package 파ㅇ;
//메인 프레임의 본물에 해당되는 내용
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public  class MineGUI extends JPanel implements ActionListener,Protocol {
	//변수설정
	private JLabel[]	mineLabel	= null;
	private JLabel[]	scoreLabel	= null;
	private JLabel lblNewLabel = null ;
	
	
	private JButton		newButton	= null;
	//방향키
	private JButton button_1 = null;
	private JButton button_2 = null;
	private JButton button_3 = null;
	private JButton button_4 = null;
	
	private JLabel	lblMine	= null; // mine number
	private JLabel	lblScore= null; // score
	private JLabel[][]	lblground= null; 
	private JLabel[][]	lblscore= null; 


	private JPanel progressPanel	= null;
	private JPanel gamePanel		= null;
	
	private JPanel printgroundPanel		= null; //바닥 배열박스
	private JPanel printscorePanel		= null; //점수(수치배열박스)
	
	private JPanel controlPanel		= null; //방향키 버튼 들어갈
	
	/* ImageIcon Field */
	private ImageIcon[]	numImgList		= null;
	private ImageIcon	mineImage	= null;
	private ImageIcon	closeImage		= null;
	private ImageIcon	pressedImage	= null;
	private ImageIcon	flagImage		= null;
	private ImageIcon	goalImage		= null;
	private ImageIcon	groud1Image		= null;

	
	//경고창에 들어갈 내용 (게임 끝나거나 중간에 오류발생 시)
	private String			errorString		= "제대로 눌러주세요.";
	private String			winString		= "생존하였습니다.!!";
	private String			scoreString		= "\n점수 : ";
	private String			mineString		= "지뢰를 밟았습니다.\nBombbbbbbbbbbbbb!";
	
	
	Main M = new Main();//Main
		
	public MineGUI() {
		this.setLayout(new BorderLayout());

		setImageLoading();
		setProgressPane();
		setGamePane();
		setControlPane();

    	M.getMinePosition();
    	M.settingBoard();
    	M.start();
    	M.eplement();//
	}
	/* image loading method */
	public void setImageLoading() { //이미지는  /Img 파일에 있습니다.
		numImgList = new ImageIcon[10];
		for ( int i = 0; i < 10; i++ )
			numImgList[i] = new ImageIcon("img/" + i + "n.gif");
		
		mineImage = new ImageIcon("img/mine2.gif");
		
		closeImage		= new ImageIcon("img/close.gif");
		pressedImage	= new ImageIcon("img/pressed.gif");
		flagImage		= new ImageIcon("img/flag.gif");
		goalImage		= new ImageIcon("img/goal.jpg");
		groud1Image	= new ImageIcon("img/ground.jpg");
		
		
	}
	
	public void setControlPane() { //방향키 조작 패널 구성
		controlPanel = new JPanel();
		
		
		controlPanel.setLayout(new GridLayout(2, 3, 0, 0)); //레이아웃 설정, 그리드 레이아웃 가로 3개로 지정
		controlPanel.setBackground(Color.BLACK);
		//빈칸 라벨
			JLabel lblNewLabel = new JLabel("");
			controlPanel.add(lblNewLabel);
		//위쪽 방향키
			JButton button_1 = new JButton("^");
			button_1.setBackground(Color.WHITE);
			controlPanel.add(button_1);
			button_1.addActionListener(new NewcontrolButtonListener()); 

		//빈칸 라벨	
			JLabel lblNewLabel_1 = new JLabel("");
			controlPanel.add(lblNewLabel_1);
		//왼쪽 방향키	
			JButton button_2 = new JButton("<");
			button_2.setBackground(Color.WHITE);
			controlPanel.add(button_2);
			button_2.addActionListener(new NewcontrolButtonListener());
		//아래쪽 방향키	
			JButton button_3 = new JButton("v");
			button_3.setBackground(Color.WHITE);
			controlPanel.add(button_3);
			button_3.addActionListener(new NewcontrolButtonListener());
		//오른쪽 방향키	
			JButton button_4 = new JButton(">");
			button_4.setBackground(Color.WHITE);
			controlPanel.add(button_4);
			button_4.addActionListener(new NewcontrolButtonListener());
		
			//메인 프레임에 컨트롤패널 추가
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	/* method */
	public void setProgressPane() { //위쪽에 위치랑 팸널 , Mine 수, 점수(수치) 출력
		progressPanel = new JPanel();
		progressPanel.setBackground(Color.BLACK);
		progressPanel.setLayout( new GridLayout(1, 4) ); //그리드 에이아웃으로  4칸 설정 (2개,2개)로 할 거임.
		JPanel lPanel = new JPanel();
	    JPanel c1Panel = new JPanel();
	    JPanel c2Panel = new JPanel();
	    JPanel sPanel = new JPanel();
	    
	    lPanel.setBackground(Color.BLACK);
	    c1Panel.setBackground(Color.BLACK);
	    c2Panel.setBackground(Color.BLACK);
	    sPanel.setBackground(Color.BLACK);
	    
		JPanel minePanel = new JPanel();
		JPanel scorePanel = new JPanel();
		
		
		
		minePanel.setLayout( new GridLayout( 1, 3 ) ); //지뢰수 이미지 넣을려함.
		mineLabel = new JLabel[3];
		mineLabel[0] = new JLabel( numImgList[(MINE/100)%10] ); mineLabel[1] = new JLabel( numImgList[(MINE/10)%10] ); mineLabel[2] = new JLabel( numImgList[(MINE/1)%10] );
		
		minePanel.add( mineLabel[0] ); minePanel.add( mineLabel[1] ); minePanel.add( mineLabel[2] );
		lPanel.add( minePanel );
		
		scorePanel.setLayout( new GridLayout( 1, 3 ) ); //점수(수치)넣을려함
		scoreLabel = new JLabel[3];
		scoreLabel[0] = new JLabel( numImgList[(M.score/100)%10] ); scoreLabel[1] = new JLabel( numImgList[(M.score/10)%10] ); scoreLabel[2] = new JLabel( numImgList[(M.score/1)%10] );
		
		scorePanel.add( scoreLabel[0] ); scorePanel.add( scoreLabel[1] ); scorePanel.add( scoreLabel[2] );
		sPanel.add( scorePanel );
		
		lblMine = new JLabel("MINE NUMBER : ");	 //MINe
		lblMine.setForeground(Color.WHITE); //디자인
		c1Panel.add( lblMine );
		
		
		
		lblScore= new JLabel("SCORE / Detection value : ");//score
		lblScore.setForeground(Color.WHITE); //디자인
		c2Panel.add( lblScore );
		//
		progressPanel.add ( c1Panel );
		progressPanel.add ( lPanel );
		progressPanel.add ( c2Panel );
		progressPanel.add ( sPanel );
		
		this.add( progressPanel, BorderLayout.NORTH );
	}

	public void setGamePane() { //게임 패널
		gamePanel = new JPanel();
		gamePanel.setLayout( new GridLayout( 1,0,0,0 ) );
		printgroundPanel = new JPanel();
		printscorePanel = new JPanel();
		
		
		lblground =new JLabel[ROWS][COLS];
		printgroundPanel.setLayout(new GridLayout(ROWS, COLS));
		
		lblscore =new JLabel[ROWS][COLS];
		printscorePanel.setLayout(new GridLayout(ROWS, COLS));
		
	setprintgroundPane(); // 땅 패널
	setprintscorePane();//점수 패널
		
	
	    JPanel Panel = new JPanel();
	    JLabel grondImage =new JLabel(groud1Image); //'파ㅇ' 이미지 (가운데에 떡하니 들어감)
	    Panel.setBackground(Color.BLACK);
	    Panel.add(grondImage);
	    
	    
	    printgroundPanel.setBackground(Color.BLACK);
	    printscorePanel.setBackground(Color.BLACK);
		gamePanel.add(printscorePanel);gamePanel.add(Panel);gamePanel.add(printgroundPanel); //패널 추가
     
		
		this.add( gamePanel, BorderLayout.CENTER );
	}
	
	public void setprintgroundPane() { //땅 패널 (좌측)
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if(M.x==r&&M.y==c&&M.kkkkk==1){
					lblground [r][c] =new JLabel(mineImage);
					
				}
				else if(M.boardsss[r][c]==-2){
					lblground [r][c] =new JLabel(""+M.boards[r][c]);
					lblground [r][c].setForeground(Color.WHITE);
				}
				else lblground [r][c] =new JLabel(closeImage);

				printgroundPanel.add( lblground[r][c] );
			}
		}
	}
	public void setprintscorePane() { //점수 패널
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if(r==M.x&&c==M.y){
					lblscore [r][c] =new JLabel(flagImage);
				}
				else if(M.boardsss[r][c]==-2){
					lblscore [r][c] =new JLabel(pressedImage);
				}
				else if(r==ROWS-1&&c==M.COLS-1){
					lblscore [r][c] =new JLabel(goalImage);
				}
				else lblscore [r][c] =new JLabel(closeImage);
				printscorePanel.add( lblscore[r][c] );
			}
		}
	}

	public void reStartGame() { //버튼 누르면 실행 
		this.remove(progressPanel);
		this.remove(gamePanel);
		this.setVisible(false);
		setProgressPane();
		setGamePane();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* Inner class */	
	//리스너가 주가 됨.
	class NewcontrolButtonListener extends JFrame  implements ActionListener { //화면의 방향키 버튼 클릭시
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if ( b.getText().equals("^")){ //위
				if(M.x-1!=-1){	
						M.x-=1; //위로
						if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
							if(M.x==ROWS-1&&M.y==COLS-1){
								System.out.println("축하합니다. 살았군요!!"); //끝까지 감
								JOptionPane.showMessageDialog( this, winString+scoreString+M.score, "Win~!", JOptionPane.INFORMATION_MESSAGE );
	
							}
					        else if(M.boardss[M.x][M.y]==-1) {
					        	System.out.println("BOMBBBBBBBBBB!!!!!!!!!"); //지뢰 밟음
					        	JOptionPane.showMessageDialog( this, mineString+scoreString+M.score, "BOMBBBBBBBBBB!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE );
					        }
							M.score();  //점수 
						}
						else M.eplement(); M.score(); 
						reStartGame();
					}
				else {
					System.out.println("똑바로 눌러"); //판에서 벗어날려고 함.
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
	
				}
			}
			//위와 동일
			if ( b.getText().equals("<")){//왼쪽
				if(M.y-1!=-1){
					M.y-=1;//왼쪽으로
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("축하합니다. 살았군요!!");
							JOptionPane.showMessageDialog( this, winString+scoreString+M.score, "Win~!", JOptionPane.INFORMATION_MESSAGE );

						}
				        else if(M.boardss[M.x][M.y]==-1) {
				        	System.out.println("BOMBBBBBBBBBB!!!!!!!!!");
				        	JOptionPane.showMessageDialog( this, mineString+scoreString+M.score, "BOMBBBBBBBBBB!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE );
				        }
						M.score(); 
						
					}
					else M.eplement(); M.score(); 
					reStartGame();
				}
				else {
					System.out.println("똑바로 눌러");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
				
			}
			if ( b.getText().equals("v")){//아래쪽
				if(M.x+1!=ROWS){	
					M.x+=1;//아래로
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("축하합니다. 살았군요!!");
							JOptionPane.showMessageDialog( this, winString+scoreString+M.score, "Win~!", JOptionPane.INFORMATION_MESSAGE );

						}
				        else if(M.boardss[M.x][M.y]==-1) {
				        	System.out.println("BOMBBBBBBBBBB!!!!!!!!!");
				        	JOptionPane.showMessageDialog( this, mineString+scoreString+M.score, "BOMBBBBBBBBBB!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE );
				        }
						M.score(); 
						
					}
					else M.eplement(); M.score(); 
					reStartGame();
				}
				else {
					System.out.println("똑바로 눌러");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
			}
			if ( b.getText().equals(">")){//오른쪽
				if(M.y+1!=COLS){	
					M.y+=1;			//오른쪽으로
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("축하합니다. 살았군요!!");
							JOptionPane.showMessageDialog( this, winString+scoreString+M.score, "Win~!", JOptionPane.INFORMATION_MESSAGE );

						}
				        else if(M.boardss[M.x][M.y]==-1) {
				        	System.out.println("BOMBBBBBBBBBB!!!!!!!!!");
				        	JOptionPane.showMessageDialog( this, mineString+scoreString+M.score, "BOMBBBBBBBBBB!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE );
				        }
						M.score(); 
						
					}
					else M.eplement(); M.score();
					reStartGame();
				}
				else {
					System.out.println("똑바로 눌러");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		}
		
	}
	
}
