package �Ĥ�;
//���� �������� ������ �ش�Ǵ� ����
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public  class MineGUI extends JPanel implements ActionListener,Protocol {
	//��������
	private JLabel[]	mineLabel	= null;
	private JLabel[]	scoreLabel	= null;
	private JLabel lblNewLabel = null ;
	
	
	private JButton		newButton	= null;
	//����Ű
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
	
	private JPanel printgroundPanel		= null; //�ٴ� �迭�ڽ�
	private JPanel printscorePanel		= null; //����(��ġ�迭�ڽ�)
	
	private JPanel controlPanel		= null; //����Ű ��ư ��
	
	/* ImageIcon Field */
	private ImageIcon[]	numImgList		= null;
	private ImageIcon	mineImage	= null;
	private ImageIcon	closeImage		= null;
	private ImageIcon	pressedImage	= null;
	private ImageIcon	flagImage		= null;
	private ImageIcon	goalImage		= null;
	private ImageIcon	groud1Image		= null;

	
	//���â�� �� ���� (���� �����ų� �߰��� �����߻� ��)
	private String			errorString		= "����� �����ּ���.";
	private String			winString		= "�����Ͽ����ϴ�.!!";
	private String			scoreString		= "\n���� : ";
	private String			mineString		= "���ڸ� ��ҽ��ϴ�.\nBombbbbbbbbbbbbb!";
	
	
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
	public void setImageLoading() { //�̹�����  /Img ���Ͽ� �ֽ��ϴ�.
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
	
	public void setControlPane() { //����Ű ���� �г� ����
		controlPanel = new JPanel();
		
		
		controlPanel.setLayout(new GridLayout(2, 3, 0, 0)); //���̾ƿ� ����, �׸��� ���̾ƿ� ���� 3���� ����
		controlPanel.setBackground(Color.BLACK);
		//��ĭ ��
			JLabel lblNewLabel = new JLabel("");
			controlPanel.add(lblNewLabel);
		//���� ����Ű
			JButton button_1 = new JButton("^");
			button_1.setBackground(Color.WHITE);
			controlPanel.add(button_1);
			button_1.addActionListener(new NewcontrolButtonListener()); 

		//��ĭ ��	
			JLabel lblNewLabel_1 = new JLabel("");
			controlPanel.add(lblNewLabel_1);
		//���� ����Ű	
			JButton button_2 = new JButton("<");
			button_2.setBackground(Color.WHITE);
			controlPanel.add(button_2);
			button_2.addActionListener(new NewcontrolButtonListener());
		//�Ʒ��� ����Ű	
			JButton button_3 = new JButton("v");
			button_3.setBackground(Color.WHITE);
			controlPanel.add(button_3);
			button_3.addActionListener(new NewcontrolButtonListener());
		//������ ����Ű	
			JButton button_4 = new JButton(">");
			button_4.setBackground(Color.WHITE);
			controlPanel.add(button_4);
			button_4.addActionListener(new NewcontrolButtonListener());
		
			//���� �����ӿ� ��Ʈ���г� �߰�
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	/* method */
	public void setProgressPane() { //���ʿ� ��ġ�� �Գ� , Mine ��, ����(��ġ) ���
		progressPanel = new JPanel();
		progressPanel.setBackground(Color.BLACK);
		progressPanel.setLayout( new GridLayout(1, 4) ); //�׸��� ���̾ƿ�����  4ĭ ���� (2��,2��)�� �� ����.
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
		
		
		
		minePanel.setLayout( new GridLayout( 1, 3 ) ); //���ڼ� �̹��� ��������.
		mineLabel = new JLabel[3];
		mineLabel[0] = new JLabel( numImgList[(MINE/100)%10] ); mineLabel[1] = new JLabel( numImgList[(MINE/10)%10] ); mineLabel[2] = new JLabel( numImgList[(MINE/1)%10] );
		
		minePanel.add( mineLabel[0] ); minePanel.add( mineLabel[1] ); minePanel.add( mineLabel[2] );
		lPanel.add( minePanel );
		
		scorePanel.setLayout( new GridLayout( 1, 3 ) ); //����(��ġ)��������
		scoreLabel = new JLabel[3];
		scoreLabel[0] = new JLabel( numImgList[(M.score/100)%10] ); scoreLabel[1] = new JLabel( numImgList[(M.score/10)%10] ); scoreLabel[2] = new JLabel( numImgList[(M.score/1)%10] );
		
		scorePanel.add( scoreLabel[0] ); scorePanel.add( scoreLabel[1] ); scorePanel.add( scoreLabel[2] );
		sPanel.add( scorePanel );
		
		lblMine = new JLabel("MINE NUMBER : ");	 //MINe
		lblMine.setForeground(Color.WHITE); //������
		c1Panel.add( lblMine );
		
		
		
		lblScore= new JLabel("SCORE / Detection value : ");//score
		lblScore.setForeground(Color.WHITE); //������
		c2Panel.add( lblScore );
		//
		progressPanel.add ( c1Panel );
		progressPanel.add ( lPanel );
		progressPanel.add ( c2Panel );
		progressPanel.add ( sPanel );
		
		this.add( progressPanel, BorderLayout.NORTH );
	}

	public void setGamePane() { //���� �г�
		gamePanel = new JPanel();
		gamePanel.setLayout( new GridLayout( 1,0,0,0 ) );
		printgroundPanel = new JPanel();
		printscorePanel = new JPanel();
		
		
		lblground =new JLabel[ROWS][COLS];
		printgroundPanel.setLayout(new GridLayout(ROWS, COLS));
		
		lblscore =new JLabel[ROWS][COLS];
		printscorePanel.setLayout(new GridLayout(ROWS, COLS));
		
	setprintgroundPane(); // �� �г�
	setprintscorePane();//���� �г�
		
	
	    JPanel Panel = new JPanel();
	    JLabel grondImage =new JLabel(groud1Image); //'�Ĥ�' �̹��� (����� ���ϴ� ��)
	    Panel.setBackground(Color.BLACK);
	    Panel.add(grondImage);
	    
	    
	    printgroundPanel.setBackground(Color.BLACK);
	    printscorePanel.setBackground(Color.BLACK);
		gamePanel.add(printscorePanel);gamePanel.add(Panel);gamePanel.add(printgroundPanel); //�г� �߰�
     
		
		this.add( gamePanel, BorderLayout.CENTER );
	}
	
	public void setprintgroundPane() { //�� �г� (����)
		
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
	public void setprintscorePane() { //���� �г�
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

	public void reStartGame() { //��ư ������ ���� 
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
	//�����ʰ� �ְ� ��.
	class NewcontrolButtonListener extends JFrame  implements ActionListener { //ȭ���� ����Ű ��ư Ŭ����
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if ( b.getText().equals("^")){ //��
				if(M.x-1!=-1){	
						M.x-=1; //����
						if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
							if(M.x==ROWS-1&&M.y==COLS-1){
								System.out.println("�����մϴ�. ��ұ���!!"); //������ ��
								JOptionPane.showMessageDialog( this, winString+scoreString+M.score, "Win~!", JOptionPane.INFORMATION_MESSAGE );
	
							}
					        else if(M.boardss[M.x][M.y]==-1) {
					        	System.out.println("BOMBBBBBBBBBB!!!!!!!!!"); //���� ����
					        	JOptionPane.showMessageDialog( this, mineString+scoreString+M.score, "BOMBBBBBBBBBB!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE );
					        }
							M.score();  //���� 
						}
						else M.eplement(); M.score(); 
						reStartGame();
					}
				else {
					System.out.println("�ȹٷ� ����"); //�ǿ��� ������� ��.
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
	
				}
			}
			//���� ����
			if ( b.getText().equals("<")){//����
				if(M.y-1!=-1){
					M.y-=1;//��������
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("�����մϴ�. ��ұ���!!");
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
					System.out.println("�ȹٷ� ����");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
				
			}
			if ( b.getText().equals("v")){//�Ʒ���
				if(M.x+1!=ROWS){	
					M.x+=1;//�Ʒ���
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("�����մϴ�. ��ұ���!!");
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
					System.out.println("�ȹٷ� ����");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
			}
			if ( b.getText().equals(">")){//������
				if(M.y+1!=COLS){	
					M.y+=1;			//����������
					if(M.boardss[M.x][M.y]==-1||(M.x==ROWS-1&&M.y==COLS-1)){
						if(M.x==ROWS-1&&M.y==COLS-1){
							System.out.println("�����մϴ�. ��ұ���!!");
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
					System.out.println("�ȹٷ� ����");
					JOptionPane.showMessageDialog( this, errorString, "Error", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		}
		
	}
	
}
